package Dynamic_Programming;

import java.util.Arrays;

public class Solution279_1 {


    public int numSquares(int n) {

        int[] memo = new int[n + 1];
        memo[0] = 0;
        for(int i = 1; i < n + 1; i++) {

            int min = n;
            for(int j = 1; j * j <= i; j ++) {
                min = Math.min(min, 1 + memo[i - j * j]);
            }
            memo[i] = min;
        }

        return memo[n];
    }
}
