package Contains_Nearby_Duplicate_219;

import java.util.HashMap;
import java.util.HashSet;

public class Solution2 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {

            if(set.contains(nums[i]))
                return true;
            set.add(nums[i]);

            if(set.size() == k + 1)
                set.remove(nums[i - k]);
        }

        return false;
    }
}
