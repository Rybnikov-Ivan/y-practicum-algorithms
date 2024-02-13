package ru.ypracticum.sprint2.F;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class F {
    public static void main(String[] args) throws IOException {
        Stack myStack = new Stack();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            for (int i = 0; i < n; i++) {
                String[] request = reader.readLine().split(" ");
                if (request.length == 2) {
                    myStack.push(Integer.parseInt(request[1]));
                } else {
                    if (request[0].equals("get_max")) {
                        System.out.println(myStack.getMax());
                    } else {
                        if (myStack.pop().equals("error")) {
                            System.out.println("error");
                        }
                    }
                }
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}

class Stack {
    List<Integer> items = new ArrayList<>();
    int max = Integer.MIN_VALUE;

    public Integer push(Integer elem) {
        max = Math.max(max, elem);
        items.add(elem);
        return elem;
    }

    public String pop() {
        if (items.isEmpty()) {
            return "error";
        } else {
            Integer remove = items.remove(items.size() - 1);
            if (remove == max) {
                max = Integer.MIN_VALUE;
                for (Integer item : items) {
                    max = Math.max(item, max);
                }

            }
            return String.valueOf(remove);
        }
    }

    public String getMax() {
        if (items.isEmpty()) {
            return "None";
        } else {
            return String.valueOf(max);
        }
    }
}
