package fr.greencodeinitiative.java.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.Collections;
import java.util.List;

@Rule(key = "EC29")
@DeprecatedRuleKey(repositoryKey = "greencodeinitiative-java", ruleKey = "EC29")
public class UseLatestJavaVersion extends IssuableSubscriptionVisitor {

    protected static final String MESSAGE_RULE = "Use latest version of your environment";

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.CLASS);
    }

    @Override
    public void visitNode(Tree tree) {

        ClassTree classTree = (ClassTree) tree;
        if (!this.context.getJavaVersion().isJava19Compatible()) {
            reportIssue(classTree, MESSAGE_RULE);
        }
    }
}