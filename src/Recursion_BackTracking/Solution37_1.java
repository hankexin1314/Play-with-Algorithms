package Recursion_BackTracking;

public class Solution37_1 {
    public void solveSudoku(char[][] board) {
        helper(board, 0, 0);
    }

    // 从 row col开始寻找
    private boolean helper(char[][] board, int row, int col) {

        for(int i = row; i < 9; i++, col = 0) {
            for(int j = col; j < 9; j++) {
                if(board[i][j] == '.') {
                    for(char num = '1'; num <= '9'; num++) {
                        if(isValid(board, i, j, num)) {
                            board[i][j] = num;
                            if(helper(board, i, j+1))
                                return true;
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char num) {

        for(int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) return false;
        }
        int m = row / 3;
        int n = col / 3;
        for(int i = 3 * m; i < 3 * m + 3; i++) {
            for(int j = 3 * n; j < 3 * n + 3; j++) {
                if(board[i][j] == num) return false;
            }
        }

        return true;
    }
}
