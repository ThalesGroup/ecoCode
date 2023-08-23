import java.util.*;  // Noncompliant
import java.io.File; // Compliant

public class NoImportAllFromLibrary {
    public static void main(String[] args) {
        List<String> myList = new ArrayList<>(); // Just to use something from the imported package
        File myFile = new File("test.txt");      // Just to use something from the imported package
    }
}


