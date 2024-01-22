package ru.ypracticum.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class F {
    private static boolean isPalindrome(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            char leftL = text.charAt(left);
            char rightL = text.charAt(right);

            if (!Character.isLetterOrDigit(leftL)) {
                left += 1;
            } else if (!Character.isLetterOrDigit(rightL)) {
                right -= 1;
            } else {
                if (Character.toLowerCase(leftL) != Character.toLowerCase(rightL)) {
                    return false;
                } else {
                    left += 1;
                    right -= 1;
                }
            }
        }

        return true;
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String text = reader.readLine();
            if (isPalindrome(text)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }
}
