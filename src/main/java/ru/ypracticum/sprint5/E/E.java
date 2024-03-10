package ru.ypracticum.sprint5.E;

import java.util.HashSet;
import java.util.Set;

public class E {
    private static int rootValue;
    private static boolean isBinaryTree(Set<Integer> set, Node head) {
        if (head == null) {
            return true;
        }

        if (head.left != null && head.right != null) {
            int left = head.left.value;
            int right = head.right.value;

            if (head.value != rootValue) {
                if (head.value < rootValue) {
                    if (left > rootValue || right > rootValue) {
                        return false;
                    }
                } else {
                    if (left < rootValue || right < rootValue) {
                        return false;
                    }
                }
            }

            if (left > right || left > head.value || right < head.value) {
                return false;
            }
        } else if (head.right != null){
            int right = head.right.value;
            if (right < head.value) {
                return false;
            }
        } else if (head.left != null) {
            int left = head.left.value;
            if (left > head.value) {
                return false;
            }
        }

        if (head.left != null) {
            if (!set.add(head.left.value)) {
                return false;
            }
        }

        if (head.right != null) {
            if (!set.add(head.right.value)) {
                return false;
            }
        }

        return isBinaryTree(set, head.left) && isBinaryTree(set, head.right);
    }

    public static boolean treeSolution(Node head) {
        Set<Integer> set = new HashSet<>();
        set.add(head.value);
        rootValue = head.value;
        return isBinaryTree(set, head);
        // “ヽ(´▽｀)ノ”
    }

    public static void main(String[] args) {
        test();

    }

    // <template>
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    // <template>


    private static void test() {
//        Node node11 = new Node(148, null, null);
//        Node node10 = new Node(140, node11, null);
//        Node node9 = new Node(130, null, null);
//        Node node8 = new Node(139, node9, node10);
//        Node node7 = new Node(19, null, node8);
//        Node node6 = new Node(9, null, node7);
//        Node node5 = new Node(6, null, null);
        Node node4 = new Node(7, null, null);
        Node node3 = new Node(1, null, null);
        Node node2 = new Node(8, null, null);
        Node node1 = new Node(3, node3, node4);
        Node node0 = new Node(5, node1, node2);
        boolean b = treeSolution(node0);
        assert b;
    }
}
