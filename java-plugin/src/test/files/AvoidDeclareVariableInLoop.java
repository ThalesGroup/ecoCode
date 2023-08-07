package fr.greencodeinitiative.java.checks;

public class AvoidDeclareVariableInLoop {
    private void test(){
        for(int i = 0; i < 5; i++){
            int x = i * 2; // Noncompliant
        }

        int x; // Compliant
        for(int i = 0; i < 5; i++){
            x = i * 2;
        }
    }
}