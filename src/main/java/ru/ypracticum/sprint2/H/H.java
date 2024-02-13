package ru.ypracticum.sprint2.H;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class H {

    private static boolean isTrueBracketLine(char[] chars) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                Character pop = stack.pop();
                if (pop == '(' && ch == ')') {
                    continue;
                } else if (pop == '[' && ch == ']') {
                    continue;
                } else if (pop == '{' && ch == '}') {
                    continue;
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            String ans = isTrueBracketLine(line.toCharArray()) ? "True" : "False";
            System.out.println(ans);
        }
    }
}
