package Max_Points_149;

import java.util.HashMap;

public class Solution {

    public int maxPoints(int[][] points) {

        if(points.length < 3)
            return points.length;

        // key为约分后的dx
        HashMap<String, Integer> map = new HashMap<>();
        int res = 0;
        for(int i = 0; i < points.length; i++) {

            map.clear();
            int max = 1;
            int overlap = 0; // 用来记录和点i重复的点
            for(int j = i + 1; j < points.length; j++) {

                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];
                if(dx == 0 && dy == 0) {
                    overlap++;
                    continue;
                }
                else {
                    int gcd = getGCD(dx, dy);
                    dx /= gcd;
                    dy /= gcd;
                    int count = map.getOrDefault(dy + ":" + dx, 1);
                    map.put(dy + ":" + dx, count + 1);
                }
                max = Math.max(max, map.get(dy + ":" + dx));
            }
            res = Math.max(res, max + overlap);
        }

        return res;
    }

    // 求最大公约数
    private int getGCD(int x, int y) {

        while(y != 0) {
            int tmp = x % y;
            x = y;
            y = tmp;
        }

        return x;
    }
}
