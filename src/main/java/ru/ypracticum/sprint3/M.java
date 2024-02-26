package ru.ypracticum.sprint3;

public class M {
    public static void main(String[] args) {
        ListNode next3 = new ListNode(5);
        ListNode next2 = new ListNode(4, next3);
        ListNode next1 = new ListNode(3, next2);
        ListNode next = new ListNode(2, next1);
        ListNode head = new ListNode(1, next);
        System.out.println(reverseList(head));
    }

    public static ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;

        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
