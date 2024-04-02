package ru.ypracticum.sprint5.finals.B;

// <template>
class Node {
    private int value;
    private Node left;
    private Node right;

    Node(Node left, Node right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
// <template>

public class B {
    public static Node remove(Node root, int key) {

        if (root == null) {
            return null;
        }

        if (root.getValue() == key) {
            if (root.getLeft() == null && root.getRight() == null) {
                return null;
            }

            if (root.getRight() == null) {
                return root.getLeft();
            }

            if (root.getLeft() == null) {
                return root.getRight();
            }

            Node min = findMin(root.getRight());

            root.setValue(min.getValue());
            root.setRight(remove(root.getRight(), min.getValue()));

            return root;
        }

        if (root.getValue() > key) {
            if (root.getLeft() != null) {
                root.setLeft(remove(root.getLeft(), key));
            }
        } else {
            if (root.getRight() != null) {
                root.setRight(remove(root.getRight(), key));
            }
        }

        return root;
    }

    private static Node findMin(Node node) {
        if (node.getLeft() == null) {
            return node;
        } else {
            return findMin(node.getLeft());
        }
    }

    private static void test() {
        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(node1, null, 3);
        Node node3 = new Node(null, node2, 1);
        Node node4 = new Node(null, null, 6);
        Node node5 = new Node(node4, null, 8);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node3, node6, 5);
        Node newHead = remove(node7, 10);
        assert newHead.getValue() == 5;
        assert newHead.getRight() == node5;
        assert newHead.getRight().getValue() == 8;
    }
}