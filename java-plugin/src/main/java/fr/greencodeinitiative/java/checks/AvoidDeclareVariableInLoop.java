package fr.greencodeinitiative.java.checks;


import java.util.Arrays;
import java.util.List;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.BlockTree;
import org.sonar.plugins.java.api.tree.ForStatementTree;
import org.sonar.plugins.java.api.tree.ForEachStatement;
import org.sonar.plugins.java.api.tree.StatementTree;
import org.sonar.plugins.java.api.tree.DoWhileStatementTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.WhileStatementTree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

@Rule(key = "ECVarInLoop")
@DeprecatedRuleKey(repositoryKey = "greencodeinitiative-java", ruleKey = "ECVarInLoop")
public class AvoidDeclareVariableInLoop extends IssuableSubscriptionVisitor {
    public static final String MESSAGE_RULE = "Avoid declare variables in loops";

    @Override
    public List<Kind> nodesToVisit() {
        return Arrays.asList(
                Tree.Kind.FOR_STATEMENT,
                Tree.Kind.WHILE_STATEMENT,
                Tree.Kind.DO_STATEMENT,
                Tree.Kind.FOR_EACH_STATEMENT
        );
    }

    @Override
    public void visitNode(Tree tree) {
        if (tree.is(Kind.FOR_STATEMENT)) {
            // ForStatementTree
            ForStatementTree forStatement = (ForStatementTree) tree;
            checkLoopBody(forStatement.statement());
        } else if (tree.is(Kind.WHILE_STATEMENT)) {
            // WhileStatementTree
            WhileStatementTree whileStatement = (WhileStatementTree) tree;
            checkLoopBody(whileStatement.statement());
        } else if (tree.is(Kind.DO_STATEMENT)) {
            // DoWhileStatementTree
            DoWhileStatementTree doWhileStatement = (DoWhileStatementTree) tree;
            checkLoopBody( doWhileStatement.statement());
        } else if (tree.is(Kind.FOR_EACH_STATEMENT)) {
            // ForEachStatementTree
            ForEachStatement forEachStatement = (ForEachStatement) tree;
            checkLoopBody(forEachStatement.statement());
        }
    }

    private void checkLoopBody(StatementTree body) {
        if (body.is(Kind.BLOCK)) {
            BlockTree block = (BlockTree) body;
            block.body().stream()
                    .filter(statement -> statement.is(Kind.VARIABLE))
                    .forEach(varDeclaration -> reportIssue(varDeclaration, MESSAGE_RULE));
        } else if (body.is(Kind.VARIABLE)) {
            reportIssue(body, MESSAGE_RULE);
        }
    }
}