package fr.greencodeinitiative.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.*;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.Collections;
import java.util.List;

@Rule(key = "ECSystemGc")
@DeprecatedRuleKey(repositoryKey = "greencodeinitiative-java", ruleKey = "ECSystemGc")
public class AvoidCallingSystemGC extends IssuableSubscriptionVisitor {
    public static final String MESSAGE_RULE = "Avoid Calling System.gc";
    private static final String SYSTEM_CLASS = "java.lang.System";
    private static final String GC_METHOD = "gc";

    @Override
    public List<Kind> nodesToVisit() {
        return Collections.singletonList(Kind.METHOD_INVOCATION);
    }

    public void visitNode(Tree tree) {
        MethodInvocationTree methodInvocationTree = (MethodInvocationTree) tree;
        if (isSystemGcMethod(methodInvocationTree)) {
            reportIssue(methodInvocationTree, MESSAGE_RULE);
        }
    }

    private boolean isSystemGcMethod(MethodInvocationTree methodInvocationTree) {
        ExpressionTree methodSelect = methodInvocationTree.methodSelect();
        if (methodSelect.is(Kind.MEMBER_SELECT)) {
            MemberSelectExpressionTree memberSelect = (MemberSelectExpressionTree) methodSelect;
            IdentifierTree identifier = memberSelect.identifier();
            String methodName = identifier.name();
            return SYSTEM_CLASS.equals(memberSelect.expression().symbolType().fullyQualifiedName()) && GC_METHOD.equals(methodName);
        }
        return false;
    }
}