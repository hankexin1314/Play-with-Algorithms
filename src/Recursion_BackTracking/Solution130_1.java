package Recursion_BackTracking;

public class Solution130_1 {

    private int height;
    private int width;
    private int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void solve(char[][] board) {

        if(board == null || board.length == 0 || board[0].length == 0)
            return;
        height = board.length;
        width = board[0].length;

        for(int i = 0; i < height; i++) {
            if(board[i][0] == 'O') dfs(board, 0, i);
            if(board[i][width - 1] == 'O') dfs(board, width - 1, i);
        }

        for(int i = 0; i < width; i++) {
            if(board[0][i] == 'O') dfs(board, i, 0);
            if(board[height - 1][i] == 'O') dfs(board, i, height - 1);
        }

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(board[i][j] == 'O') board[i][j] = 'X';
                if(board[i][j] == '*') board[i][j] = 'O';
            }
        }
    }

    // 将与(x, y) 相邻的O全变为 *
    private void dfs(char[][] board, int x, int y) {

        board[y][x] = '*';
        for(int i = 0; i < 4; i++) {
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if(isValid(newX, newY) && board[newY][newX] == 'O')
                dfs(board, newX, newY);
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

}
