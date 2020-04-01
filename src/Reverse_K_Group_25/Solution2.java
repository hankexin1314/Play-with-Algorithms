package Reverse_K_Group_25;

import java.util.Stack;

public class Solution2 {

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode cur = head;
        ListNode tail = pre;
        int count = 0;
        while(true) {

            count = 0;
            while(tail.next != null && count != k) {
                tail = tail.next;
                count++;
            }
            if(count != k)
                return dummyHead.next;
            while(pre.next != tail) {
                pre.next = cur.next;
                cur.next = tail.next;
                tail.next = cur;
                cur = pre.next;
            }
            pre = head;
            head = head.next;
            cur = head;
            tail = pre;
        }
    }
}
