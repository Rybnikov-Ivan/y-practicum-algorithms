package ru.ypracticum.sprint2.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class I {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int commandValue = readInt(reader); // количество комманд
            int maxSize = readInt(reader); // размер очереди

            Queue myQueue = new Queue(maxSize);

            for (int i = 0; i < commandValue; i++) {
                String[] command = reader.readLine().split(" ");
                switch (command[0]) {
                    case "push":
                        myQueue.push(Integer.parseInt(command[1]));
                        break;
                    case "pop":
                        myQueue.pop();
                        break;
                    case "peek":
                        myQueue.peek();
                        break;
                    default:
                        myQueue.size();
                        break;
                }
            }

        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}

class Queue {
    private final Integer[] queue;
    private int head;
    private int tail;
    private int size;
    private final int maxSize;


    public Queue(int maxSize) {
        this.queue = new Integer[maxSize];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.maxSize = maxSize;
    }

    public void push(int elem) {
        if (size != maxSize) {
            queue[tail] = elem;
            tail = (tail + 1) % maxSize;
            size += 1;
        } else {
            System.out.println("error");
        }
    }

    public void pop() {
        if (size == 0) {
            System.out.println("None");
        } else {
            Integer x = queue[head];
            queue[head] = null;
            head = (head + 1) % maxSize;
            size -= 1;
            System.out.println(x);
        }
    }

    public void peek() {
        if (size == 0) {
            System.out.println("None");
        } else {
            System.out.println(queue[head]);
        }
    }

    public void size() {
        System.out.println(size);
    }
}
