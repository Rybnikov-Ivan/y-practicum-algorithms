package ru.ypracticum.sprint5.A;

public class A {
    private static int res = Integer.MIN_VALUE;
    public static int treeSolution(Node head) {
        if (head == null) {
            return res;
        }

        res = Math.max(head.value, treeSolution(head.left));
        res = Math.max(head.value, treeSolution(head.right));

        return res;
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
        Node node4 = new Node(2);
        node4.left = node3;
        int res = treeSolution(node4);
        assert res == 3;
    }
}
