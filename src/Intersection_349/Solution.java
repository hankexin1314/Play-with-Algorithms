package Intersection_349;

// 时间复杂度 nlogn  n为nums1 和nums2的尺寸中较大的那个
// 空间复杂度 n

import java.util.TreeSet;

public class Solution {

    public int[] intersection(int[] nums1, int[] nums2) {

        TreeSet<Integer> resSet = new TreeSet<>();
        TreeSet<Integer> set = new TreeSet<>();

        for(int i: nums1)
            set.add(i);

        for(int i: nums2) {
            if(set.contains(i))
                resSet.add(i);
        }
        int[] res = new int[resSet.size()];
        int index = 0;
        for(int i: resSet)
            res[index++] = i;

        return res;
    }
}
