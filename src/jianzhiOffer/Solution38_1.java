package jianzhiOffer;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution38_1 {

    boolean[] used;
    public String[] permutation(String s) {

        if(s == null || s.equals("")) return new String[0];
        char[] chars = s.toCharArray();
        used = new boolean[chars.length];
        Arrays.sort(chars);
        ArrayList<String> ans = new ArrayList<>();
        helper(chars, new StringBuilder(), ans);
        String[] res = new String[ans.size()];
        for(int i = 0; i < res.length; i++) res[i] = ans.get(i);

        return res;
    }

    public void helper(char[] chars, StringBuilder sb, ArrayList<String> ans) {

        if(sb.length() == chars.length) {
            ans.add(sb.toString());
            return;
        }
        Character pre = null;
        for(int i = 0; i < chars.length; i++) {
            if(pre != null && chars[i] == pre) continue;
            if(!used[i]) {
                sb.append(chars[i]);
                used[i] = true;
                int l = sb.length();
                helper(chars, sb, ans);
                sb.setLength(l - 1);
                used[i] = false;
                pre = chars[i];
            }
        }
    }

    public static void main(String[] args) {
        String[] strings = (new Solution38_1()).permutation("abc");
        System.out.println(strings);
    }
}
