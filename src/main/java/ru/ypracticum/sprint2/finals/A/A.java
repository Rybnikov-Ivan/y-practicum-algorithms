package ru.ypracticum.sprint2.finals.A;

/*
-- ПРИНЦИП РАБОТЫ --
В этой задаче я реализовал деку с использованием массива целочисленного типа и двух указателей (head и tail).
При реализации деки, был использован принцип буферного кольца.

Изначально головной индекс указывает на 0, хвостовой индекс - указывает на 1.

При добавлении элемента в начало (push_front) мы устанавливаем в ячейку с индексом head элемент и проверяем,
что головной индекс не равен 0. В случае равенства, head переносится в концец массива, указывая на последнюю ячейку.
Если равенства нет, мы уменьшаем head на 1.

При добавлении элемента в конец (push_back) происходит аналогичные, полностью противоположные действия.
Проверяем на равенство хвостового индекса индексу последней ячейки. И устанавливаем значение в зависимости от равенства.

При добавлении реальный размер массива (количество записанных элементов в ячейки, без учета ячеек  null)
увеличиваетсяс на 1.

Для исключения перезаписывания элементов, мы проверяем реальный размер массива с максимальным.

При выдачи элемента, совершаются полностью обратные действия и проверки. Проверяется что индекс не равен
граничному индексу массива (0, size - 1). Индексы устанавливаются в зависимости от операции.

Реальный размер массива уменьшается на 1.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Основные операции push_back, push_front, pop_back, pop_front выполняются за O(1)

Временная сложность зависит от входного параметра - количества команд. O(n), где n - количество команд

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пространственная сложность зависит от максимального размера массива, хранящегося в деке. Поэтому O(m),
где m - размер массива.
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://contest.yandex.ru/contest/22781/run-report/106519175/
public class A {
    private static final String ERROR = "error";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer tokenizer;
            int commandValue = readInt(reader); // количество комманд
            int maxSize = readInt(reader); // размер деки

            Deque deque = new Deque(maxSize);

            for (int i = 0; i < commandValue; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                Integer value;
                switch (tokenizer.nextToken()) {
                    case "push_front":
                        if (!deque.pushFront(Integer.parseInt(tokenizer.nextToken()))) {
                            printError(writer);
                        }
                        break;
                    case "pop_front":
                        value = deque.popFront();
                        if (value != null) {
                            printResult(writer, value);
                        } else {
                            printError(writer);
                        }
                        break;
                    case "push_back":
                        if (!deque.pushBack(Integer.parseInt(tokenizer.nextToken()))) {
                            printError(writer);
                        }
                        break;
                    case "pop_back":
                        value = deque.popBack();
                        if (value != null) {
                            printResult(writer, value);
                        } else {
                            printError(writer);
                        }
                        break;
                }
            }
        }
    }

    private static void printResult(BufferedWriter writer, int value) throws IOException {
        writer.write(String.valueOf(value));
        writer.newLine();
    }

    private static void printError(BufferedWriter writer) throws IOException {
        writer.write(ERROR);
        writer.newLine();
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}

class Deque {
    private int head;
    private int tail;
    int size;
    int maxSize;
    Integer[] deque;

    public Deque(int maxSize) {
        this.maxSize = maxSize;
        deque = new Integer[maxSize];
        head = 0;
        tail = 1;
        size = 0;
    }

    public boolean pushFront(Integer elem) {
        if (size >= maxSize) {
            return false;
        } else {
            deque[head] = elem;
            head = head == 0 ? maxSize - 1 : head - 1;
            size += 1;
            return true;
        }
    }

    public boolean pushBack(Integer elem) {
        if (size >= maxSize) {
            return false;
        } else {
            deque[tail] = elem;
            tail = (tail + 1) == maxSize ? 0 : tail + 1;
            size += 1;
            return true;
        }
    }

    public Integer popFront() {
        if (size <= 0) {
            return null;
        } else {
            head = (head + 1) >= maxSize ? 0 : head + 1;
            Integer result = deque[head];
            deque[head] = null;
            size -= 1;
            return result;
        }
    }

    public Integer popBack() {
        if (size <= 0) {
            return null;
        } else {
            tail = (tail - 1) < 0 ? maxSize - 1 : tail - 1;
            Integer result = deque[tail];
            deque[tail] = null;
            size -= 1;
            return result;
        }
    }
}
