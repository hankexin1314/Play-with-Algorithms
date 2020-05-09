package Dynamic_Programming;

// 动态规划 优化存储空间

public class Solution198_4 {

    public int rob(int[] nums) {

        int L = nums.length;
        if(L == 0) return 0;
        int p1 = 0, p2 = 0; // 往前数1位 往前数2位
        for(int i = 0; i < L; i++) {
            int tmp = p1;
            p1 = Math.max(nums[i] + p2, p1);
            p2 = tmp;
        }

        return p1;
    }


}
