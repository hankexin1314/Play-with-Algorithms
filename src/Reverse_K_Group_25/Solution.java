package Reverse_K_Group_25;

import java.util.List;
import java.util.Stack;

public class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {

        Stack<ListNode> s = new Stack<>();
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;

        while(true) {
            while(s.size() != k && head != null) {
                s.push(head);
                head = head.next;
            }
            if(s.size() != k)
                return dummyHead.next;
            while(!s.isEmpty()) {
                pre.next = s.pop();
                pre = pre.next;
            }
            pre.next = head;
        }
    }
}
