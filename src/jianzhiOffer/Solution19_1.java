package jianzhiOffer;

public class Solution19_1 {

    char[] sChars; // s的字符数组
    char[] pChars; // p的字符数组
    public boolean isMatch(String s, String p) {

        sChars = s.toCharArray();
        pChars = p.toCharArray();
        return helper(sChars.length - 1, pChars.length - 1, false);
    }

    /**
     *
     * @param sR 匹配到哪一个字符了 从右开始遍历数组
     * @param pR 指向p的字符数组
     * @param flag 是否被*标记
     * @return
     */
    private boolean helper(int sR, int pR, boolean flag) {

        if(sR == -1 && pR == -1) return true;
        if(pR == -1) return false;
        char p = pChars[pR];
        if(sR >= 0) {
            char s = sChars[sR];
            if (flag) {
                if (s == p || p == '.') return helper(sR, pR - 1, false) || // 假设有0个被标记的字符
                        helper(sR - 1, pR, true); // n个被标记的字符 n>=1
                else return helper(sR, pR - 1, false);
            } else {
                if (s == p || p == '.') return helper(sR - 1, pR - 1, false);
                else if (p == '*') return helper(sR, pR - 1, true);
                else return false;
            }
        }
        else {
            if(flag) return helper(sR, pR - 1, false);
            if(p == '*') return helper(sR, pR - 1, true);
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println((new Solution19_1()).isMatch("aab", "c*a*b"));
    }
}
