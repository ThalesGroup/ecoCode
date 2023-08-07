package fr.greencodeinitiative.java.checks;

public class UseRecordInsteadOfSetterOrGetter {
    private String name;
    private int age;

    public String getName(){ // Noncompliant
        return name;
    }

    public void setName(String name){ // Noncompliant
        this.name = name;
    }

    public int getAge(){ // Noncompliant
        return age;
    }

    public void setAge(int age){ // Noncompliant
        this.age = age;
    }

    public record Test(String s1, String s2, int n){ // Compliant
        // Records generate automatically getters and setters
    }
}

