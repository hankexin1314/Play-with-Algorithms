package Dynamic_Programming;

import java.util.Arrays;
import java.util.List;

// 记忆化搜索
public class Solution139_2 {

    int[] memo;
    public boolean wordBreak(String s, List<String> wordDict) {
        memo = new int[s.length() + 1];
        Arrays.fill(memo, -1); // -1 表示未访问 0为false 1 为true
        return helper(s, wordDict, s.length() - 1);
    }

    // index 指示str判断到第几个字符了
    private boolean helper(String s, List<String> wordDict, int index) {

        if(index < 0) return true;
        if(memo[index] == -1) {
            for (int i = index; i >= 0; i--) {
                if (wordDict.contains(s.substring(i, index + 1)))
                    if (helper(s, wordDict, i - 1)) memo[index] = 1;
            }
            if(memo[index] == -1) memo[index] = 0;
        }

        return memo[index] == 1;
    }
}
