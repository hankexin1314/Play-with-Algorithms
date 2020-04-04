package Is_Valid_20;

import java.util.Stack;

public class Solution {

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == '(' || arr[i] == '[' || arr[i] == '{')
                stack.push(arr[i]);
            else {
                if(stack.isEmpty())
                    return false;
                else {
                    char tmp = stack.pop();
                    if(tmp == '(' && arr[i] != ')') return false;
                    if(tmp == '[' && arr[i] != ']') return false;
                    if(tmp == '{' && arr[i] != '}') return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
