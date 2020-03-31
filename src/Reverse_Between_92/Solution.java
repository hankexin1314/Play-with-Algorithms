package Reverse_Between_92;

public class Solution {

    public ListNode reverseBetween(ListNode head, int m, int n) {

        if(head == null)
            return head;

        ListNode pre = null, cur = head, next = head.next;
        int index = 1;
        while(index < m) {
            pre = cur;
            cur = next;
            next = next.next;
            index++;
        }
        ListNode m_pre = pre, first = cur; // m_pre记录 m-1位置的节点 first记录待翻转部分的第一个节点
        while(index < n) {
            pre = cur;
            cur = next;
            next = next.next;
            cur.next = pre;
            index++;
        }
        if(m_pre != null)
            m_pre.next = cur;
        else
            head = cur;
        first.next = next;

        return head;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
