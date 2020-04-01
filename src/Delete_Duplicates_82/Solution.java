package Delete_Duplicates_82;

import java.util.HashSet;

public class Solution {

    public ListNode deleteDuplicates(ListNode head) {

        if(head == null || head.next == null)
            return head;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode slow = dummyHead;
        ListNode fast = dummyHead.next;

        while(fast != null) {

            if(fast.next != null && fast.next.val == fast.val) {
                int val = fast.val;
                while(fast != null && fast.val == val) // GJ
                    fast = fast.next;
            }
            else {
                slow.next = fast;
                slow = fast;
                fast = fast.next;
            }
        }
        slow.next = fast;

        return dummyHead.next;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 3, 4, 4, 5};
        ListNode head = new ListNode(arr);
        ListNode res = (new Solution()).deleteDuplicates(head);
    }
}
