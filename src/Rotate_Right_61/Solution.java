package Rotate_Right_61;

public class Solution {

    public ListNode rotateRight(ListNode head, int k) {

        if(head == null || head.next == null)
            return head;

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        int count = 0;
        // 计算链表长度
        while(head != null) {
            count ++;
            head = head.next;
        }
        k = k % count;
        if(k == 0)
            return dummyHead.next;
        ListNode slow = dummyHead;
        ListNode fast = dummyHead.next;
        int distance = 1;
        while(distance < k) {
            fast = fast.next;
            distance ++;
        }

        while(fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = dummyHead.next;
        dummyHead.next = slow.next;
        slow.next = null;
        return dummyHead.next;
    }
}
