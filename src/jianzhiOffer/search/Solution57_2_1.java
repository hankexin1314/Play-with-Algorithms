package jianzhiOffer.search;

import java.util.ArrayList;
import java.util.List;

public class Solution57_2_1 {

    public int[][] findContinuousSequence(int target) {

        int l = 1, r = 2;
        List<int[]> res = new ArrayList<>();
        while(l < r && r <= target / 2 + 1) {
            int sum = (l + r) * (r - l + 1) / 2;
            if(sum == target) {
                int[] tmp = new int[r - l + 1];
                for(int i = l; i <= r; i++)
                    tmp[i - l] = i;
                res.add(tmp);
                l ++;
                r ++;
            }
            else if(sum > target) l++;
            else r++;
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        (new Solution57_2_1()).findContinuousSequence(9);
    }
}
