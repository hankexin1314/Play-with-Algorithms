package 设计;

import java.util.HashMap;

public class LRUCache {

    class Node {
        Node pre, next;
        int val;
        int key;
        public Node() {
            pre = null;
            next = null;
            val = 0;
            key = 0;
        }
        public Node(int key, int val) {
            pre = null;
            next = null;
            this.val = val;
            this.key = key;
        }
    }
    private HashMap<Integer, Node> map = new HashMap<>();
    private int cap;
    private Node tail, head;

    public LRUCache(int capacity) {
        cap = capacity;
        tail = new Node();
        head = new Node();
        head.next = tail;
        tail.pre = head;
    }

    private void addLast(Node node) {
        node.pre = tail.pre;
        node.next = tail;
        tail.pre.next = node;
        tail.pre = node;
    }
    private void removeFirst() {
        Node node = head.next;
        node.next.pre = node.pre;
        node.pre.next = node.next;
        node.pre = null;
        node.next = null;
    }

    private void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = null;
        node.next = null;
    }

    private void makeNodeRecently(Node node) {
        removeNode(node);
        addLast(node);
    }


    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        makeNodeRecently(node);
        return node.val;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            makeNodeRecently(node);
            return;
        }
        if(cap <= map.size()) {
            int oldestKey = head.next.key;
            removeFirst();
            map.remove(oldestKey);
        }
        Node node = new Node(key, value);
        map.put(key, node);
        addLast(node);
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println(lru.get(1));
        lru.put(3, 3);
        System.out.println(lru.get(2));
    }
}
