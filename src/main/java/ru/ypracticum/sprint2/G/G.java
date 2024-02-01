package ru.ypracticum.sprint2.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;

public class G {
    public static void main(String[] args) throws IOException {
        Stack myStack = new Stack();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);

            for (int i = 0; i < n; i++) {
                String[] request = reader.readLine().split(" ");
                if (request.length == 2) {
                    myStack.push(Integer.parseInt(request[1]));
                } else {
                    if (request[0].equals("get_max")) {
                        System.out.println(myStack.getMax());
                    } else if (request[0].equals("pop")){
                        if (myStack.pop().equals("error")) {
                            System.out.println("error");
                        }
                    } else {
                        System.out.println(myStack.top());
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
    LinkedList<Integer> items = new LinkedList<>();
    LinkedList<Integer> itemsMax = new LinkedList<>();

    public Integer push(Integer elem) {
        items.push(elem);
        if (itemsMax.isEmpty() || itemsMax.getFirst() <= elem) {
            itemsMax.push(elem);
        }

        return elem;
    }

    public String pop() {
        if (items.isEmpty()) {
            return "error";
        } else {
            Integer remove = items.pop();
            if (!itemsMax.isEmpty() && Objects.equals(remove, itemsMax.getFirst())) {
                itemsMax.pop();
            }
            return String.valueOf(remove);
        }
    }

    public String top() {
        return items.isEmpty() ? "error" : String.valueOf(items.getFirst());
    }

    public String getMax() {
        return items.isEmpty() ? "None" : String.valueOf(itemsMax.getFirst());
    }
}
