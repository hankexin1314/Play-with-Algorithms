package Merge_Two_Lists_21;

import java.util.List;

public class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(0); // 存储结果
        ListNode res = dummyHead;

        while(l1 != null || l2 != null) {

            if(l1 == null) {
                res.next = l2;
                l2 = null;
            }
            else if(l2 == null) {
                res.next = l1;
                l1 = null;
            }
            else if(l1.val <= l2.val) {
                res.next = new ListNode(l1.val);
                res = res.next;
                l1 = l1.next;
            }
            else {
                res.next = new ListNode(l2.val);
                res = res.next;
                l2 = l2.next;
            }
        }

        return dummyHead.next;
    }
}
