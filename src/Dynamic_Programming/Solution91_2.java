package Dynamic_Programming;

// 动态规划
public class Solution91_2 {


    public int numDecodings(String s) {

        if(s == null) return 0;
        char[] cs = s.toCharArray();
        int L = cs.length;
        int[] memo = new int[L + 1];
        memo[0] = 0;
        memo[1] = cs[0] == '0' ? 0 : 1;
        for(int i = 2; i <= L; i++) {
            int first = cs[i - 1] - '0';
            int second = (cs[i - 2] - '0') * 10 + cs[i - 1] - '0';
            if(first >= 1 && first <= 9) {
                memo[i] += memo[i - 1];
            }
            if(second >= 10 && second <= 26) {
                memo[i] += memo[i - 2];
            }
        }


        return memo[L];
    }

}

