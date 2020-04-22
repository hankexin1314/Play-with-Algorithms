package Recursion_BackTracking;

// 迭代


import java.util.ArrayList;
import java.util.List;

public class Solution46_3 {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        res.add(new ArrayList<Integer>());
        for(int i = 0; i < nums.length; i++) {
            List<List<Integer>> tmpRes = new ArrayList<>();
            for(int insertPos = 0; insertPos <= i; insertPos++) {
                for(List<Integer> arr : res) {
                    List<Integer> arrCopy = new ArrayList<>(arr);
                    arrCopy.add(insertPos, nums[i]);
                    tmpRes.add(arrCopy);
                }
            }
            res = tmpRes;
        }

        return res;
    }

}
