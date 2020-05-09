package Dynamic_Programming;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 动态规划
public class Solution322_2 {

    public int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++) {
            for(int coin: coins) {
                if(i >= coin) dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
