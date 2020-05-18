package jianzhiOffer;

public class Solution16_1 {

    public double myPow(double x, int n) {

        long aux = Math.abs(n);
        double res = helper(x, aux);

        return n > 0 ? res : 1 / res;
    }

    private double helper(double x, long n) {

        if(n == 0) return 1;
        double res = helper(x, n / 2);
        if(n % 2 == 0) return res * res;
        else return x * res * res;
    }
}
