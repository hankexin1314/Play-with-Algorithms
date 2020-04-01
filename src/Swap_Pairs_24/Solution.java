package Swap_Pairs_24;

import java.util.List;

public class Solution {

    public ListNode swapPairs(ListNode head) {

        if(head == null || head.next == null)
            return head;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode swap1 = head, swap2 = head.next;

        while(swap1 != null && swap2 != null) {
            swap1.next = swap2.next;
            swap2.next = swap1;
            pre.next = swap2;

            pre = swap1;
            swap1 = swap1.next;
            swap2 = swap1 == null ? null : swap1.next.next;
        }

        return dummyHead.next;
    }
}
