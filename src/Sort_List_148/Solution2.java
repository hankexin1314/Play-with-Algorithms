package Sort_List_148;

public class Solution2 {

    public ListNode sortList(ListNode head) {

        if(head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        ListNode pre = null; // 如果没有这个，对于只有两个节点的链表，无法将其分为两部分
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null; // 剪断

        ListNode l = sortList(head);
        ListNode r = sortList(slow);

        return merge(l, r);
    }


    // 左边部分的头结点l 和右边部分的头结点r
    // 返回头结点
    private ListNode merge(ListNode l, ListNode r) {

        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while(l != null && r != null) {
            if(l.val <= r.val) {
                cur.next = l;
                l = l.next;
                cur = cur.next;
            }
            else {
                cur.next = r;
                r = r.next;
                cur = cur.next;
            }
        }
        if(l != null) cur.next = l;
        else if(r != null) cur.next = r;

        return dummyHead.next;
    }
}
