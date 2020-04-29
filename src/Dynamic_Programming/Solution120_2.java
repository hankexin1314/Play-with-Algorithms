package Dynamic_Programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 递归 记忆化搜索

public class Solution120_2 {

    Integer[][] memo;

    public int minimumTotal(List<List<Integer>> triangle) {

        if(triangle == null) return 0;
        memo = new Integer[triangle.size()][triangle.size()];
        return calcPath(triangle, 0, 0);
    }

    // 返回从指定row col开始的到底部的最短路径
    private int calcPath(List<List<Integer>> triangle, int row, int col) {

        int num = triangle.get(row).get(col);
        if(row == triangle.size() - 1) return num;
        if(memo[row][col] == null)
            memo[row][col] = num + Math.min(calcPath(triangle, row + 1, col), calcPath(triangle, row + 1, col + 1));
        return memo[row][col];
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[2];
        System.out.println(Arrays.toString(a));
    }
}
