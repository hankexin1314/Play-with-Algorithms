package Length_Of_Longest_Substring_3;

// 时间复杂度 O(len(s))
// 空间复杂度 O(len(charSet))

import java.util.Arrays;

public class Solution2 {

    public int lengthOfLongestSubstring(String s) {

        int[] last = new int[256];
        Arrays.fill(last, -1);
        int l = 0, r = -1; // 滑动窗口[l...r]
        int res = 0;

        while(r + 1 < s.length()) {

            r++;
            if(last[s.charAt(r)] == -1)
                last[s.charAt(r)] = r;
            else {
                // l = last[s.charAt(r)] + 1;
                l = Math.max(last[s.charAt(r)] + 1, l);
                last[s.charAt(r)] = r;
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    public static void main(String [] args) {

        int a = (new Solution2()).lengthOfLongestSubstring("abcabcd");
    }
}
