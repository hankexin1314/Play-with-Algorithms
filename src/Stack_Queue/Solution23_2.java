package Stack_Queue;

import java.util.PriorityQueue;

// 分治 递归实现
public class Solution23_2 {

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        return mergeKLists(lists, 0, lists.length - 1);
    }

    // 将数组分为两部分进行归并 [start, mid] [mid + 1, end]
    private ListNode mergeKLists(ListNode[] lists, int start, int end) {
        if(start == end) return lists[start];
        int mid = (end - start) / 2 + start;
        ListNode l = mergeKLists(lists, start, mid);
        ListNode r = mergeKLists(lists, mid + 1, end);

        return mergeTwoList(l, r);
    }

    // 归并两个链表
    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val <= l2.val) {
            l1.next = mergeTwoList(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoList(l1, l2.next);
            return l2;
        }
    }

}
