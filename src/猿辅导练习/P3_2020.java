package 猿辅导练习;

import java.util.Arrays;
import java.util.Scanner;

public class P3_2020 {

    public static void solution(int[] nums, int s) {
        int l = 0, r = 0;
        int sum = nums[0];
        int res = 0;
        while(true) {
            if(sum <= s) {
                res = Math.max(res, r - l + 1);
                r ++;
                if(r < nums.length) sum += nums[r];
                else break;
            }
            else {
                sum -= nums[l];
                l ++;
            }
        }
        System.out.print(res);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");
        int n = Integer.parseInt(strs[0]), s = Integer.parseInt(strs[0]);
        strs = sc.nextLine().trim().split(" ");
        int[] nums = new int[n];
        for(int i = 0; i < n; i++)
            nums[i] = Integer.parseInt(strs[i]);
        solution(nums, s);
        
    }
}
