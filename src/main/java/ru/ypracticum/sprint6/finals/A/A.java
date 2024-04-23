package ru.ypracticum.sprint6.finals.A;

/*
-- ПРИНЦИП РАБОТЫ --
В данной задаче я реализовал алгоритм Прима (поиск максимального остовного дерева) на куче

В начале я заполняю список смежности неориентированного графа входными значениями.

Алгоритм поиска следующий:
Я Рассматриваю все ребра исходящие из вершины (начинать не важно с какой, т.к в итоге все вершины попадут в максимальное остовное дерево)
и беру ребро с максимальным весом. Добавляю в остов ребро и вершину, в котороую оно входило.

Если хранить рёбра, исходящие из уже собранного подмножества остова в куче максимума,
то выбирать ребро с максимальным весом легко.

Если вместе с ребром в подграф добавляется новая вершина, то это ребро добавляется в остов.
Если ребро соединяет две вершины, уже присутствующее в подмножестве остова,
мы отбрасываем его из дальнейшего рассмотрения и из кучи в том числе.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность составляет O(|E|*log|V|), где |E| - количество ребер, |V| - количество вершин
Логарифм возникает благодаря куче, т.к. просеивание в куче оценивается именно логарифмом

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пространственная сложность составляет O(|V|), где V - количество вершин, которые буду добавлены в кучу
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// https://contest.yandex.ru/contest/25070/run-report/112485571/
public class A {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer tokenizer;
            int[] input = readArr(reader);
            int vertexes = input[0];
            int edges = input[1];

            Graph graph = new Graph(vertexes + 1);

            for (int i = 0; i < edges; i++) {
                tokenizer = new StringTokenizer(reader.readLine());

                int v = Integer.parseInt(tokenizer.nextToken());
                int u = Integer.parseInt(tokenizer.nextToken());
                int weight = Integer.parseInt(tokenizer.nextToken());
                graph.addEdge(v, u, weight);
            }

            graph.findMST();
            graph.print();
        }
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static class Graph {
        private static final String NO_TREE = "Oops! I did it again";
        private final Map<Integer, List<Vertex>> graph;
        private final List<Vertex> MST; // Максимальное остовное дерево
        private final List<Boolean> notAdded; // Множество вершин, ещё не добавленных в остов.
        private final Queue<Vertex> edges; // Массив рёбер, исходящих из остовного дерева.

        public Graph(int vertexes) {
            graph = new HashMap<>();

            for (int i = 1; i <= vertexes; i++) {
                graph.put(i, new ArrayList<>());
            }

            this.MST = new ArrayList<>();
            this.notAdded = new ArrayList<>(Collections.nCopies(vertexes, false));
            this.edges = new PriorityQueue<>();
        }

        public void addEdge(int from, int to, int weight) {
            graph.get(from).add(new Vertex(to, weight));
            graph.get(to).add(new Vertex(from, weight));
        }

        public void findMST() {
            edges.add(new Vertex(1, 0));
            while (!edges.isEmpty()) {
                Vertex maximum = getMaximum();
                int v = maximum.getV();
                if (notAdded.get(v)) {
                    continue;
                }

                int weight = maximum.getWeight();
                MST.add(new Vertex(v, weight));

                notAdded.set(v, true);
                for (Vertex vertex : graph.get(v)) {
                    if (!notAdded.get(vertex.v)) {
                        edges.add(new Vertex(vertex.getV(), vertex.getWeight()));
                    }
                }
            }
        }

        private Vertex getMaximum() {
            return edges.poll();
        }

        public void print() {
            int countVertex = graph.size();
            long countVisited = notAdded.stream()
                    .filter(o -> o)
                    .count();

            if (countVertex - 1 != countVisited) {
                System.out.println(NO_TREE);
            } else {
                int sum = MST.stream().mapToInt(Vertex::getWeight).sum();
                System.out.println(sum);
            }
        }
    }

    private static class Vertex implements Comparable<Vertex> {
        Integer v;
        Integer weight;

        public Vertex(Integer v, Integer weight) {
            this.v = v;
            this.weight = weight;
        }

        public Integer getV() {
            return v;
        }

        public Integer getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return -this.getWeight().compareTo(o.getWeight());
        }
    }
}
