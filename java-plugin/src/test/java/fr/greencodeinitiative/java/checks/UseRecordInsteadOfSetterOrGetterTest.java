package fr.greencodeinitiative.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class UseRecordInsteadOfSetterOrGetterTest {
    @Test
    void test() {
        CheckVerifier.newVerifier()
                .onFile("src/test/files/UseRecordInsteadOfSetterOrGetter.java")
                .withCheck(new UseRecordInsteadOfSetterOrGetter())
                .verifyIssues();
    }
}