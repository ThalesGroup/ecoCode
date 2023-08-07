package fr.greencodeinitiative.java.checks;
public class UseStaticVariables {

    public void someMethod(){
        int nonStaticVariable = 42; // Noncompliant {{Use static instead of nothing}}
        static int staticVarible = 42; // Compliant

    }

    public void anotherMethod(){
        String nonStaticVariable = "42"; // Noncompliant {{Use static instead of nothing}}
        static String staticVariable = "42"; // Compliant
    }


}