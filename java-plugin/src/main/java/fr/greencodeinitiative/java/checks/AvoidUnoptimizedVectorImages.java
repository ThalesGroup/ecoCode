//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fr.greencodeinitiative.java.checks;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.LiteralTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

@Rule(key = "EC10")
public class AvoidUnoptimizedVectorImages extends IssuableSubscriptionVisitor {
    private static final Pattern LAYERS_PATTERN = Pattern.compile("</g>");

    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Kind.STRING_LITERAL);
    }

    public void visitNode(Tree tree) {
        LiteralTree literalTree = (LiteralTree)tree;
        if (literalTree.is(new Tree.Kind[]{Kind.STRING_LITERAL})) {
            String value = ((LiteralTree)tree).value();
            this.checkSVG(value, literalTree);
        }

    }

    private void checkSVG(String value, LiteralTree literalTree) {
        this.checkComments(value, literalTree);
        this.checkLayers(value, literalTree);
        this.checkNamespaces(value, literalTree);
        this.checkMetadata(value, literalTree);
    }

    private void checkComments(String value, LiteralTree literalTree) {
        if (this.isSvgTagNotDetected(value)) {
            if (value.contains("<!--") || value.contains("-->")) {
                this.reportIssue(literalTree, "Avoid Unoptimized Vector Image");
            }

        }
    }

    private void checkLayers(String value, LiteralTree literalTree) {
        if (!this.isSvgTagNotDetected(value)) {
            Matcher matcher = LAYERS_PATTERN.matcher(value);

            int matches;
            for(matches = 0; matcher.find(); ++matches) {
            }

            if (matches > 1) {
                this.reportIssue(literalTree, "Avoid Unoptimized Vector Image");
            }

        }
    }

    private void checkNamespaces(String value, LiteralTree literalTree) {
        if (!this.isSvgTagNotDetected(value)) {
            if (value.contains("xmlns:") && !value.contains("xmlns:svg=")) {
                this.reportIssue(literalTree, "Avoid Unoptimized Vector Image");
            }

        }
    }

    private void checkMetadata(String value, LiteralTree literalTree) {
        if (!this.isSvgTagNotDetected(value)) {
            if (value.contains("</metadata>")) {
                this.reportIssue(literalTree, "Avoid Unoptimized Vector Image");
            }

        }
    }

    private boolean isSvgTagNotDetected(String value) {
        return !value.contains("</svg>");
    }
}
