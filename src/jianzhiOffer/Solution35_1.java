package jianzhiOffer;

import java.util.HashMap;

public class Solution35_1 {

    HashMap<Node, Node> map = new HashMap<>();
    public Node copyRandomList(Node head) {

        if(head == null) return null;
        if(map.containsKey(head)) return map.get(head);
        Node node = new Node(head.val);
        map.put(head, node);
        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);

        return node;
    }
}
