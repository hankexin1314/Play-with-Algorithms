package Four_Sum_Count_454;

import java.util.HashMap;

public class Solution {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i: C) {
            for(int j: D) {
                int count = map.getOrDefault(i + j, 0);
                map.put(i + j, count + 1);
            }
        }

        int res = 0;
        for(int i: A) {
            for(int j: B) {
                if(map.containsKey(-i-j))
                    res += map.get(-i-j);
            }
        }

        return res;
    }
}
