package fr.greencodeinitiative.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class UseOfMethodsForBasicOperationsTest {
    @Test
    void test() {
        CheckVerifier.newVerifier()
                .onFile("src/test/files/UseOfMethodsForBasicOperations.java")
                .withCheck(new UseOfMethodsForBasicOperations())
                .verifyIssues();
    }
}