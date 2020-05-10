package Dynamic_Programming;

import java.util.List;

// 递归
public class Solution139_1 {

    public boolean wordBreak(String s, List<String> wordDict) {
        return helper(s, wordDict, 0);
    }

    // index 指示str判断到第几个字符了
    private boolean helper(String s, List<String> wordDict, int index) {

        if(index == s.length()) return true;
        boolean res = false;
        for(int i = index; i < s.length(); i++) {
            if(wordDict.contains(s.substring(index, i + 1)))
                if(helper(s, wordDict, i + 1)) return true;
        }

        return false;
    }
}
