package Reorder_List_143;

public class Solution {

    public void reorderList(ListNode head) {

        if(head == null || head.next == null)
            return;

        ListNode dummyHead1 = new ListNode(0);
        dummyHead1.next = head;

        // 寻找中间节点
        ListNode slow = dummyHead1, fast = dummyHead1;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 将链表分为两部分
        ListNode dummyHead2 = new ListNode(0);
        dummyHead2.next = slow.next;
        slow.next = null;

        // 翻转第二部分链表
        ListNode pre = null, cur = dummyHead2.next, next = cur;
        while(next != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        dummyHead2.next = pre;

        ListNode head1 = dummyHead1.next, head2 = dummyHead2.next;
        ListNode next1, next2;
        while(head1 != null && head2 != null) {
            next1 = head1.next;
            next2 = head2.next;
            head1.next = head2;
            head2.next = next1;
            head1 = next1;
            head2 = next2;
        }
    }
}
