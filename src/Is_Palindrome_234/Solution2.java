package Is_Palindrome_234;

import java.util.ArrayList;

public class Solution2 {

    public boolean isPalindrome(ListNode head) {

        if(head == null || head.next == null)
            return true;

        ListNode dummyHead1 = new ListNode(0);
        dummyHead1.next = head;
        ListNode slow = dummyHead1, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 分为两个链表
        ListNode dummyHead2 = new ListNode(0);
        if(fast == null) { // 偶数个节点
            dummyHead2.next = slow.next;
            slow.next = null;
        }
        else { // fast.next == null 奇数个节点
            dummyHead2.next = slow.next.next;
            slow.next = null;
        }

        // 翻转其中一个链表
        ListNode pre = null, cur = dummyHead2.next, head2 = cur;
        while(head2 != null) {
            head2 = cur.next;
            cur.next = pre;
            pre = cur;
            cur = head2;
        }
        dummyHead2.next = pre;

        // 比较
        head = dummyHead1.next;
        head2 = dummyHead2.next;
        while(head != null && head2 != null) {
            if(head.val != head2.val)
                return false;
            head = head.next;
            head2 = head2.next;
        }

        return true;
    }
}
