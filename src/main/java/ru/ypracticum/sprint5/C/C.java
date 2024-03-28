package ru.ypracticum.sprint5.C;

public class C {
    public static void main(String[] args) {
        test();
    }
    public static boolean treeSolution(Node head) {
        if (head == null) {
            return true;
        }

        return helper(head.left, head.right);
    }

    private static boolean helper(Node left, Node right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        return left.value == right.value && helper(left.left, right.right) && helper(left.right, right.left);
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
        Node node1 = new Node(3,  null,  null);
        Node node2 = new Node(4,  null,  null);
        Node node3 = new Node(4,  null,  null);
        Node node4 = new Node(3,  null,  null);
        Node node5 = new Node(2, node1, node2);
        Node node6 = new Node(2, node3, node4);
        Node node7 = new Node(1, node5, node6);
        boolean b = treeSolution(node7);
        assert b;
    }
}
