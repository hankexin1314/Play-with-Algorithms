package Dynamic_Programming;

// 动态规划
public class Solution474_3 {

    public int findMaxForm(String[] strs, int m, int n) {

        int L = strs.length;
        int[] ones = new int[L], zeros = new int[L];
        for(int i = 0; i < L; i++) {
            String str = strs[i];
            for(char c: str.toCharArray()) {
                if(c == '0') zeros[i]++;
                if(c == '1') ones[i]++;
            }
        }
        int[][] memo = new int[m + 1][n + 1];
        for(int i = 0; i < L; i++) {
            for(int zero = m; zero >= zeros[i]; zero--) {
                for(int one = n; one >= ones[i]; one--) {
                    memo[zero][one] = Math.max(1 + memo[zero - zeros[i]][one - ones[i]],
                            memo[zero][one]);
                }
            }
        }

        return memo[m][n];
    }

}
