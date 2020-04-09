package Stack_Queue;


// 迭代实现合并操作
public class Solution23_3 {

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
    // 迭代实现
    private ListNode mergeTwoList(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;

        while(l1 != null || l2 != null) {
            if (l1 == null) {
                cur.next = l2;
                break;
            }
            else if (l2 == null) {
                cur.next = l1;
                break;
            }
            else if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        return dummyHead.next;
    }

}
