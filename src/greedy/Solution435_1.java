package greedy;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;

public class Solution435_1 {

    public int eraseOverlapIntervals(int[][] intervals) {

        if(intervals.length == 0) return 0;
//        Arrays.sort(intervals, (a, b) -> a[0] >= b[0] ? 1: -1);
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) { // 按照起始点排序
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        int dp[] = new int[intervals.length]; // 使用[0, i]元素 能构成的最长不重叠子序列
        dp[0] = 1;
        for(int i = 1; i < intervals.length; i++) {
            for(int j = 0; j < i; j++) {
                if(intervals[i][0] >= intervals[j][1])
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
            }
        }
        int res = 0;
        for(int num: dp) res = Math.max(res, num);

        return intervals.length - res;
    }

    private static class Demo {
        int val;

        public Demo(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Demo{" +
                    "val=" + val +
                    '}';
        }
    }
    public static void main(String[] args) {
        Demo[] ds = new Demo[2];
        ds[0] = new Demo(0);
        ds[1] = new Demo(1);

        Arrays.sort(ds, new Comparator<Demo>() {
            @Override
            public int compare(Demo o1, Demo o2) {
                return o2.val - o1.val;
            }
        });
        System.out.println(Arrays.toString(ds));
    }
}
