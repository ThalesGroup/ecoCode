package fr.greencodeinitiative.java.checks;

public class UseOfMethodsForBasicOperations {
    public void testMin(){
        Math.min(4,2);  // Noncompliant {{Use of methods for basic operations}}
        int result = minimum(4,2);
        int result2 = minWithAutoImplement(4,2);
    }

    public int minimum(int a, int b){
        return Math.min(a,b); // Noncompliant {{Use of methods for basic operations}}
    }

    public int minWithAutoImplement(int a, int b){
        return a < b ? a : b; // Compliant
    }
}