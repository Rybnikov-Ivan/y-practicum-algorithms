package ru.ypracticum.sprint2.finals.B;

/*
-- ПРИНЦИП РАБОТЫ --
В этой задаче я реализовал алгоритм расчета постфиксной записи выражения с помощью
стека.

При встрече в входной строке операнда, я кладу его в стек. При встрече оператора,
я достаю два операнда из стека и выполняю над ними операцию.

Необычно было встретится с целочисленным математическим делением, т.к. в Java обычное деление реализовано
не так, как требуется в задаче. Я воспользовался функцией Math.floorDiv

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Выходной стек хранит элементы в порядке, обратном тому,
в каком они пришли во входной.

Стек -- это порядок LIFO.
Стек инвертирует порядок элементов: первые становятся последними.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Добавление в стек стоит O(1).
Извлечение из стека стоит стоит так же O(1).
Весь алгоритм занимает О(n) времени, т.к. все элементы кладутся и извлекаются из стека 1 раз (для каждого).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Т.к. мы используем дополнительную память, которая выделяется под стек, то в худшем случае
пространственная сложность будет равна O(n)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

//https://contest.yandex.ru/contest/22781/run-report/106698916/
public class B {

    private static int getResult(List<String> list) {
        Stack<Integer> stack = new Stack<>();
        for (String value : list) {
            if (isNumber(value)) {
                stack.push(Integer.valueOf(value));
            } else {
                int secondNumber = stack.pop();
                int firstNumber = stack.pop();
                char operation = value.charAt(0);
                int resultOperation = executeOperation(firstNumber, secondNumber, operation);
                stack.push(resultOperation);
            }
        }

        return stack.pop();
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
            List<String> list = new ArrayList<>();
            while (tokenizer.hasMoreTokens()) {
                list.add(tokenizer.nextToken());
            }
            System.out.println(getResult(list));
        }
    }
}
