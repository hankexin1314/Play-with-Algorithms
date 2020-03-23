package Merge_88;

import java.util.Arrays;
// 使用辅助空间
public class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int[] aux = Arrays.copyOfRange(nums1, 0, m);
        int i = 0, j = 0;
        while(i < m && j < n) {
            if(aux[i] <= nums2[j])
                i++;
            else
                nums1[]
        }
    }
}
