package Find_Anagrams_438;

import java.util.ArrayList;
import java.util.List;


// 时间复杂度O(n) n 为s的长度
// 空间复杂度O(26) 26个字母
public class Solution {

    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> res = new ArrayList<>();
        int[] a = new int[26]; // 记录滑动窗口中子串的频率
        int[] b = new int[26]; // 记录目标子串的频率

        for(int i = 0; i < p.length(); i++)
            b[p.charAt(i) - 'a']++;

        int l = 0, r = -1; // [l...r] 滑动窗口
        while(r + 1 < s.length()) {

            r++;
            a[s.charAt(r) - 'a']++;
            if(r - l + 1 > p.length()) {
                a[s.charAt(l) - 'a']--;
                l++;
            }
            if(isAnagrams(a, b))
                res.add(l);
        }

        return res;
    }

    private boolean isAnagrams(int[] s, int[] p) {

        for(int i = 0; i < s.length; i++)
            if(s[i] != p[i])
                return false;

        return true;
    }

}
