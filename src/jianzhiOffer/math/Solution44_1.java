package jianzhiOffer.math;

public class Solution44_1 {

    public int findNthDigit(int n) {

        if(n == 0) return 0;
        int l = 1; // 几位数
        long count = 9; // l位数的个数
        long numCount = count * l; // 数字的个数
        while(n > numCount) {
            n -= numCount;
            l ++;
            count *= 10;
            numCount = count * l;
        }

        int num = (int)(Math.pow(10, l - 1)) + (n - 1) / l;
        return Integer.toString(num).charAt((n - 1) % l) - '0';
    }

    public int findNthDigit2(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.
    }

    public static void main(String[] args) {
        int a = (new Solution44_1()).findNthDigit(100);
        int b = (new Solution44_1()).findNthDigit2(100);
    }
}
