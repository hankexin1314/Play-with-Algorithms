package Reverse_List_206;

public class Solution2 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode reverseList(ListNode head) {

        if(head == null)
            return null;
        ListNode pre = null, cur = head;
        while(head != null) {
            head = cur.next;
            cur.next = pre;
            pre = cur;
            cur = head;
        }

        return pre;
    }

}
