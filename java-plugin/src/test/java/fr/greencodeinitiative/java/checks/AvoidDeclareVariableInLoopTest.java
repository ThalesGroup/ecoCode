package fr.greencodeinitiative.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class AvoidDeclareVariableInLoopTest {
    @Test
    void test() {
        CheckVerifier.newVerifier()
                .onFile("src/test/files/AvoidDeclareVariableInLoop.java")
                .withCheck(new AvoidDeclareVariableInLoop())
                .verifyIssues();
    }
}