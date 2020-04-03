package Insertion_Sort_List_147;

public class Solution {

    public ListNode insertionSortList(ListNode head) {

        if(head == null || head.next == null)
            return head;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre;
        ListNode cur; // 这两个指针用来实现插入排序 head用来遍历
        ListNode preHead = head; // head的前一个元素 head前的元素全部排序完毕
        head = head.next;
        while(head != null) {

            if(head.val >= preHead.val) {
                preHead = head;
                head = head.next;
            }
            else {
                pre = dummyHead;
                cur = dummyHead.next; // 这两个指针用来实现插入排序 head用来遍历
                while(cur.val < head.val) {
                    pre = cur;
                    cur = cur.next;
                }
                preHead.next = head.next;
                pre.next = head;
                head.next = cur;
                head = preHead.next;
            }
        }

        return dummyHead.next;
    }
}
