package Eval_RPN_150;

import java.util.Stack;

public class Solution {

    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();
        int a, b;
        for(int i = 0; i < tokens.length; i++) {
            if(tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/")) {
                b = stack.pop();
                a = stack.pop(); // 注意顺序
                if(tokens[i].equals("+")) stack.push(a + b);
                if(tokens[i].equals("-")) stack.push(a - b);
                if(tokens[i].equals("*")) stack.push(a * b);
                if(tokens[i].equals("/")) stack.push(a / b);
            }
            else {
                stack.push(strToNum(tokens[i]));
            }
        }
        return stack.pop();
    }

    private int strToNum(String str) {

        int res = 0;
        char[] arr = str.toCharArray();
        boolean nega = false; // 标记是否为负数

        for(char c: arr) {
            if(c == '-') {
                nega = true;
                continue;
            }
            res *= 10;
            res += c - '0';
        }
        return nega ? - res : res;
    }
}
