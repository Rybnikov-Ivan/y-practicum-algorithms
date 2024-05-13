package ru.ypracticum.sprint7.finals.A;

/*
-- ПРИНЦИП РАБОТЫ --
В данной задаче я реализовал алгоритм Алгоритм Вагнера — Фишера для нахождения расстояния Левешнштейна

В данном алгоритме формируется двумерный массив, где по оси x - находится первая строка (индексы - символы в строке)
А по y - вторая. Количество индексов + 1 = количество символов.

Далее я заполняю 0 колонку и столбец числами от 1 до длины строки.

Решаю задачу алгоритмом Вагнера - Фишера.
Сравниваю поэлементно символы из двух строк и в зависимости от этого вычисляю расстояние.

Расстояние же высчитывается в зависимости от равенства символов:
- Если символы не равны, высчитывается минимальное значение из dp[i-1][j] + 1, dp[i][j-1] + 1, dp[i-1][j-1] + 1
- Если символы равны, высчитывается минимальное значение из dp[i-1][j] + 1, dp[i][j-1] + 1, dp[i-1][j-1]

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность составляет O(n*m), где n - длина первой строки, m - длина второй строки

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пространственная сложность составляет O(n * m), где n - длина первой строки, m - длина второй строки
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://contest.yandex.ru/contest/25597/run-report/113621935/
public class A {
    private static String fString;
    private static String sString;
    private static int[] fDp;
    private static int[] sDp;

    private static int getDistance() {
        fillDP();

        for (int i = 1; i <= fString.length(); i++) {
            for (int j = 0; j < sDp.length; j++) {
                fDp[j] = sDp[j];
            }

            sDp[0] = i;

            for (int j = 1; j <= sString.length(); j++) {
                if (fString.charAt(i - 1) != sString.charAt(j - 1)) {
                    sDp[j] = Math.min(Math.min(fDp[j] + 1, sDp[j - 1] + 1), fDp[j - 1] + 1);
                } else {
                    sDp[j] = Math.min(Math.min(fDp[j] + 1, sDp[j - 1] + 1), fDp[j - 1]);
                }
            }
        }
        return sDp[sDp.length - 1];
    }

    private static void fillDP() {
        int m = sString.length() + 1;
        fDp = new int[m];
        sDp = new int[m];

        for (int i = 1; i < m; i++) {
            sDp[i] = i;
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        int res = getDistance();

        output(res);
    }

    private static void output(int res) {
        System.out.println(res);
    }

    private static void input() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            fString = reader.readLine();
            sString = reader.readLine();
        }
    }
}
