package Contains_Nearby_Duplicate_219;

import java.util.HashMap;
import java.util.HashSet;

public class Solution {

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        int l = 0;
        int r = Math.min(k, nums.length - 1); // [l...k]
        HashSet<Integer> set = new HashSet<>();
        for(int i = l; i <= r; i++) {
            if(set.contains(nums[i]))
                return true;
            else
                set.add(nums[i]);
        }

        while(r + 1 < nums.length) {

            set.remove(nums[l]);
            l++;
            r++;
            if(set.contains(nums[r]))
                return true;
            set.add(nums[r]);
        }

        return false;
    }
}
