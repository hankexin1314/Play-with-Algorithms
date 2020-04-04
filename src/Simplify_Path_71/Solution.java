package Simplify_Path_71;

import java.util.Stack;

public class Solution {

    public String simplifyPath(String path) {

        String[] arr = path.split("/");
        StringBuilder res = new StringBuilder("");
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
            res.append("/");
        else {
            Stack<String> tmp = new Stack<>();
            while (!stack.isEmpty())
                tmp.push(stack.pop());
            while (!tmp.isEmpty()) {
                res.append("/");
                res.append(tmp.pop());
            }
        }

        return res.toString();
    }

    public static void main(String[] args) {

        String str = "/home/";
        String[] arr = str.split("/");
        for(String s: arr)
            System.out.println(s);

        System.out.println((new Solution()).simplifyPath(str));
    }
}
