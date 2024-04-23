package ru.ypracticum.sprint6.finals.B;

/*
-- ПРИНЦИП РАБОТЫ --
В данной задаче я реализовал алгоритм поиска цикла в ориентированном графе при помози DFS

Для этого я воспользовался списком цвета вершин. Тут происходит обход в ширину и покраска посещенных вершин в серый цвет.
Если при проверке смежных вершин очередная вершина окажется серой — цикл есть.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Если граф представлен списками смежности,
то перебрать все смежные вершины можно за время, пропорциональное числу этих вершин.
Временная сложность составляет O(|E|+|V|), где |E| - количество ребер, |V| - количество вершин

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пространственная сложность составляет O(|V|), где V - количество вершин, которые буду добавлены в массив цветов.
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
import java.util.Stack;
import java.util.StringTokenizer;


// https://contest.yandex.ru/contest/25070/run-report/112486108/
public class B {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer tokenizer;
            int[] input = readArr(reader);
            int vertexes = input[0];

            Graph graph = new Graph(vertexes);

            for (int i = 0; i < vertexes - 1; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                char[] arr = tokenizer.nextToken().toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    graph.addEdge(i, j, arr[j]);
                }
            }

            for (int i = 0; i < graph.graph.size(); i++) {
                graph.dfs(i);
                if (graph.isCycle) {
                    break;
                }
            }
            graph.print();
        }
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static class Graph {
        private static final String NO = "NO";
        private static final String YES = "YES";
        private static final char B = 'B';
        private static final char R = 'R';
        private final Map<Integer, List<Integer>> graph;
        private final List<EColor> colors;
        private boolean isCycle;

        public Graph(int vertexes) {
            graph = new HashMap<>();

            for (int i = 0; i < vertexes; i++) {
                graph.put(i, new ArrayList<>());
            }

            isCycle = false;
            colors = new ArrayList<>(Collections.nCopies(vertexes, EColor.WHITE));
        }

        public void addEdge(int i, int j, char type) {
            int val = i + j + 1;
            if (type == B) {
                graph.get(i).add(val);
            } else if (type == R){
                graph.get(val).add(i);
            }
        }

        public void dfs(int startVertex) {
            Stack<Integer> stack = new Stack<>();
            stack.push(startVertex);

            while (!stack.isEmpty()) {
                Integer v = stack.pop();
                if (colors.get(v).equals(EColor.WHITE)) {
                    colors.set(v, EColor.GRAY);
                    stack.push(v);

                    for (int w : graph.get(v)) {
                        EColor color = colors.get(w);
                        if (color.equals(EColor.WHITE)) {
                            stack.push(w);
                        } else if (color.equals(EColor.GRAY)) {
                            isCycle = true;
                            break;
                        }
                    }
                } else if (colors.get(v).equals(EColor.GRAY)) {
                    colors.set(v, EColor.BLACK);
                }
            }
        }

        public void print() {
            if (isCycle) {
                System.out.println(NO);
            } else {
                System.out.println(YES);
            }
        }
    }

    enum EColor {
        WHITE,
        GRAY,
        BLACK
    }
}
