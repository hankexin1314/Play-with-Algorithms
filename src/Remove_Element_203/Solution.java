package Remove_Element_203;

public class Solution {

    public ListNode removeElements(ListNode head, int val) {

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while(head != null) {
            if(head.val == val) {
                head = head.next;
                pre.next = head;
            }
            else {
                pre = head;
                head = head.next;
            }
        }

        return dummyHead.next;
    }
}
