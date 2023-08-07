package fr.greencodeinitiative.java.checks;

public class UsePrimitiveType {

    int count; // Compliant
    double amount; // Compliant
    int[] tab; // Compliant

    Integer count1; // Noncompliant
    Double amount1; // Noncompliant
    List<String> names; // Noncompliant
}