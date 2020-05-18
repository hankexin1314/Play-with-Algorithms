package jianzhiOffer;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

// dfs
public class Solution13_2 {



    public int movingCount(int m, int n, int k) {

        int[] memo = new int[100]; // memo[n] 表示n的位数之和
        for(int i = 0; i < 100; i++) memo[i] = bSum(i);
        boolean[][] used = new boolean[m][n];
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(0, 0));
        int res = 0;
        while(!queue.isEmpty()) {
            Pair<Integer, Integer> pair = queue.poll();
            int row = pair.getKey(), col = pair.getValue();
            if(row >= m || col >= n || used[row][col] || memo[row] + memo[col] > k) continue;
            used[row][col] = true;
            res ++;
            queue.offer(new Pair<>(row + 1, col));
            queue.offer(new Pair<>(row, col + 1));
        }
        return res;
    }


    // 数位之和
    private int bSum(int num) {
        int res = 0;
        while (num != 0) {
            res += num % 10;
            num = num / 10;
        }
        return res;
    }
}
