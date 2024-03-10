package ru.ypracticum.sprint4.finals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/*
-- ПРИНЦИП РАБОТЫ --
В данной задачи я реализовал хеш таблицу с функцией разрешения колизий методом цепочек.

В качестве размеров хеш таблицы выступают простые числа, актуальный размер я вычисляю при инициализации.
Вычисление хэша ключа занимается функция hashCode(), номер корзины определяется по хэш % размер таблицы
-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Оснонвые операции выполняются за О(1). Время выполнения програм зависит от количество команд (n)

О(1) - это время выполнения команд в лучшем случае, при отсутсвии коллизий. В худшем случае, время основных команд ухудшается
до О(n). Это связано с колизиями. Т.к. мы разрешаем их методом цепочек, то например при операции удалении, нам придется пройти
по всем элементам в корзине.

В каждую ячейку хеш таблицы мы можем попасть с вероятностью в 1/m, где m - количество корзин в таблице. Т.к. у нас k
элементов, то средняя сложность основных команд, будет составлять О(1 + k/m) или О(1 + alpha) {alpha = k/m}

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Для хранения состояния словаря я использую динамический массив. Временная сложность равна O(m), где m - количесво элементов в массиве
*/

// https://contest.yandex.ru/contest/24414/run-report/108835491/
public class B {
    private static final String GET = "get";
    private static final String PUT = "put";
    private static final String DELETE = "delete";
    private static final String BAD_RESPONSE = "None";
    private static int n;
    private static List<String[]> responses;

    public static void main(String[] args) throws IOException {
        read();

        write(responses);
    }

    private static void write(List<String[]> responses) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            Dictionary<Integer, Integer> dictionary = new Dictionary<>(n);
            for (String[] response : responses) {
                String command = response[0];
                Integer key = Integer.valueOf(response[1]);

                Integer res;
                switch (command) {
                    case PUT:
                        int value = Integer.parseInt(response[2]);
                        dictionary.put(key, value);
                        break;
                    case GET:
                        res = dictionary.get(key);
                        if (res == null) {
                            writer.write(BAD_RESPONSE);
                        } else {
                            writer.write(String.valueOf(res));
                        }
                        writer.newLine();
                        break;
                    case DELETE:
                        res = dictionary.delete(key);
                        if (res == null) {
                            writer.write(BAD_RESPONSE);
                        } else {
                            writer.write(String.valueOf(res));
                        }
                        writer.newLine();
                        break;
                }

            }
        }
    }

    private static void read() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            n = readInteger(reader);
            responses = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                responses.add(readArr(reader));
            }
        }
    }

    private static int readInteger(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static String[] readArr(BufferedReader reader) throws IOException {
        return reader.readLine().split(" ");
    }
}

class Dictionary<K, V> {
    private final int size;
    private final List<Entry<K, V>> buckets;

    public Dictionary(Integer size) {
        this.size = getPrime(size);
        this.buckets = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            buckets.add(null);
        }
    }

    public V put(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value);
        int bucket = getBucket(key);

        Entry<K, V> current = buckets.get(bucket);
        if (current == null) {
            buckets.set(bucket, entry);
            return null;
        }

        if (current.getKey().equals(key)) {
            V res = current.getValue();
            current.setValue(value);
            return res;
        }

        Entry<K, V> next = current.getNext();

        while (next != null) {
            if (next.getKey().equals(key)) {
                V res = next.getValue();
                next.setValue(value);
                return res;
            }
            current = next;
            next = next.getNext();
        }
        current.setNext(entry);
        return null;
    }

    public V get(K key) {
        int bucket = getBucket(key);
        Entry<K, V> current = buckets.get(bucket);
        if (current != null) {
            do {
                if (current.getKey().equals(key)) {
                    return current.getValue();
                }
                current = current.getNext();
            } while (current != null);
        }
        return null;

    }

    public V delete(K key) {
        int bucket = getBucket(key);
        Entry<K, V> current = buckets.get(bucket);
        if (current == null) {
            return null;
        }

        if (current.getKey().equals(key)) {
            V res = current.getValue();
            buckets.set(bucket, current.getNext());
            return res;
        }

        Entry<K, V> next = current.getNext();

        while (next != null) {
            if (next.getKey().equals(key)) {
                V res = next.getValue();
                current.setNext(next.getNext());
                return res;
            }
            current = next;
            next = next.getNext();
        }
        return null;
    }

    private int getHash(K key) {
        return Math.abs(key.hashCode());
    }

    private int getBucket(K key) {
        return getHash(key) % size;
    }

    private int getPrime(int size) {
        if (size <= 2) {
            return 2;
        }

        for (int i = size; i >= 2; i--) {
            if(isPrime(i)){
                return i;
            }
        }

        return size;
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        int prime = 2;
        while (prime * prime <= n) {
            if (n % prime == 0) {
                return false;
            }
            prime += 1;
        }

        return true;
    }
}

class Entry<K, V> {
    private final K key;
    private V value;
    private Entry<K, V> next;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Entry<K, V> getNext() {
        return next;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setNext(Entry<K, V> next) {
        this.next = next;
    }
}
