package Dynamic_Programming;

import java.util.Arrays;
import java.util.List;

// dp
public class Solution139_3 {


    public boolean wordBreak(String s, List<String> wordDict) {

        boolean[] dp = new boolean[s.length() + 1]; // dp[i]表示 前i个字符是否可以被拆分
        dp[0] = true;
        for(int i = 1; i <= s.length(); i++) {
            for(int start = i - 1; start >= 0; start--) {
                if (wordDict.contains(s.substring(start, i)) && dp[start]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }


}
