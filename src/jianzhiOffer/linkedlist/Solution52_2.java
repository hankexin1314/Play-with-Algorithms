package jianzhiOffer.linkedlist;

import java.util.HashSet;

public class Solution52_2 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if(headA == null || headB == null) return null;
        ListNode node1 = headA, node2 = headB;
        while(node1 != null || node2 != null) {
            if(node1 == null) node1 = headB;
            if(node2 == null) node2 = headA;
            if(node1== node2) return node1;
            node1 = node1.next;
            node2 = node2.next;
        }
        return null;
    }
}
