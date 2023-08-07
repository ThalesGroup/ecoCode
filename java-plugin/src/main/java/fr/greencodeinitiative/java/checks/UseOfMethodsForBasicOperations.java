package fr.greencodeinitiative.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonarsource.analyzer.commons.annotations.DeprecatedRuleKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Rule(
        key = "EC22",
        name = "Developpement",
        description = "<p>Use of Methods for basic operations </p>",
        priority = Priority.MINOR,
        tags = {"bug"})
@DeprecatedRuleKey(repositoryKey = "greencodeinitiative-java", ruleKey = "EC22")

public class UseOfMethodsForBasicOperations extends IssuableSubscriptionVisitor{
    public static final String RULE_KEY = "EC22";
    public static final String MESSAGE_RULE = "Use of methods for basic operations";


    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.METHOD_INVOCATION);
    }

    @Override
    public void visitNode(Tree tree){
        AtomicBoolean contains = new AtomicBoolean(false);

        MethodInvocationTree methodInvocationTree = (MethodInvocationTree) tree;
        String methodName = methodInvocationTree.symbol().name();

        List<Tree> parents = getAllParents(tree, new ArrayList<>());

        for(Tree parent : parents){
            if(parent.is(Tree.Kind.METHOD)){
                MethodTree methodTree = (MethodTree) parent;
                if(isFunctionDeclared(methodTree, methodName)) {
                    contains.set(true);
                    break;
                }
            } else if(parent.is(Tree.Kind.CLASS, Tree.Kind.INTERFACE, Tree.Kind.ENUM)) {
                ClassTree classTree = (ClassTree) parent;
                List<Tree> members = classTree.members();
                for(Tree member : members){
                    if(member.is(Tree.Kind.METHOD)){
                        MethodTree methodTree1 = (MethodTree) member;
                        if(isFunctionDeclared(methodTree1, methodName)){
                            contains.set(true);
                            break;
                        }
                    }
                }
            }
        }

        if(!contains.get()){
            reportIssue(tree, MESSAGE_RULE);
        }
    }

    private boolean isFunctionDeclared(MethodTree methodTree, String methodName) {
        if(methodTree == null){
            return false;
        }
        return methodTree.simpleName().name().trim().equals(methodName.trim());
    }

    private List<Tree> getAllParents(Tree tree, List<Tree> list) {
        if(tree == null){
            return list;
        }

        Tree parent = tree.parent();

        if(parent == null){
            return list;
        }

        list.add(parent);

        return getAllParents(parent, list);
    }


}