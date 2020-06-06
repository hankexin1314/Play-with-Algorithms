package jianzhiOffer.bit;

public class Solution56_2_1 {

    public int singleNumber(int[] nums) {

        int one = 0, two = 0;
        for(int num: nums) {
            one = one ^ num & ~two;
            two = two ^ num & ~one;
        }

        return one;
    }
}
