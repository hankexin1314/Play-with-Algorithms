package Dynamic_Programming;

import java.util.List;

// 递归 超出时间限制

public class Solution120_1 {
    public int minimumTotal(List<List<Integer>> triangle) {

        return calcPath(triangle, 0, 0);
    }

    // 返回从指定row col开始的到底部的最短路径
    private int calcPath(List<List<Integer>> triangle, int row, int col) {

        if(row == triangle.size()) return 0;
        return triangle.get(row).get(col) + Math.min(calcPath(triangle, row + 1, col), calcPath(triangle, row + 1, col + 1));
    }
}
