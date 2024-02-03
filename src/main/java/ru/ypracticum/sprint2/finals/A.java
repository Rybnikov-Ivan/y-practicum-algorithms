package ru.ypracticum.sprint2.finals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

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
                switch (tokenizer.nextToken()) {
                    case "push_front":
                        if (!deque.pushFront(Integer.parseInt(tokenizer.nextToken()))) {
                            printError(writer);
                        }
                        break;
                    case "pop_front":
                        printResult(writer, deque.popFront());
                        break;
                    case "push_back":
                        if (!deque.pushBack(Integer.parseInt(tokenizer.nextToken()))) {
                            printError(writer);
                        }
                        break;
                    case "pop_back":
                        printResult(writer, deque.popBack());
                        break;
                }
            }
        }
    }

    private static void printResult(BufferedWriter writer, String value) throws IOException {
        writer.write(value);
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

    public String popFront() {
        if (size <= 0) {
            return "error";
        } else {
            head = (head + 1) >= maxSize ? 0 : head + 1;
            Integer result = deque[head];
            deque[head] = null;
            size -= 1;
            return String.valueOf(result);
        }
    }

    public String popBack() {
        if (size <= 0) {
            return "error";
        } else {
            tail = (tail - 1) < 0 ? maxSize - 1 : tail - 1;
            Integer result = deque[tail];
            deque[tail] = null;
            size -= 1;
            return String.valueOf(result);
        }
    }
}
