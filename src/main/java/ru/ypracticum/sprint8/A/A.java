package ru.ypracticum.sprint8.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    private static String[] arr;

    private static void reverseString() {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            String tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left += 1;
            right -= 1;
        }
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            arr = reader.readLine().split(" ");

            reverseString();

            StringBuilder res = new StringBuilder();
            for (String s : arr) {
                res.append(s).append(" ");
            }

            System.out.println(res);
        }
    }
}
