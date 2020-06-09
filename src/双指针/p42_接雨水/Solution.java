package 双指针.p42_接雨水;

public class Solution {
    public int trap(int[] height) {

        if(height == null || height.length < 3) return 0;
        int res = 0;
        int lMax = 0, rMax = 0;
        int l = 0, r = height.length - 1;
        while(l < r) {
            if(height[l] < height[r]) {
                if(height[l] >= lMax)
                    lMax = height[l];
                else
                    res += lMax - height[l];
                l ++;
            }
            else {
                if(height[r] >= rMax)
                    rMax = height[r];
                else
                    res += rMax - height[r];
                r --;
            }
        }
        return res;
    }
}
