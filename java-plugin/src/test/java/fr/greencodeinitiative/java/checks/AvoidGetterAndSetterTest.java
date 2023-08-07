package fr.greencodeinitiative.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class AvoidGetterAndSetterTest {

    @Test
    void test() {
        CheckVerifier.newVerifier()
                .onFile("src/test/files/AvoidGetterAndSetter.java")
                .withCheck(new AvoidGetterAndSetter())
                .verifyIssues();
    }

}