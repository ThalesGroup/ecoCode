public class AvoidCallingSystemGC {

    public void test(){
        System.gc(); // Noncompliant
        Runtime.getRuntime().gc(); // Compliant
        String s = "test";
        s = null;
        System.gc(); // Noncompliant
    }

    public void test2(){
        System.gc(); // Noncompliant
        //Some other code ...
    }
}