package Recursion_BackTracking;

// 优化结构
public class Solution79_2 {

    private int width; // 宽
    private int height; // 高
    private boolean used[][]; // 是否访问过
    private int d[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public boolean exist(char[][] board, String word) {

        if(board.length == 0 || board[0].length == 0) return false;
        assert word.length() > 0;
        height = board.length;
        width = board[0].length;
        used = new boolean[height][width];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(searchWord(board, word, 0, i, j))
                    return true;
            }
        }
        return false;
    }

    // col row 表示当前指针的位置 charAt表示要判断的char的index
    private boolean searchWord(char[][] board, String word, int charAt, int row, int col) {
        char c = word.charAt(charAt);
        if(charAt == word.length() - 1) return c == board[row][col];
        if(c == board[row][col]) {
            used[row][col] = true;
            for(int i = 0; i < 4; i ++) {
                int newRow = row + d[i][0];
                int newCol = col + d[i][1];
                if(inAera(newRow, newCol) && !used[newRow][newCol])
                    if(searchWord(board, word, charAt + 1, newRow, newCol))
                        return true;
            }
            used[row][col] = false;
        }
        return false;
    }

    // 判断指定 row col的路是否可行
    private boolean inAera(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

}
