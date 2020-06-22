package 字符串.p647;

class Solution {
    public int countSubstrings(String s) {

        if(s == null) return 0;
        int res = s.length();
        for(int i = 0; i < s.length(); i++) {
            res += countSubstrings(s, i - 1, i + 1); // 中心是一个元素
            res += countSubstrings(s, i - 1, i); // 中心是两个元素
        }

        return res;
    }


    // 辅助函数 如果s[l] == s[r] 则回文串的数量+1
    // 计算以一个或者两个元素为中心的回文串的个数
    private int countSubstrings(String s, int l, int r) {

        int res = 0;
        while(l >= 0 && r < s.length()) {
            if(s.charAt(l) == s.charAt(r))
                res ++;
            else break;
            l --;
            r ++;
        }

        return res;
    }

    public static void main(String[] args) {

        System.out.println((new Solution().countSubstrings("fdsklf")));
    }
}
