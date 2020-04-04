package Simplify_Path_71;

import java.util.Stack;

public class Solution2 {

    public String simplifyPath(String path) {

        String[] arr = path.split("/");
        String res = "";
        Stack<String> stack = new Stack<>();
        for(String s: arr) {
            switch(s) {
                case ".":
                    break;
                case "..":
                    if(!stack.isEmpty())
                        stack.pop();
                    break;
                case "":
                    break;
                default:
                    stack.push(s);
                    break;
            }
        }
        if(stack.isEmpty())
            res  = "/";
        else {
            while(!stack.isEmpty())
                res = "/" + stack.pop() + res;
        }

        return res;
    }

    public static void main(String[] args) {

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        for(int i = 0; i < 5; i++) {
            s1.push(i);
            s2.push(i);
        }
        while(!s1.isEmpty())
            System.out.println(s1.pop());
        for(int i = 0; i < s2.size(); i++)
            System.out.println(s2.get(i));

    }
}
