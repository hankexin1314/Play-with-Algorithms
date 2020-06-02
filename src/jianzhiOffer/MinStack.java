package jianzhiOffer;

import java.util.Stack;

public class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {

        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {

        stack.push(x);
        if(minStack.isEmpty()) minStack.push(x);
        else minStack.push(x < minStack.peek() ? x : minStack.peek());
    }

    public void pop() {

        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
