package Partition_List_86;

public class Solution {

    public ListNode partition(ListNode head, int x) {

        ListNode dummyHead1 = new ListNode(0);
        ListNode dummyHead2 = new ListNode(0);
        ListNode node1 = dummyHead1;
        ListNode node2 = dummyHead2;
        while (head != null) {
            System.out.println(dummyHead1.next);
            System.out.println(dummyHead2.next);
            if (head.val < x) {
                node1.next = head;
                node1 = node1.next;
            } else {
                node2.next = head;
                node2 = node2.next;
            }

            head = head.next;
        }
        node1.next = dummyHead2.next;
        return dummyHead1.next;
    }

    public static void main(String[] args) {

        int[] arr = {1, 4, 3, 2, 5, 2};
        ListNode head = new ListNode(arr);
        ListNode res = (new Solution()).partition(head, 3);
    }



}
