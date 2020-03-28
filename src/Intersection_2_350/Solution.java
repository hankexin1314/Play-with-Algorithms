package Intersection_2_350;

// 时间复杂度 n 如果使用普通的map就是nlogn
// 空间复杂度 n

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

public class Solution {

    public int[] intersection(int[] nums1, int[] nums2) {

        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> resList = new ArrayList<>();


        for(int i: nums1) {
            int count = map.getOrDefault(i, 0);
            map.put(i, count + 1);
        }

        for(int i: nums2) {
            int count = map.getOrDefault(i, 0);
            if(count > 0) {
                resList.add(i);
                map.put(i, count - 1);
            }

        }
        int[] res = new int[resList.size()];
        for(int i = 0; i < resList.size(); i++)
            res[i] = resList.get(i);

        return res;
    }
}
