package Stack_Queue;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 动态规划
public class Solution279_4 {

    public int numSquares(int n) {

        int[] dp = new int[n + 1]; // dp[i]代表组成数字i的完全平方数的个数
        Arrays.fill(dp, n + 1); // 最大不超过n
        dp[0] = 0;

        for(int i = 1; i < n + 1; i++) {
            int min = dp[i];
            for(int j = 1; i - j * j >= 0; j++)
                min = Math.min(min, dp[i - j * j] + 1);
            dp[i] = min;
        }

        return dp[n];
    }
}
