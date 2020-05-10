package Dynamic_Programming;

// 记忆化搜索
public class Solution474_2 {

    int[][][] memo;
    boolean[][][] used;
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
        memo = new int[L][m + 1][n + 1];
        used = new boolean[L][m + 1][n + 1];

        return helper(ones, zeros, m, n, L - 1);
    }

    // 0-index  最多能组成几种
    private int helper(int[] ones, int[] zeros, int m, int n, int index) {

        if(index < 0 || m < 0 || n < 0) return 0;
        if(!used[index][m][n]) {
            if (m >= zeros[index] && n >= ones[index])
                memo[index][m][n] =  Math.max(1 + helper(ones, zeros, m - zeros[index], n - ones[index], index - 1),
                        helper(ones, zeros, m, n, index - 1));
            else
                memo[index][m][n] = helper(ones, zeros, m, n, index - 1);
            used[index][m][n] = true;
        }
        return memo[index][m][n];
    }
}
