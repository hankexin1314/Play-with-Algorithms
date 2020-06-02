package jianzhiOffer;

public class Solution18_1 {

    public ListNode deleteNode(ListNode head, int val) {

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while(head != null) {
            if(head.val == val) {
                pre.next = head.next;
                head.next = null;
                break;
            }
            pre = head;
            head = head.next;
        }

        return dummyHead.next;
    }
}
