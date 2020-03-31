package Delete_Duplicates_83;

import java.util.HashSet;

public class Solution {

    public ListNode deleteDuplicates(ListNode head) {

        if(head == null)
            return null;
        HashSet<Integer> set = new HashSet<>();
        ListNode pre = null, cur = head;
        while(cur != null) {
            if(set.contains(cur.val)) {
                pre.next = cur.next;
                cur = cur.next;
            }
            else {
                set.add(cur.val);
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
