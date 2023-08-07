package fr.greencodeinitiative.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.BlockTree;
import org.sonar.plugins.java.api.tree.ForStatementTree;
import org.sonar.plugins.java.api.tree.StatementTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.Collections;
import java.util.List;

@Rule(
        key = "ECVarInLoop",
        name = "Performance",
        description = "<p>Avoid declare variable in loop</p>",
        priority = Priority.MINOR,
        tags = {"bug"})
@DeprecatedRuleKey(repositoryKey = "greencodeinitiative-java", ruleKey = "ECVarInLoop")
public class AvoidDeclareVariableInLoop extends IssuableSubscriptionVisitor {
    public static final String RULE_KEY = "ECVarInLoop";
    public static final String MESSAGE_RULE = "Avoid declare variables in loops";

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.FOR_STATEMENT);
    }

    @Override
    public void visitNode(Tree tree){
        ForStatementTree forStatementTree = (ForStatementTree) tree;
        StatementTree body = forStatementTree.statement();

        if(body.is(Tree.Kind.BLOCK)){
            BlockTree blockTree = (BlockTree) body;

            blockTree.body().stream()
                    .filter(statement -> statement.is(Tree.Kind.VARIABLE))
                    .forEach(varDecl -> reportIssue(varDecl, MESSAGE_RULE));
        } else if(body.is(Tree.Kind.VARIABLE)) {
            reportIssue(body, MESSAGE_RULE);
        }
    }
}