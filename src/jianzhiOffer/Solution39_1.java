package jianzhiOffer;

import java.util.HashMap;

public class Solution39_1 {

    public int majorityElement(int[] nums) {

        int max = nums.length / 2;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums) {
            int count = map.getOrDefault(num, 0) + 1;
            if(count > max) return num;
            map.put(num, count);
        }

        throw new IllegalStateException("没有解");
    }
}
