package jianzhiOffer;

public class Solution16_2 {

    public double myPow(double x, int n) {

        if(x == 0) return 0;
        long tmp = n;
        tmp = tmp > 0 ? tmp : -tmp;
        double res = 1.0;

        while(tmp > 0) {
            if((tmp & 1) == 1) res *= x;
            x *= x;
            tmp >>= 1;
        }
        return n > 0 ? res : 1 / res;
    }


}
