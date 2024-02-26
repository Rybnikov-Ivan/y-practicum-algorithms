package ru.ypracticum.sprint3.K;

import java.util.Arrays;

public class K {

    public static int[] merge(int[] arr, int left, int mid, int right) {
        if (arr.length == 1) {
            return arr;
        }

        int[] result = new int[arr.length];
        int l = left;
        int r = mid;
        int k = 0;

        while (l < mid && r < right) {
            if (arr[l] <= arr[r]) {
                result[k] = arr[l];
                l += 1;
            } else {
                result[k] = arr[r];
                r += 1;
            }
            k += 1;
        }

        while (l < mid) {
            result[k] = arr[l];
            k += 1;
            l += 1;
        }

        while (r < right) {
            result[k] = arr[r];
            k += 1;
            r += 1;
        }

        return result;
    }

    public static void merge_sort(int[] arr, int left, int right) {
        if (right - left <= 1) {
            return;
        }
        int mid = (left + right) / 2;

        int[] leftArr = Arrays.copyOfRange(arr, left, mid);
        int[] rightArr = Arrays.copyOfRange(arr, mid, right);

        merge_sort(leftArr, 0, leftArr.length);
        merge_sort(rightArr, 0, rightArr.length);

        System.arraycopy(leftArr, 0, arr, 0, leftArr.length);
        System.arraycopy(rightArr, 0, arr, leftArr.length, rightArr.length);

        int[] res = merge(arr, left, mid, right);
        if (right - left >= 0) System.arraycopy(res, left, arr, left, right - left);
    }

    public static void main(String[] args) {
        int[] a = {1, 4, 9, 2, 10, 11};
        int[] b = merge(a, 0, 3, 6);
        int[] expected = {1, 2, 4, 9, 10, 11};
        assert Arrays.equals(b, expected);
        int[] c = {1, 4, 2, 10, 1, 2};
        merge_sort(c, 0, 6);
        int[] expected2 = {1, 1, 2, 2, 4, 10};
        assert Arrays.equals(c, expected2);
    }
}
