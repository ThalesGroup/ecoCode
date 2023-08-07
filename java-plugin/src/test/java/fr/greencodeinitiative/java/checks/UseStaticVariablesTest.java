package fr.greencodeinitiative.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class UseStaticVariablesTest {
    @Test
    void test() {
        CheckVerifier.newVerifier()
                .onFile("src/test/files/UseStaticVariables.java")
                .withCheck(new UseStaticVariables())
                .verifyIssues();
    }
}