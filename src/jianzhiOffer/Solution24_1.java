package jianzhiOffer;

public class Solution24_1 {

    public ListNode reverseList(ListNode head) {

        ListNode pre = null;
        while(head != null) {
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }

        return pre;
    }
}
