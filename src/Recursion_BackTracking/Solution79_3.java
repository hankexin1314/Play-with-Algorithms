package Recursion_BackTracking;

// 优化速度
public class Solution79_3 {

    private int width; // 宽
    private int height; // 高
    private int d[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public boolean exist(char[][] board, String word) {

        if(board.length == 0 || board[0].length == 0) return false;
        assert word.length() > 0;
        char[] chars = word.toCharArray(); // 使用字符数组
        height = board.length;
        width = board[0].length;
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(searchWord(board, chars, 0, i, j))
                    return true;
            }
        }
        return false;
    }

    // col row 表示当前指针的位置 charAt表示要判断的char的index
    private boolean searchWord(char[][] board, char[] chars, int charAt, int row, int col) {
        char c = chars[charAt];
        if(charAt == chars.length - 1) return c == board[row][col];
        if(c == board[row][col]) {
            board[row][col] ^= 256;
            for(int i = 0; i < 4; i ++) {
                int newRow = row + d[i][0];
                int newCol = col + d[i][1];
                if(inAera(newRow, newCol))
                    if(searchWord(board, chars, charAt + 1, newRow, newCol))
                        return true;
            }
            board[row][col] ^= 256;
        }
        return false;
    }

    // 判断指定 row col的路是否可行
    private boolean inAera(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

}
