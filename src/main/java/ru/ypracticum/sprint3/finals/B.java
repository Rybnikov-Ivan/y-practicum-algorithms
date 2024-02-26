package ru.ypracticum.sprint3.finals;

/*
-- ПРИНЦИП РАБОТЫ --
В данной задаче я реализовал эффективную сортировку

По началу я считываю данные с входного потока и вместо массива, мапю их на объект класса участника (Participant)
Данный класс имеет 3 атрибута как сказано в условии - Количество задач, Штраф и Логин

Далее я переопределил метод компаратора, чтоб он сравнивал объекты данного класса по 3-м атрибутам исходя из условия задачи

Сама эффективная быстрая сортировка отличается отсутствием дополнительной памяти для хранения состояний разделенных массивов
В сигнатуру метода передается 2 указателя - левый (указывающий на начальный индекс массива) и правый (указывающий на конец)

Далее вычисляется средний индекс по стандартной формуле и разделяет массив на 2 части.

Левый указатель двигается вправо до тех пор, пока элементы ниже (по compareTo) опорного, правый наоборот до тех пор, пока выше.

Когда движение указателей прекращается, элементы, на которых указатели остановились меняются местами.

Такой алгоритм повторяется до тех пор, пока левый и правый указатели не столкнутся
-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность определяется в сортировке только циклами. Методы перестановки элементов (swap) и сравнения (compareTo)
имеют временную сложность О(1).

Эфектиная сортировка отличается от обычной быстрой лишь отсутствием памяти.
Поэтому у временная сложность у моего алгоритма в среднем O(nlogn) и в худшем О(n^2).

Худший случай может возникнуть при ситуации, когда массив уже отсортирован и элемент pivot выбран первым в массиве.

Однако, если в том же отсортированном массиве выбрать в качестве опорного элемента элемент из середины массива.
То временная сложность алгоритма сведется к O(nlogn), где n - количество элементов в массиве

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Т.к. я реализовал эффективную сортировку без дополнительной памяти для хранении разделенных массивов,
временная сложность зависит от входного параметра - количества участников. O(m), где m - количество участников
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// https://contest.yandex.ru/contest/23815/run-report/107925806/
public class B {
    private static void effectiveQuickSort(Participant[] participants, int left, int right) {
        if (right <= left || participants.length == 0) {
            return;
        }

        Participant pivot = participants[(left + right) / 2];

        int l = left;
        int r = right;

        while (l <= r) {

            while (participants[l].compareTo(pivot) > 0) {
                l += 1;
            }

            while (participants[r].compareTo(pivot) < 0) {
                r -= 1;
            }

            if (l <= r) {
                swap(participants, l++, r--);
            }
        }

        if (right > l) {
            effectiveQuickSort(participants, l, right);
        }

        if (left < r) {
            effectiveQuickSort(participants, left, r);
        }
    }

    private static void swap(Participant[] participants, int l, int r) {
        Participant buf = participants[l];
        participants[l] = participants[r];
        participants[r] = buf;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            Participant[] participants = new Participant[n];
            for (int i = 0; i < n; i++) {
                participants[i] = readParticipant(reader);
            }

            effectiveQuickSort(participants, 0, participants.length - 1);

            for (Participant participant : participants) {
                writer.write(participant.getLogin());
                writer.newLine();
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static Participant readParticipant(BufferedReader reader) throws IOException {
        String[] line = reader.readLine().split(" ");
        return new Participant(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]));
    }
}

class Participant implements Comparable<Participant> {
    private final String login;
    private final int taskNumber;
    private final int fine;

    public Participant(String login, int taskNumber, int fine) {
        this.login = login;
        this.taskNumber = taskNumber;
        this.fine = fine;
    }

    public String getLogin() {
        return login;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public int getFine() {
        return fine;
    }

    @Override
    public int compareTo(Participant other) {
        int taskNumber = Integer.compare(this.getTaskNumber(), other.getTaskNumber());
        if (taskNumber == 0) {
            int fine = Integer.compare(this.getFine(), other.getFine());
            if (fine == 0) {
                return -this.getLogin().compareTo(other.getLogin());
            } else {
                return -fine;
            }
        } else {
            return taskNumber;
        }
    }
}
