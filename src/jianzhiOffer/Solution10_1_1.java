package jianzhiOffer;

public class Solution10_1_1 {

    public int fib(int n) {

        if(n < 2) return n;
        int[] fibs = new int[n + 1];
        fibs[0] = 0; fibs[1] = 1;
        for(int i = 2; i <= n; i++)
            fibs[i] = (fibs[i - 1] + fibs[i - 2]) % (1000000007);
        return fibs[n];
    }

    public static void main(String[] args) {
        System.out.println((new Solution10_1_1()).fib(45));
    }
}
