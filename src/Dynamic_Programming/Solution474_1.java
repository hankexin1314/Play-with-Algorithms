package Dynamic_Programming;

// 递归
public class Solution474_1 {

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

        return helper(ones, zeros, m, n, 0);
    }

    // 0-L - 1  最多能组成几种
    private int helper(int[] ones, int[] zeros, int m, int n, int index) {

        if(index == ones.length || m < 0 || n < 0) return 0;
        if(m >= zeros[index] && n >= ones[index])
            return Math.max(1 + helper(ones, zeros, m - zeros[index], n - ones[index], index + 1),
                    helper(ones, zeros, m, n, index + 1));

        return helper(ones, zeros, m, n, index + 1);
    }
}
