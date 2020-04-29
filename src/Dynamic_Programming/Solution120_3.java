package Dynamic_Programming;

import java.util.Arrays;
import java.util.List;

// 动态规划 自底向上

public class Solution120_3 {


    public int minimumTotal(List<List<Integer>> triangle) {

        if(triangle == null) return 0;
        int row = triangle.size(); // 行数
        int[] memo = new int[row];
        // 将最后一行放入 初始化memo
        for(int i = 0; i < row; i++) memo[i] = triangle.get(row - 1).get(i);
        for(int i = row - 2; i >= 0; i--) {
            for(int j = 0; j <= i; j++) {
                memo[j] = triangle.get(i).get(j) + Math.min(memo[j], memo[j + 1]);
            }
        }

        return memo[0];
    }


}
