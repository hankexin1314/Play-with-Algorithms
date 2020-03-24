package Merge_88;

import java.util.Arrays;
// 使用辅助空间 双指针 从前往后
// 空间复杂度O(m)
// 时间复杂度O(m + n)
public class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int[] aux = Arrays.copyOfRange(nums1, 0, m);

        int i = 0, j = 0; // i为aux的索引, j为nums2的索引
        for(int k = 0; k < m + n; k++) { // k为nums1的索引
            if(i >= m) {
                nums1[k] = nums2[j];
                j++;
            }
            else if(j >= n) {
                nums1[k] = aux[i];
                i++;
            }
            else if(aux[i] <= nums2[j]) {
                nums1[k] = aux[i];
                i++;
            }
            else {
                nums1[k] = nums2[j];
                j++;
            }
        }
    }
}
