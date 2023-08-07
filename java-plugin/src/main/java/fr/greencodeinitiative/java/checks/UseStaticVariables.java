package fr.greencodeinitiative.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.*;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.*;

@Rule(
        key = "ECStatic",
        name = "Developpement",
        description = "<p>Use static variables for shared data </p>",
        priority = Priority.MINOR,
        tags = {"bug"})
@DeprecatedRuleKey(repositoryKey = "greencodeinitiative-java", ruleKey = "ECStatic")
public class UseStaticVariables extends IssuableSubscriptionVisitor {

    public static final String RULE_KEY = "ECStatic";
    public static final String MESSAGE_RULE = "Use static instead of nothing";

    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.VARIABLE);
    }

    public void visitNode(Tree tree) {
        VariableTree variableTree = (VariableTree) tree;
        if (!isStatic(variableTree)) {
            reportIssue(variableTree, MESSAGE_RULE);
        }
    }

    private boolean isStatic(VariableTree variableTree) {
        ModifiersTree modifiers = variableTree.modifiers();
        if (modifiers != null && !modifiers.isEmpty()) {
            for (ModifierKeywordTree modifier : variableTree.modifiers().modifiers()) {
                if (modifier.modifier() == Modifier.STATIC) {
                    return true;
                }
            }
        }
        return false;

    }
}
