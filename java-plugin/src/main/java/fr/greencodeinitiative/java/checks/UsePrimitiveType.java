package fr.greencodeinitiative.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ArrayTypeTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.VariableTree;
import org.sonar.plugins.java.api.tree.WildcardTree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.Collections;
import java.util.List;

@Rule(key = "ECPrimitive")
@DeprecatedRuleKey(repositoryKey = "greencodeinitiative-java", ruleKey = "ECPrimitive")
public class UsePrimitiveType extends IssuableSubscriptionVisitor {
    public static final String MESSAGE_RULE = "Use Primitive Type";

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.VARIABLE);
    }

    @Override
    public void visitNode(Tree tree) {
        VariableTree variableTree = (VariableTree) tree;
        Tree typeTree = variableTree.type();
        if(typeTree.is(Tree.Kind.PRIMITIVE_TYPE)){
            return;
        }
        if(typeTree.is(Tree.Kind.ARRAY_TYPE)){
            ArrayTypeTree arrayTypeTree = (ArrayTypeTree) typeTree;
            Tree elementTypeTree = arrayTypeTree.type();
            if(elementTypeTree.is(Tree.Kind.PRIMITIVE_TYPE)){
                return;
            }
        }
        if(typeTree.is(Tree.Kind.UNBOUNDED_WILDCARD)){
            WildcardTree wildcardTree = (WildcardTree) typeTree;
            Tree bondTypeTree = wildcardTree.bound();
            if(bondTypeTree != null && bondTypeTree.is(Tree.Kind.PRIMITIVE_TYPE)){
                return;
            }
        }
        reportIssue(variableTree.simpleName(), MESSAGE_RULE);
    }
}