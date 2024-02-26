package ru.ypracticum.sprint3.finals;

/*
-- ПРИНЦИП РАБОТЫ --
В данной задачи я реализовал бинарный поиск на массиве, смещенным вправо на k элементов

Данный бинарный поиск отличается от обычного проверками крайних элементов вместо центрального mid.

В обычном бинарном поиске мы проверяем элемент с центральным индексом на равенство/неравенство элемента искомому числу.

В бинарном поиске на сломанном массиве перед этой проверкой проверяются границы указателей (левого и правого)

В случае если порядок не нарушен данная задача сводится к бинарному поиску на обычном (отсортированном подмассиве)

В обратном, мы ищем элемент на двух участках
-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность как и у обычного бинарного поиска равна O(logn), где n - количество элементов в массиве

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пространственная сложность определяется глубиной рекурсии. Так как каждый раз алгоритм будет рекрусивно вызываться на
половине передаваемого массива, то в стек вызовов может быть записано O(logm) дополнительных вызовов, где m - длина массива,

*/

// https://contest.yandex.ru/contest/23815/run-report/107812114/
public class A {
    public static int brokenSearch(int[] arr, int k) {
        return binarySearch(arr, k, 0, arr.length - 1);
    }

    private static int binarySearch(int[] arr, int k, int left, int right) {
        if (right < left) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == k) {
            return mid;
        }

        if (arr[left] < arr[right]) {
            if (k < arr[mid]) {
                return binarySearch(arr, k, left, mid - 1);
            } else {
                return binarySearch(arr, k, mid + 1, right);
            }
        } else {
            int lRes = binarySearch(arr, k, left, mid - 1);
            int rRes = binarySearch(arr, k, mid + 1, right);
            if (lRes == -1) {
                return lRes;
            } else {
                return rRes;
            }
        }
    }

    private static void test() {
        int[] arr = {1};
        int i = brokenSearch(arr, 1);
        System.out.println(i);
        assert 6 == brokenSearch(arr, 5);
    }

    public static void main(String[] args) {
        test();
    }
}
