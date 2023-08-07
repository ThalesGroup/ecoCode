package fr.greencodeinitiative.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class UsePrimitiveTypeTest {
    @Test
    void test() {
        CheckVerifier.newVerifier()
                .onFile("src/test/files/UsePrimitiveType.java")
                .withCheck(new UsePrimitiveType())
                .verifyIssues();
    }
}