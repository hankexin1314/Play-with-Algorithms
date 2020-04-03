package Delete_Node_237;

import java.util.List;

public class Solution {

    public void deleteNode(ListNode node) {

        ListNode next = node.next; // 因为是非末尾，所以这个节点不为null
        node.val = next.val;
        node.next = next.next;
        next.next = null;
    }
}
