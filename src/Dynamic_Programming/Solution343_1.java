package Dynamic_Programming;

// 动态规划
public class Solution343_1 {
    public int integerBreak(int n) {

        int[] memo = new int[59];
        memo[1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 1; j < i; j++) {
                memo[i] = Math.max(memo[i], j * Math.max(memo[i - j], i - j));
            }
        }

        return memo[n];
    }

    public static void main(String[] args) {
        int a = (new Solution343_1()).integerBreak(10);
        System.out.println(a);
    }

}
