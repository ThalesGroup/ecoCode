package fr.greencodeinitiative.java.checks;

import org.sonar.check.Rule;
import java.util.Collections;
import java.util.List;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ImportTree;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.TypeTree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

@Rule(key = "ECLibrary")
@DeprecatedRuleKey(repositoryKey = "greencodeinitiative-java", ruleKey = "ECLibrary")
public class NoImportAllFromLibrary extends IssuableSubscriptionVisitor {
    public static final String MESSAGE_RULE = "Use Primitive Type";

    public List<Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.IMPORT);
    }

    public void visitNode(Tree tree) {
        ImportTree importTree = (ImportTree)tree;
        TypeTree typeTree = (TypeTree)importTree.qualifiedIdentifier();
        if (typeTree.is(new Kind[]{Kind.MEMBER_SELECT})) {
            MemberSelectExpressionTree memberSelect = (MemberSelectExpressionTree)typeTree;
            String importStatement = memberSelect.identifier().name();
            if (importStatement.equals("*")) {
                this.reportIssue(importTree, MESSAGE_RULE);
            }
        }

    }
}