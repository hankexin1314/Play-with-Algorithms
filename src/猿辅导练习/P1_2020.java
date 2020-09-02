package 猿辅导练习;

import java.util.Scanner;

public class P1_2020 {

    public static void solution(int[][] nums, int m, int n) {
        if(m <= 0 || n <= 0) return;
        int l = 0, r = n - 1, up = 0, bottom = m - 1;
        while(l <= r && up <= bottom) {
            // 向下
            for(int i = up; i <= bottom; i++) {
                System.out.print(nums[i][l]);
                System.out.print(" ");
            }
            l ++;
            if(l > r) break;
            // 向右
            for(int i = l; i <= r; i++) {
                System.out.print(nums[bottom][i]);
                System.out.print(" ");
            }
            bottom --;
            if(bottom < up) break;
            // 向上
            for(int i = bottom; i >= up; i--) {
                System.out.print(nums[i][r]);
                System.out.print(" ");
            }
            r --;
            if(r < l) break;
            // 向左
            for(int i = r; i >= l; i--) {
                System.out.print(nums[up][i]);
                System.out.print(" ");
            }
            up ++;
            if(up > bottom) break;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");
        int m = Integer.parseInt(strs[0]), n = Integer.parseInt(strs[1]);
        int[][] ids = new int[m][n];
        int line = 0;
        while(sc.hasNextLine()) {
            String[] nums = sc.nextLine().split(" ");
            for(int i = 0; i < n; i++)
                ids[line][i] = Integer.parseInt(nums[i]);
            line ++;
        }
        solution(ids, m, n);
    }
}
