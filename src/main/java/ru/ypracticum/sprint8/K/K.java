package ru.ypracticum.sprint8.K;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class K {

    private static int isEqual(String str1, String str2) {
        List<Character> list1 = new ArrayList<>();
        List<Character> list2 = new ArrayList<>();

        for (int i = 0; i < str1.length(); i++) {
            int val = str1.charAt(i);
            if (val % 2 == 0) {
                list1.add(str1.charAt(i));
            }
        }

        for (int i = 0; i < str2.length(); i++) {
            int val = str2.charAt(i);
            if (val % 2 == 0) {
                list2.add(str2.charAt(i));
            }
        }

        for (int i = 0; i < Math.max(list1.size(), list2.size()); i++) {
            char val1 = i < list1.size() ? list1.get(i) : Character.MIN_VALUE;
            char val2 = i < list2.size() ? list2.get(i) : Character.MIN_VALUE;
            if (val1 == val2) {
                continue;
            } else if (val1 < val2) {
                return -1;
            } else {
                return 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String firstStr = reader.readLine();
            String secondStr = reader.readLine();

            System.out.println(isEqual(firstStr, secondStr));
        }
    }
}
