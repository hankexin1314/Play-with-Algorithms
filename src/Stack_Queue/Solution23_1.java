package Stack_Queue;

import java.util.PriorityQueue;

public class Solution23_1 {

    public ListNode mergeKLists(ListNode[] lists) {

        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        int k = lists.length;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for(ListNode node: lists) {
            if(node != null)
                pq.add(node);
        }
        while(!pq.isEmpty()) {
            ListNode node = pq.poll();
            cur.next = node;
            cur = cur.next;
            if(node.next != null)
                pq.add(node.next);
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode[] lists = {};
        ListNode res = (new Solution23_1()).mergeKLists(lists);
        System.out.println(res);
    }
}
