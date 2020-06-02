package jianzhiOffer;

import java.util.List;

public class Solution22_1 {

    public ListNode getKthFromEnd(ListNode head, int k) {

        if(head == null) return head;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode fast = head, slow = dummyHead;
        int count = 1;
        while(fast != null) {
            if(count == k) break;
            fast = fast.next;
            count++;
        }
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return count == k ? slow : null;
    }
}
