package fr.greencodeinitiative.java.checks;

public class AvoidGetterAndSetter {

    public AvoidGetterAndSetter(int i, double j){
        this.i = i;
        this.j = j;
    }

    public void setI(int n){ // Noncompliant {{Avoid creating getter and setter methods in classes}}
        this.i = n;
    }

    public void setJ(int n){ // Noncompliant {{Avoid creating getter and setter methods in classes}}
        this.j = n;
    }

    public int getIAddFive() { // Noncompliant {{Avoid creating getter and setter methods in classes}}
        AvoidGetterAndSetter a = new AvoidGetterAndSetter();
        return a.getI();
    }

    public int getI(){  // Noncompliant {{Avoid creating getter and setter methods in classes}}
        return this.i;
    }

    public double getJ(){  // Noncompliant {{Avoid creating getter and setter methods in classes}}
        return this.j;
    }

    public boolean isPositive(){ // Compliant
        return this.i >= 0;
    }

}