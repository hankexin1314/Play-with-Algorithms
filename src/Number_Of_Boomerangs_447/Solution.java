package Number_Of_Boomerangs_447;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Solution {

    public int numberOfBoomerangs(int[][] points) {

        int res = 0;
        for(int i = 0; i < points.length; i++) {
            // key为距离 value为点的数量
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int j = 0; j < points.length; j++) {
                if(i == j)
                    continue;
                int dis = distance(points, i, j);
                int count = map.getOrDefault(dis, 0);
                map.put(dis, count + 1);
            }

            for(int key: map.keySet()) {
                int count = map.get(key);
                res += ((count - 1) * count);
            }
        }
        return res;
    }

    // 计算两索引对应的点之间的距离的平方
    private int distance(int[][] points, int i, int j) {

        int[] x = points[i], y = points[j];
        int sum = 0;
        for(int k = 0; k < x.length; k++)
                sum += Math.pow(x[k] - y[k], 2);

        return sum;
    }

}
