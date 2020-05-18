package jianzhiOffer;

import java.math.BigInteger;

public class Solution14_2_1 {

    public int cuttingRope(int n) {

        if(n < 4) return n - 1;
        if(n == 4) return 4;
        long res = 1;
        while(n > 4) {
            res *= 3;
            res %= 1000000007;
            n -= 3;
        }
        return (int)(res * n % 1000000007);
    }


    public static void main(String[] args) {

    }
}
