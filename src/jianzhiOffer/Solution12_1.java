package jianzhiOffer;

import java.util.Arrays;

public class Solution12_1 {

    int[][] ds = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    boolean[][] used;

    public boolean exist(char[][] board, String word) {

        if(board == null || word == null || board.length == 0 || board[0].length == 0)
            return false;
        if (word.length() == 0) return true;
        int m = board.length, n = board[0].length;
        used = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(dfs(board, word, 0, i, j))
                    return true;
            }
        }
        return false;
    }

    /**
     * 以row col为起点，以String从index开始的部分为目标，是否可以组成
     * @param board
     * @param word
     * @param index 指向第几个字母
     * @param row  起始位置
     * @param col
     * @return
     */
    private boolean dfs(char[][] board, String word, int index, int row, int col) {

        if(index == word.length()) return true;
        if (isValid(row, col) && word.charAt(index) == board[row][col] && !used[row][col]) {
            used[row][col] = true;
            for (int[] d : ds) {
                int newRow = row + d[0];
                int newCol = col + d[1];
                if (dfs(board, word, index + 1, newRow, newCol))
                    return true;
            }
            used[row][col] = false;
        }
        return false;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < used.length && col >= 0 && col < used[0].length;
    }
}
