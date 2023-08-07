package fr.greencodeinitiative.java.checks;

import java.util.Collections;
import java.util.List;


import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;

import org.sonar.plugins.java.api.tree.*;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

@Rule(
        key = "EC34",
        name = "Developpement",
        description = "<p>Avoid Using Try Catch Finally</p>",
        priority = Priority.MINOR,
        tags = {"bug"})
@DeprecatedRuleKey(repositoryKey = "greencodeinitiative-java", ruleKey = "EC34")

public class AvoidUsingTryCatchFinally extends IssuableSubscriptionVisitor {

    public static final String RULE_KEY = "EC34";
    public static final String MESSAGE_RULE = "Avoid the use of try-catch";

    @Override
    public List<Kind> nodesToVisit() {
        return Collections.singletonList(Kind.TRY_STATEMENT);
    }

    public void visitNode(Tree tree) {
        TryStatementTree tree1 = (TryStatementTree) tree;
        SyntaxToken tryKeyword = tree1.tryKeyword();
        reportIssue(tryKeyword, MESSAGE_RULE);

    }
}