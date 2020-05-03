package Dynamic_Programming;

import java.util.Arrays;

// 记忆化搜索
public class Solution343_3 {
    int[] memo;
    public int integerBreak(int n) {

        memo = new int[n + 1];
        Arrays.fill(memo, 0);
        return breakInteger(n);
    }

    // 将一个整数进行分割 获得最大积
    private int breakInteger(int n) {

        if(n == 1) return 1;
        if(memo[n] != 0) return memo[n];

        int max = -1;
        for(int i = 1; i < n; i++) {
            max = Math.max(max, i * Math.max(n - i, breakInteger(n - i)));
        }
        memo[n] = max;
        return max;
    }

    public static void main(String[] args) {
        int a = (new Solution343_3()).integerBreak(10);
        System.out.println(a);
    }

}
