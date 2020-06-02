package jianzhiOffer;

import java.util.Stack;

public class Solution36_1 {

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public Node treeToDoublyList(Node root) {

        if(root == null) return null;
        Node dummyHead = new Node();
        boolean flag = true; // 记录是否为第一个访问的节点
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        Node pre = null;
        while(cur != null || !stack.isEmpty()) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            // 访问
            if(flag) {
                dummyHead.right = cur;
                flag = false;
            }
            else {
                pre.right = cur;
                cur.left = pre;
            }
            pre = cur;
            cur = cur.right;
        }
        pre.right = dummyHead.right;
        dummyHead.right.left = pre;

        return dummyHead.right;
    }
}
