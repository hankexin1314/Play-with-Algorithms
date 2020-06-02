package jianzhiOffer;

import java.util.Stack;

public class Solution31_1 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {

        if(pushed == null || popped == null) return false;
        Stack<Integer> stack = new Stack<>();
        int index = 0; // 指向当前判断poped数组中的数的index

        for(int num: pushed) {
            stack.push(num);
            while(!stack.isEmpty()) {
                if(stack.peek() != popped[index]) break;
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }
}
