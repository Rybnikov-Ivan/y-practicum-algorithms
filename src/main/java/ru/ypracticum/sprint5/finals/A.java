package ru.ypracticum.sprint5.finals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class A {

    static int n;
    static Heap heap;

    public static void main(String[] args) throws IOException {
        read();

        write();
    }

    private static void write() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            for (int i = 0; i < n; i++) {
                writer.write(heap.pop());
                writer.newLine();
            }
        }
    }

    private static void read() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = readInteger(reader);

            heap = new Heap();
            for (int i = 0; i < n; i++) {
                String[] arr = reader.readLine().split(" ");
                heap.add(new Participant(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2])));
            }
        }
    }

    private static int readInteger(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    static class Participant implements Comparable<Participant> {
        private final String login;
        private final Integer taskNumber;
        private final Integer fine;

        public Participant(String login, int taskNumber, int fine) {
            this.login = login;
            this.taskNumber = taskNumber;
            this.fine = fine;
        }

        @Override
        public int compareTo(Participant other) {
            if (!other.getTaskNumber().equals(this.getTaskNumber())) {
                return other.getTaskNumber().compareTo(this.getTaskNumber());
            }
            if (!other.getFine().equals(this.getFine())) {
                return this.getFine().compareTo(other.getFine());
            }
            return this.getLogin().compareTo(other.getLogin());
        }

        public String getLogin() {
            return login;
        }

        public Integer getTaskNumber() {
            return taskNumber;
        }

        public Integer getFine() {
            return fine;
        }
    }

    static class Heap {
        private final List<Participant> heap;

        public Heap() {
            this.heap = new ArrayList<>();
        }

        public void add(Participant elem) {
            int index = heap.size() + 1;
            heap.add(elem);
            siftUp(index);
        }

        private void siftUp(int index) {
            if (index == 1) {
                return;
            }
            int parentIndex = index / 2;
            if (heap.get(parentIndex - 1).compareTo(heap.get(index - 1)) > 0) {
                Participant temp = heap.get(parentIndex - 1);
                heap.set(parentIndex - 1, heap.get(index - 1));
                heap.set(index - 1, temp);
                siftUp(parentIndex);
            }
        }

        private void siftDown(int index) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;

            if (left > heap.size() - 1) {
                return;
            }

            int largeIndex = left;
            if (right < heap.size()) {
                if (heap.get(left).compareTo(heap.get(right)) > 0) {
                    largeIndex = right;
                }
            }

            if (heap.get(index).compareTo(heap.get(largeIndex)) > 0) {
                Participant tmp = heap.get(index);
                heap.set(index, heap.get(largeIndex));
                heap.set(largeIndex, tmp);
                siftDown(largeIndex);
            }
        }

        public String pop() {
            String pop = heap.get(0).getLogin();
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            siftDown(0);
            return pop;
        }
    }
}
