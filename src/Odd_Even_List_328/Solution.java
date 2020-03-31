package Odd_Even_List_328;

public class Solution {

    public ListNode oddEvenList(ListNode head) {

        if(head == null)
            return null;

        ListNode head2 = head.next;
        ListNode cur1 = head, cur2 = head.next;
        while(cur2 != null && cur2.next != null) {
            cur1.next = cur2.next;
            cur1 = cur1.next;
            cur2.next = cur1.next;
            cur2 = cur2.next;
        }
        cur1.next = head2;

        return head;
    }
}
