package Recursion_BackTracking;

import java.util.Arrays;

public class Solution79_1 {

    private int width; // 宽
    private int height; // 高
    private boolean used[][]; // 是否访问过

    public boolean exist(char[][] board, String word) {

        if(board.length == 0 || board[0].length == 0 || word.length() == 0) return false;
        height = board.length;
        width = board[0].length;
        used = new boolean[height][width];
        for(int row = 0; row < height; row++) {
            for(int col = 0; col < width; col++) {
                used = new boolean[height][width];
                if(helper(board, word, 0, row, col))
                    return true;
            }
        }

        return false;
    }

    // col row 表示当前指针的位置 charAt表示要判断的char的index
    private boolean helper(char[][] board, String word, int charAt, int row, int col) {
        char c = word.charAt(charAt);
        if(charAt == word.length() - 1) return c == board[row][col];
        if(board[row][col] == c) {
            used[row][col] = true;
            if (isOk(row - 1, col)) { // 上
                if(helper(board, word, charAt + 1, row - 1, col))
                    return true;
            }
            if (isOk(row + 1, col)) { // 下
                if(helper(board, word, charAt + 1, row + 1, col))
                    return true;
            }
            if (isOk(row, col - 1)) { // 左
                if(helper(board, word, charAt + 1, row, col - 1))
                    return true;
            }
            if (isOk(row, col + 1)) { // 右
                if(helper(board, word, charAt + 1, row, col + 1))
                    return true;
            }
            used[row][col] = false;
        }
        return false;
    }

    // 判断指定 row col的路是否可行
    private boolean isOk(int row, int col) {
        if(row >= height || col >= width || row < 0 || col < 0) return false;
        if(used[row][col]) return false;
        return true;
    }

}
