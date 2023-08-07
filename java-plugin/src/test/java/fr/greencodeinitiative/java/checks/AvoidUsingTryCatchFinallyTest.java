package fr.greencodeinitiative.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class AvoidUsingTryCatchFinallyTest {

    @Test
    void test() {
        CheckVerifier.newVerifier()
                .onFile("src/test/files/AvoidUsingTryCatchFinally.java")
                .withCheck(new AvoidUsingTryCatchFinally())
                .verifyIssues();
    }

}