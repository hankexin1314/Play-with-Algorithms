package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class Solution435_2 {

    public int eraseOverlapIntervals(int[][] intervals) {

        if(intervals.length == 0) return 0;
//        Arrays.sort(intervals, (a, b) -> a[0] >= b[0] ? 1: -1);
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) { // 按照结尾点排序
                if(o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });
        int pre = 0;
        int res = 1;
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] >= intervals[pre][1]) {
                pre = i;
                res ++;
            }
        }

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
