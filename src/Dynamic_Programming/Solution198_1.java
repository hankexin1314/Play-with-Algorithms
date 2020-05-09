package Dynamic_Programming;

// 动态规划

public class Solution198_1 {

    public int rob(int[] nums) {

        int L = nums.length;
        if(L == 0) return 0;
        int[] memo = new int[L]; // [0, i] 所得到的最大利益
        for(int i = 0; i < L; i++) memo[i] = nums[i] +
            Math.max( i - 2 >= 0 ? memo[i - 2] : 0, //
                    i - 3 >= 0 ? memo[i - 3] : 0);

        return Math.max(memo[L - 1], L - 2 >= 0 ? memo[L - 2]: 0);
    }


}
