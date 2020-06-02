package jianzhiOffer;

public class Solution22_2 {

    public ListNode getKthFromEnd(ListNode head, int k) {

        ListNode slow = head, fast = head;
        for(int i = 0; i < k; i++) {
            if(fast == null) return null;
            fast = fast.next;
        }
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
