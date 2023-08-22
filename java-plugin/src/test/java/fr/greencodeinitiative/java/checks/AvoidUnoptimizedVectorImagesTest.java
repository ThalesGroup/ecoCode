package fr.greencodeinitiative.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class AvoidUnoptimizedVectorImagesTest {
    @Test
    void test() {
        CheckVerifier.newVerifier()
                .onFile("src/test/files/AvoidUnoptimizedVectorImages.java")
                .withCheck(new AvoidStatementForDMLQueries())
                .verifyIssues();
    }
}
