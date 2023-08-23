package fr.greencodeinitiative.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class NoImportAllFromLibraryTest {
    @Test
    void test() {
        CheckVerifier.newVerifier()
                .onFile("src/test/files/NoImportAllFromLibrary.java")
                .withCheck(new NoImportAllFromLibrary())
                .verifyIssues();
    }
}
