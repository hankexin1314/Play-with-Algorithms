package jianzhiOffer;

import java.util.HashMap;

public class Solution35_2 {

    public Node copyRandomList(Node head) {

        if(head == null) return null;
        // 合并
        Node cur = head;
        while(cur != null) {
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next  = node;
            cur = node.next;
        }
        // 处理random
        cur = head;
        while(cur != null) {
            Node node = cur.next;
            node.random = cur.random == null ? null : cur.random.next;
            cur = node.next;
        }
        // 拆分
        Node res = head.next;
        cur = head.next;
        while(cur != null) {
            head.next = head.next.next;
            head = head.next;
            cur.next = cur.next == null ? null : cur.next.next;
            cur = cur.next;
        }
        return res;
    }
}
