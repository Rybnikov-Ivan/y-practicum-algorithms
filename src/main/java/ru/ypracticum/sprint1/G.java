package ru.ypracticum.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G {
    private static String getBinaryNumber(int n) {
        StringBuilder sb = new StringBuilder();
        if (n == 0) {
            sb.append(0);
        }
        while (n != 0) {
            if (n % 2 == 1) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            n /= 2;
        }

        return sb.reverse().toString();
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            System.out.println(getBinaryNumber(n));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
