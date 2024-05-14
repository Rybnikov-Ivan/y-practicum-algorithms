package ru.ypracticum.sprint8.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G {
    private static String getIndexes(int[] xi, int[] ai) {
        StringBuilder res = new StringBuilder();
        int[] templates = getTemplatesNumber(ai);

        for (int i = 0; i <= xi.length - ai.length; i++) {
            boolean match = true;
            for (int j = 0; j < templates.length; j++) {
                if (Math.abs(xi[j + i] - xi[j + i + 1]) != templates[j]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                res.append(i + 1).append(" ");
            }
        }

        return res.toString();
    }

    private static int[] getTemplatesNumber(int[] ai) {
        int[] templates = new int[ai.length - 1];

        for (int i = 0; i < ai.length; i++) {
            if (i + 1 != ai.length)
                templates[i] = Math.abs(ai[i] - ai[i + 1]);
        }

        return templates;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInteger(reader);
            int[] xi = readArr(reader);
            int m = readInteger(reader);
            int[] ai = readArr(reader);

            System.out.println(getIndexes(xi, ai));
        }
    }

    private static int readInteger(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
