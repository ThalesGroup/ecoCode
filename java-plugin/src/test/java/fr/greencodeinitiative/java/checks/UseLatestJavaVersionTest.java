package fr.greencodeinitiative.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class UseLatestJavaVersionTest {
    @Test
    void test() {
        CheckVerifier.newVerifier()
                .onFile("src/test/files/UseLatestJavaVersion.java")
                .withCheck(new UseLatestJavaVersion())
                .verifyIssues();
    }

}
