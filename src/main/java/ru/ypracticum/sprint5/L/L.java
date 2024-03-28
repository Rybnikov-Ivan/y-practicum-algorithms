package ru.ypracticum.sprint5.L;

public class L {
    public static int siftDown(int[] heap, int idx) {
        int len = heap.length;
        int left = 2 * idx;
        int right = 2 * idx + 1;

        if (left >= len) {
            return idx;
        }

        int largeIndex = left;
        if (right < len && heap[right] > heap[left]) {
            largeIndex = right;
        }

        if (heap[largeIndex] > heap[idx]) {
            int tmp = heap[idx];
            heap[idx] = heap[largeIndex];
            heap[largeIndex] = tmp;
            return siftDown(heap, largeIndex);
        } else {
            return idx;
        }
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        int i = siftDown(sample, 2);
        assert i == 5;
    }
}
