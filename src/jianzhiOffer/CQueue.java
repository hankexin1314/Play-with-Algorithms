package jianzhiOffer;

import java.util.Stack;

public class CQueue {

    private Stack<Integer> a;
    private Stack<Integer> b;

    public CQueue() {
        a = new Stack<>();
        b = new Stack<>();
    }

    public void appendTail(int value) {
        a.push(value);
    }

    public int deleteHead() {
        if(b.isEmpty()) {
            if(a.isEmpty()) return -1;
            while (!a.isEmpty())
                b.push(a.pop());
        }
        return b.pop();
    }
}
