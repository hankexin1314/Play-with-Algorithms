package Sort_List_148;

import org.w3c.dom.ls.LSException;

public class Solution {

    public ListNode sortList(ListNode head) {

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        int count = 0; // 记录链表中有多少个节点
        while(head != null) {
            count ++;
            head = head.next;
        }

        for(int step = 1; step < count; step <<= 1) {
            ListNode cur = dummyHead.next;
            ListNode pre = dummyHead;
            while(cur != null) {
                ListNode left = cur;
                ListNode right = split(left, step);
                cur = split(right, step);
                pre = merge(left, right, pre);
            }
        }

        return dummyHead.next;

    }

    // 从起始节点start 开始，选取step个节点 将其截断  返回截断部分的下一个节点
    private ListNode split(ListNode start, int step) {

        if(start == null)
            return null;

        for(int i = 1; start.next != null && i < step; i++) {
            start = start.next;
        }
        ListNode res = start.next;
        start.next = null;
        return res;
    }


    // 左边部分的头结点l 和右边部分的头结点r  还有这两部分之前的节点  最后会将合并后的结果接在pre 后面
    // 返回合并后的部分的最后一个节点
    private ListNode merge(ListNode l, ListNode r, ListNode pre) {

        while(l != null && r != null) {
            if(l.val <= r.val) {
                pre.next = l;
                l = l.next;
                pre = pre.next;
            }
            else {
                pre.next = r;
                r = r.next;
                pre = pre.next;
            }
        }
        if(l != null) pre.next = l;
        else if(r != null) pre.next = r;
        while(pre.next != null) pre = pre.next;

        return pre;
    }
}
