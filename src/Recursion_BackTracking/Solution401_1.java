package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.List;

public class Solution401_1 {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        for(int i = 0; i <= num; i++) {
            List<Integer> hour = new ArrayList<>();
            List<Integer> min = new ArrayList<>();
            helper(4, i, 0, hour, 0);
            helper(6, num-i, 0, min, 0);
            for(int h: hour) {
                if(h < 12) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(h);
                    sb.append(":");
                    int len = sb.length();
                    for(int m: min) {
                        if(m < 60) {
                            if(m < 10) sb.append("0");
                            sb.append(m);
                            res.add(sb.toString());
                            sb.setLength(len);
                        }
                    }
                }
            }
        }
        return res;
    }

    // pos 表示二进制有多少位 小时为4 分钟为6
    // num 表示有多少个灯亮
    // res 为结果集
    // sum 用来保存subRes
    private void helper(int pos, int num, int start,  List<Integer> res, int sum) {
        if(num == 0) {
            res.add(sum);
            return;
        }
        for(int i = start; i < pos - num + 1; i++)
            helper(pos, num - 1, i + 1, res, sum + (int)(Math.pow(2, i)));
    }

    public static void main(String[] args) {
        List<String> res = (new Solution401_1()).readBinaryWatch(1);
    }
}
