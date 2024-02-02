package ru.ypracticum.sprint2.J;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class J {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int commandValue = readInt(reader); // количество комманд// размер очереди

            MyQueue myQueue = new MyQueue();

            for (int i = 0; i < commandValue; i++) {
                String[] command = reader.readLine().split(" ");
                switch (command[0]) {
                    case "put":
                        myQueue.push(Integer.parseInt(command[1]));
                        break;
                    case "get":
                        myQueue.get();
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

class MyQueue {
    LinkedList<Integer> items = new LinkedList<>();

    public void push(Integer elem) {
        items.add(elem);
    }

    public void size() {
        System.out.println(items.size());
    }

    public void get() {
        if (items.isEmpty()) {
            System.out.println("error");
        } else {
            System.out.println(items.removeFirst());
        }
    }
}
