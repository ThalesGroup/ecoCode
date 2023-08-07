package fr.greencodeinitiative.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.MethodMatchers;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.Collections;
import java.util.List;

@Rule(
        key = "ECRecord",
        name = "Developpement",
        description = "<p>Use Records Instead of Setter or Getter</p>",
        priority = Priority.MINOR,
        tags = {"bug"})
@DeprecatedRuleKey(repositoryKey = "greencodeinitiative-java", ruleKey = "ECRecord")
public class UseRecordInsteadOfSetterOrGetter extends IssuableSubscriptionVisitor {
    public static final String RULE_KEY = "ECRecord";
    public static final String MESSAGE_RULE = "Avoid Using Getter and Setter";

    private static final MethodMatchers GETTER_SETTER = MethodMatchers.create()
            .ofAnyType()
            .name(name -> name.startsWith("get") || name.startsWith("set"))
            .withAnyParameters()
            .build();

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.METHOD);
    }
    @Override
    public void visitNode(Tree tree){

        MethodTree methodTree = (MethodTree) tree;

        if(!context.getJavaVersion().isJava17Compatible() || context.getJavaVersion().isJava8Compatible()){
            if(GETTER_SETTER.matches(methodTree)){
                reportIssue(methodTree, MESSAGE_RULE);
            }
        }
    }

}