package Length_Of_Longest_Substring_3;

// 时间复杂度 O(len(s))
// 空间复杂度 O(len(charSet))

public class Solution1 {

    public int lengthOfLongestSubstring(String s) {

        int[] freq = new int[256];
        char[] c = s.toCharArray();
        int l = 0, r = -1; // 滑动窗口[l...r]
        int max = 0;

        while(l < c.length) {

            if(r + 1 < c.length && freq[c[r+1]] == 0) {
                r++;
                freq[c[r]]++;
            }
            else {
                freq[c[l]]--;
                l++;
            }
            max = Math.max(max, r - l + 1);
        }

        return max;
    }

    public static void main(String [] args) {

        int a = (new Solution1()).lengthOfLongestSubstring("abcabcd");
    }
}
