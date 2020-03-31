package Contains_Nearby_Almost_Duplicate_220;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class Solution {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        TreeSet<Integer> set = new TreeSet<>(); // 保存距离之差
        for(int i = 0; i < nums.length; i++) {
            if(set.ceiling(nums[i] - t) != null && set.ceiling(nums[i] - t) <= nums[i] + t)
                return true;
            set.add(nums[i]);
            if(set.size() == k + 1)
                set.remove(nums[i - k]);
        }

        return false;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 1};
        boolean res = (new Solution()).containsNearbyAlmostDuplicate(nums, 3, 0);
    }

}
