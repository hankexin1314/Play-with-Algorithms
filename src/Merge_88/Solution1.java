package Merge_88;


// 不使用辅助空间，从后往前
public class Solution1 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m - 1, j = n - 1; // i为nums1指针 j为nums2指针
        for(int k = m + n - 1; k >= 0; k--) { // k为写入结果的指针
            if(i < 0) {
                nums1[k] = nums2[j];
                j--;
            }
            else if(j < 0) {
                nums1[k] = nums1[i];
                i--;
            }
            else if(nums1[i] >= nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            }
            else {
                nums1[k] = nums2[j];
                j--;
            }
        }
    }

}
