package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.List;

// 回溯 String加和
public class Solution93_3 {
    public List<String> restoreIpAddresses(String s) {

        List<String> res = new ArrayList<>();
        helper(s, res, 0, 1, new StringBuilder(""));
        return res;
    }


    private void helper(String s, List<String> res, int index, int part, StringBuilder ans) {
        int L = s.length();
        if(part == 5 && index == L) {
            res.add(ans.toString());
            return;
        }
        if(part > 5 || index > L) return;
        for(int i = 1; i <= 3; i++) {
            if(index + i > L) break;
            String sub = s.substring(index, index + i);
            if(Integer.parseInt(sub) > 255 || (i > 1 && sub.charAt(0) == '0')) break;
            ans.append(sub);
            if(part < 4) ans.append(".");
            helper(s, res, index+i, part+1, ans);
            if(part < 4) ans.delete(ans.length()-1, ans.length());
            ans.delete(ans.length()-i, ans.length());
        }
    }




}
