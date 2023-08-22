package fr.greencodeinitiative.java.checks;

import java.util.List;

public class AvoidDeclareVariableInLoop {
    public void initLoops() {
        // For loop
        for (int i = 0; i < 5; ++i) {
            int x = i * 2; // Noncompliant {{Avoid declare variables in loops}}
        }

        int x;
        for (int i = 0; i < 5; ++i) {
            x = i * 2; // Compliant
        }

        // While loop
        int j = 0;
        while (j < 5) {
            int y = j * 3; // Noncompliant {{Avoid declare variables in loops}}
            j++;
        }

        int y;
        while (j < 5) {
            y = j * 3; // Compliant
            j++;
        }

        // Do-while loop
        int k = 0;
        do {
            int z = k * 4; // Noncompliant {{Avoid declare variables in loops}}
            k++;
        } while (k < 5);

        int z;
        do {
            z = k * 4; // Compliant
            k++;
        } while (k < 5);

        // For-each loop
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        for (int num : numbers) {
            int result = num * 5; // Noncompliant {{Avoid declare variables in loops}}
        }

        int result;
        for (int num : numbers) {
            result = num * 5; // Compliant
        }
    }
}
