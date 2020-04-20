package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.List;

public class Solution131_1 {
    public List<List<String>> partition(String s) {

        List<List<String>> res = new ArrayList<>();
        helper(s, new ArrayList<>(), res, 0);
        return res;
    }
    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        char[] arr = s.toCharArray();
        while(l <= r) {
            if(arr[l] != arr[r]) return false;
            l++;
            r--;
        }

        return true;
    }

    // index 表示从第几个字串开始  前边的已经处理完毕
    private void helper(String s, List<String> subRes, List<List<String>> res, int index) {
        int L = s.length();
        if(index == L) {
            res.add(new ArrayList<>(subRes));
            return;
        }
        if(index > L) return;
        for(int i = 1; i <= L - index; i++) { // i表示这一次分割多少个字符组成的字串
            String sub = s.substring(index, index + i);
            if(isPalindrome(sub)) {
                subRes.add(sub);
                helper(s, subRes, res, index + i);
                subRes.remove(subRes.size() - 1);
            }
        }
    }
}
