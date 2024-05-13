package ru.ypracticum.sprint8.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {

    private static boolean isOk(String firstLine, String secondLine) {
        String biggest = firstLine.length() > secondLine.length() ? firstLine : secondLine;
        String smallest = firstLine.length() <= secondLine.length() ? firstLine : secondLine;

        if (biggest.length() - smallest.length() > 1) {
            return false;
        }

        int diff = 0;
        if (biggest.length() == smallest.length()) {
            for (int i = 0; i < biggest.length(); i++) {
                if (Character.toLowerCase(biggest.charAt(i)) != Character.toLowerCase(smallest.charAt(i))) {
                    diff += 1;
                }
            }

        } else {
            for (int i = 0; i < smallest.length(); i++) {
                if (diff > 1) {
                    break;
                }
                if (Character.toLowerCase(smallest.charAt(i)) != Character.toLowerCase(biggest.charAt(i + diff))) {
                    diff += 1;
                }
            }
        }
        return diff <= 1;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String firstLine = reader.readLine();
            String secondLine = reader.readLine();

            if (isOk(firstLine, secondLine)) {
                System.out.println("OK");
            } else {
                System.out.println("FAIL");
            }
        }
    }
}
