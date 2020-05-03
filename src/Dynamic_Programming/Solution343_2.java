package Dynamic_Programming;

// 动态规划 优化
public class Solution343_2 {
    public int integerBreak(int n) {

        int[] memo = new int[n + 1];
        memo[1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <= i / 2; j++) { // 这里优化
                memo[i] = Math.max(memo[i], Math.max(j, memo[j]) * Math.max(memo[i - j], i - j));
            }
        }

        return memo[n];
    }

    public static void main(String[] args) {
        int a = (new Solution343_2()).integerBreak(10);
        System.out.println(a);
    }

}
