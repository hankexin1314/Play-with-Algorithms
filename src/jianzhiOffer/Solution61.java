package jianzhiOffer;

public class Solution61 {

    public boolean isStraight(int[] nums) {

        int[] aux = new int[14];
        int l = 14, r = -1;
        for(int num: nums) {
            aux[num] ++;
            if(aux[num] > 1 && num != 0) return false;
            if(num != 0) {
                l = Math.min(l, num);
                r = Math.max(r, num);
            }
        }
        if(r - l + 1 > 5) return false;
        return true;
    }
}
