package ru.ypracticum.sprint2.finals.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B {

    private static int getResult(StringTokenizer tokenizer) {
        Stack<String> stack = new Stack<>();
        while (tokenizer.hasMoreTokens()) {
            String value = tokenizer.nextToken();
            if (isNumber(value)) {
                stack.push(value);
            } else {
                int secondNumber = Integer.parseInt(stack.pop());
                int firstNumber = Integer.parseInt(stack.pop());
                char operation = value.charAt(0);
                int resultOperation = executeOperation(firstNumber, secondNumber, operation);
                stack.push(String.valueOf(resultOperation));
            }
        }

        return Integer.parseInt(stack.pop());
    }

    private static boolean isNumber(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private static int executeOperation(int firstNumber, int secondNumber, char operation) {
        switch (operation) {
            case '+':
                return firstNumber + secondNumber;
            case '-':
                return firstNumber - secondNumber;
            case '*':
                return firstNumber * secondNumber;
            case '/':
                return Math.floorDiv(firstNumber, secondNumber);
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            System.out.println(getResult(tokenizer));
        }
    }
}
