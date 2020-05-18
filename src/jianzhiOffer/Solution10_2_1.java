package jianzhiOffer;

public class Solution10_2_1 {

    public int numWays(int n) {

        if(n < 2) return 1;
        int a = 1, b = 1;
        for(int i = 2; i <= n; i++) {
            int sum = (a + b) % 1000000007;
            b = a;
            a = sum;
        }
        return a;
    }


}
