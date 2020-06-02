package jianzhiOffer.math;

import java.util.Arrays;

public class Solution43_1 {

    public int countDigitOne(int n) {

        int res = 0, digit = 1;
        int high = n / 10, cur = n % 10, low = 0;
        while(high != 0 || cur != 0) {
            if(cur == 0) res += high * digit;
            else if(cur == 1) {
                res += (low + 1);
                res += high * digit;
            }
            else res += (high + 1) * digit;

            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }

        return res;
    }
}
