package 动态规划.p70_爬楼梯;

public class Solution {

    public int climbStairs(int n) {

        if(n < 2) return 1;
        int a = 1, b = 1;
        for(int i = 2; i <= n; i++) {
            int tmp = a;
            a = b;
            b = tmp + b;
        }

        return b;
    }
}
