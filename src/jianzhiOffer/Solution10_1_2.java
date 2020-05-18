package jianzhiOffer;

public class Solution10_1_2 {

    public int fib(int n) {

        if(n < 2) return n;
        int a = 1, b = 0;
        for(int i = 2; i <= n; i++) {
            int sum = (a + b) % 1000000007;
            b = a;
            a = sum;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println((new Solution10_1_2()).fib(45));
    }
}
