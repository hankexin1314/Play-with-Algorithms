package Remove_Nth_From_End_19;

public class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        if(head == null || head.next == null)
            return null;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode slow = dummyHead, fast = dummyHead;
        ListNode pre = dummyHead;
        int distance = 0;
        // 将两个指针移动到合适的位置
        while(distance < n) {
            fast = fast.next;
            distance ++;
        }
        while(fast != null) {
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }

        // 删除节点
        pre.next = slow.next;
        slow.next = null;

        return dummyHead.next;
    }
}
