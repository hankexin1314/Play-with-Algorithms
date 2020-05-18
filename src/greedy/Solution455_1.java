package greedy;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Solution455_1 {

    public int findContentChildren(int[] g, int[] s) {

        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        int start = 0;
        for(int i = 0; i < g.length; i++) {
            for(int j = start; j < s.length; j++) {
                if(s[j] >= g[i]) {
                    start = j + 1;
                    res ++;
                    break;
                }
            }
        }

        return res;
    }
}
