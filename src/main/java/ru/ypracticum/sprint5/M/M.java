package ru.ypracticum.sprint5.M;

public class M {
    public static void main(String[] args) {
        test();
    }
    public static int siftUp(int[] heap, int idx) {
        int parentIndex = idx / 2;

        if (idx == 1) {
            return idx;
        }

        if (heap[idx] > heap[parentIndex]) {
            int tmp = heap[idx];
            heap[idx] = heap[parentIndex];
            heap[parentIndex] = tmp;
            return siftUp(heap, parentIndex);
        } else {
            return idx;
        }
    }

    private static void test() {
        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        int i = siftUp(sample, 5);
        assert i == 1;
    }
}
