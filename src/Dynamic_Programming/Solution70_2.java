package Dynamic_Programming;

// 记忆化搜索

import java.util.ArrayList;
import java.util.Arrays;

public class Solution70_2 {

    int[] memo; // memo[n]表示爬上n节楼梯有几种方法
    boolean flag = true;

    public int climbStairs(int n) {

        if(flag) {
            memo = new int[n + 1];
            Arrays.fill(memo, -1);
            flag = false;
        }
        if(n == 0) return 1;
        if(n == 1) return 1;
        if(memo[n] == -1)
            memo[n] = climbStairs(n - 1) + climbStairs(n - 2);
        return memo[n];
    }
}
