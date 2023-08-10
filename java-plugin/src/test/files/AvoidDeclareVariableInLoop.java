package fr.greencodeinitiative.java.checks;

public class AvoidDeclareVariableInLoop {
    public void initLoops(){
        for(int i = 0; i < 5; ++i){
            int x = i * 2; // Noncompliant
        }

        int x;
        for(int i = 0; i < 5; ++i){
            x = i * 2; // Compliant
        }
    }
}