package fr.greencodeinitiative.java.checks;

import org.eclipse.core.runtime.internal.adaptor.EclipseAppLauncher;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.Symbol;
import org.sonar.plugins.java.api.tree.*;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import javax.swing.plaf.nimbus.State;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Rule(
        key = "EC7",
        name = "Developpement",
        description = "<p>Rewrite native Getter/Setters </p>",
        priority = Priority.MINOR,
        tags = {"bug"})
@DeprecatedRuleKey(repositoryKey = "greencodeinitiative-java", ruleKey = "EC7")
public class AvoidGetterAndSetter extends IssuableSubscriptionVisitor {
    public static final String RULE_KEY = "EC7";
    public static final String MESSAGE_RULE = "Avoid creating getter and setter methods in classes";

    private static final String GETTER_PREFIX = "get";
    private static final String SETTER_PREFIX = "set";

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Kind.METHOD);
    }

    public void visitNode(Tree tree) {
        MethodTree methodTree = (MethodTree) tree;
        if(isGetterOrSetter(methodTree)){
            String methodName = methodTree.simpleName().name();
            String propertyName = getPropertyName(methodName);
            if(propertyName != null){
                VariableTree variable = findVariable(methodTree, propertyName);
                if(variable == null){
                    reportIssue(methodTree, MESSAGE_RULE);
                } else {
                    boolean isGetter = isGetterMethod(methodName);
                    boolean isSetter = isSetterMethod(methodName);
                    if(isGetter && !hasCorrespondingSetter(methodName, variable)){
                        reportIssue(methodTree, MESSAGE_RULE);
                    } else if (isSetter && !hasCorrespondingGetter(methodName, variable)) {
                        reportIssue(methodTree, MESSAGE_RULE);
                    }
                }
            }
        }
    }

    private boolean isGetterOrSetter(MethodTree methodTree){
        String methodName = methodTree.simpleName().name();
        return isGetterMethod(methodName) || isSetterMethod(methodName);
    }

    private boolean isGetterMethod(String methodName){
        return methodName.startsWith(GETTER_PREFIX) && methodName.length() > GETTER_PREFIX.length();
    }

    private boolean isSetterMethod(String methodName){
        return methodName.startsWith(SETTER_PREFIX) && methodName.length() > SETTER_PREFIX.length();
    }
    private String getPropertyName(String methodName){
        if(isGetterMethod(methodName)){
            return methodName.substring(GETTER_PREFIX.length());
        } else if (isSetterMethod(methodName)) {
            return methodName.substring(SETTER_PREFIX.length());
        }
        return null;
    }

    private VariableTree findVariable(MethodTree methodTree, String property){
        ClassTree classTree = (ClassTree) methodTree.parent();
        for(Tree member : classTree.members()){
            if(member.is(Kind.VARIABLE)){
                VariableTree variable = (VariableTree) member;
                if(variable.simpleName().name().equals(property)){
                    return variable;
                }
            }
        }
        return null;
    }

    private boolean hasCorrespondingSetter(String getterName, VariableTree variable){
        String setterName = SETTER_PREFIX + variable.simpleName().name();
        return hasMethodWithName((Symbol.TypeSymbol) variable.symbol().owner(), setterName);
    }

    private boolean hasCorrespondingGetter(String setterName, VariableTree variable){
        String getterName = GETTER_PREFIX + variable.simpleName().name();
        return hasMethodWithName((Symbol.TypeSymbol) variable.symbol().owner(), getterName);
    }

    private boolean hasMethodWithName(Symbol.TypeSymbol typeSymbol, String methodName){
        return typeSymbol.memberSymbols().stream().anyMatch(symbol -> symbol.isMethodSymbol() && symbol.name().equals(methodName));
    }

}