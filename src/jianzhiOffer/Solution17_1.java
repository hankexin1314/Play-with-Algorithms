package jianzhiOffer;

public class Solution17_1 {

    public int[] printNumbers(int n) {

        if(n <= 0) return null;
        int[] res = new int[(int)Math.pow(10, n) - 1];
        for(int i = 0; i < res.length; i++) res[i] = i + 1;

        return res;
    }
}
