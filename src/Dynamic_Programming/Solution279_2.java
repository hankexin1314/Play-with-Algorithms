package Dynamic_Programming;

public class Solution279_2 {


    public int numSquares(int n) {

        int[] memo = new int[n + 1];
        memo[0] = 0;
        for(int i = 1; i < n + 1; i++) {

            int min = n;
            for(int j = (int) Math.sqrt(i); j >= 1; j --) {
                min = Math.min(min, 1 + memo[i - j * j]);
            }
            memo[i] = min;
        }

        return memo[n];
    }
}
