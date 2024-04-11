package ru.ypracticum.sprint5.B;

public class B {
    public static boolean treeSolution(Node head) {
        return isBalanced(head) != -1;
    }

    private static int isBalanced(Node head) {
        if (head == null) {
            return 0;
        } else {
            int left = isBalanced(head.left);
            int right = isBalanced(head.right);
            if (left == -1 || right == -1) return -1;
            if (Math.abs(left - right) > 1) return -1;
            return Math.max(left, right) + 1;
        }
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
    }
    // <template>

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(10);
        Node node5 = new Node(2);
        node5.left = node3;
        node5.right = node4;
        boolean b = treeSolution(node5);
        assert treeSolution(node5);
    }
}