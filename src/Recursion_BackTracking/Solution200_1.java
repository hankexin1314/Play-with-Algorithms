package Recursion_BackTracking;

public class Solution200_1 {
    private int height;
    private int width;
    private int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int numIslands(char[][] grid) {

        int res = 0;
        if(grid == null || grid.length == 0 || grid[0].length == 0) return res;
        height = grid.length;
        width = grid[0].length;

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if(grid[y][x] == '1') {
                    res++;
                    floodFill(grid, x, y);
                }
            }
        }

        return res;
    }

    // 将与这个岛屿相邻的所有岛屿标记
    private void floodFill(char[][] grid, int x, int y) {

        if(grid[y][x] == '1') {
            grid[y][x] ^= 256;
            for(int i = 0; i < 4; i++) {
                int newX = x + d[i][0];
                int newY = y + d[i][1];
                if(isValid(newX, newY))
                    floodFill(grid, newX, newY);
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public static void main(String[] args) {
        char a = '1';
        System.out.println((char)a);
        System.out.println((char)a^256);
    }
}
