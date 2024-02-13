package ru.ypracticum.sprint2.J;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class J {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int commandValue = readInt(reader); // количество комманд// размер очереди

            MyQueue myQueue = new MyQueue();

            for (int i = 0; i < commandValue; i++) {
                String[] command = reader.readLine().split(" ");
                switch (command[0]) {
                    case "put":
                        myQueue.put(Integer.parseInt(command[1]));
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
    Node<Integer> node;
    int size = 0;
    Node<Integer> head;

    public void put(Integer elem) {
        if (node == null) {
            node = new Node<>(elem, null, null);
            head = node;
        } else {
            node.next = new Node<>(elem, null, node);
            node = node.next;

        }
        size += 1;
    }

    public void size() {
        System.out.println(size);
    }

    public void get() {
        if (head == null) {
            System.out.println("error");
        } else {
            System.out.println(head.value);
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            size -= 1;
            if (size == 0) {
                node = null;
            }
        }
    }
}

class Node<V> {
    public V value;
    public Node<V> next;
    public Node<V> prev;

    public Node(V value, Node<V> next, Node<V> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}
