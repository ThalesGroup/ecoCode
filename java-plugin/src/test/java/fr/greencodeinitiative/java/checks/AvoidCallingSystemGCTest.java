package fr.greencodeinitiative.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;


public class AvoidCallingSystemGCTest {

    @Test
    void test() {
        CheckVerifier.newVerifier()
                .onFile("src/test/files/AvoidCallingSystemGC.java")
                .withCheck(new AvoidCallingSystemGC())
                .verifyIssues();
    }

}