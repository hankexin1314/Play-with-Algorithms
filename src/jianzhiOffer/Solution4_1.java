package jianzhiOffer;

public class Solution4_1 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = 0, n = matrix[0].length - 1;
        while(m < matrix.length && n >= 0) {
            if(matrix[m][n] == target) return true;
            else if(matrix[m][n] > target) n--;
            else m++;
        }
        return false;
    }
}
