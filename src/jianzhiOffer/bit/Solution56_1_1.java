package jianzhiOffer.bit;

public class Solution56_1_1 {

    public int[] singleNumbers(int[] nums) {

        int sum = 0;
        for(int num: nums) sum ^= num;
        int mask = 1;
        while((sum & mask) == 0) mask <<= 1;
        int[] res = new int[2];
        for(int num: nums) {
            if((num & mask) == 0)
                res[0] ^= num;
            else
                res[1] ^= num;
        }

        return res;
    }

}
