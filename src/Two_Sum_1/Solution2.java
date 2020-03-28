package Two_Sum_1;

import java.util.HashMap;
import java.util.HashSet;

// 时间复杂度 n
public class Solution2 {

    public int[] twoSum(int[] nums, int target) {

        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = nums.length - 1 - 1; i >= 0; i--) {
            map.put(nums[i + 1], i + 1);
            int ans = target - nums[i];
            if(map.containsKey(ans)) {
                res[0] = i;
                res[1] = map.get(ans);
                break;
            }
        }
        return res;
    }
}
