package Max_Area_11;

public class Solution {

    public int maxArea(int[] height) {

        int l = 0, r = height.length - 1;
        int aera = calAera(height, l, r); // 计算最大容量
        while(l < r) {
            if(height[l] <= height[r]) {
                l++;
                aera = Math.max(aera, calAera(height, l, r));
            }
            else {
                r--;
                aera = Math.max(aera, calAera(height, l, r));
            }
        }

        return aera;
    }

    // 计算所选两根轴的容积
    private int calAera(int[] height, int i, int j) {

        return Math.abs(j - i) * Math.min(height[i], height[j]);
    }

}
