package jianzhiOffer;

public class Solution29_1 {

    public int[] spiralOrder(int[][] matrix) {

        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[0];
        int m = matrix.length, n = matrix[0].length;
        int[] res = new int[m * n];
        int l = 0, r = n - 1, t = 0, b = m - 1;
        int index = 0; // 向res中写答案
        while(true) {
            // 向右
            for(int i = l; i <= r; i++) {
                res[index] = matrix[t][i];
                index++;
            }
            t++;
            if(t > b) break;
            // 向下
            for(int i = t; i <= b; i++) {
                res[index] = matrix[i][r];
                index++;
            }
            r--;
            if(r < l) break;
            // 向左
            for(int i = r; i >= l; i--) {
                res[index] = matrix[b][i];
                index++;
            }
            b--;
            if(b < t) break;
            // 向上
            for(int i = b; i >= t; i--) {
                res[index] = matrix[i][l];
                index++;
            }
            l++;
            if(l > r) break;
        }

        return res;
    }
}
