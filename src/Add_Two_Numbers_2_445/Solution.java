package Add_Two_Numbers_2_445;

import java.util.Stack;

// 可能造成溢出
public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        ListNode res = new ListNode(0);
        ListNode cur = null;
        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int up = 0; // 进位
        while(!s1.isEmpty() || !s2.isEmpty()) {

            int x = s1.isEmpty() ? 0 : s1.pop();
            int y = s2.isEmpty() ? 0 : s2.pop();
            int sum = x + y + up;
            up = sum / 10;
            res.next = new ListNode(sum % 10);
            res.next.next = cur;
            cur = res.next;
        }
        if(up != 0) {
            res.next = new ListNode(up);
            res.next.next = cur;
        }
        return res.next;
    }

}
