package 贪心.p455_分发饼干;

import java.util.Arrays;

public class Solution {

    public int findContentChildren(int[] g, int[] s) {

        Arrays.sort(g);
        Arrays.sort(s);

        int count = 0;
        int index = 0;
        for(int i = 0; index < g.length && i < s.length; i++) {
            if(g[index] <= s[i]) {
                count ++;
                index ++;
            }
        }

        return count;
    }
}
