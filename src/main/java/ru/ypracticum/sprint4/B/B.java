package ru.ypracticum.sprint4.B;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class B {
    private static final int a = 1_000;
    private static final int m = 123_987_123;

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxwz";

    private static void getHash() {
        Map<Integer, String> map = new HashMap<>();

        Random random = new Random();
        while (true) {
            String generator = "";

            for (int i = 0; i < 26; i++) {
                generator = generator.concat(String.valueOf(ALPHABET.charAt(random.nextInt(26))));
            }

            int hash = getHash(a, m, generator);
            if (map.containsKey(hash)) {
                System.out.println(generator);
                System.out.println(map.get(hash));

                break;
            } else {
                map.put(hash, generator);
            }
        }
    }

    private static int getHash(long a, long m, String s) {
        long res = 0;

        int n = s.length();
        for (int i = 0; i < n; i++) {
            res = res * a % m + s.charAt(i);
        }

        return (int) (res % m);
    }

    public static void main(String[] args) throws IOException {
        getHash();
    }

}
