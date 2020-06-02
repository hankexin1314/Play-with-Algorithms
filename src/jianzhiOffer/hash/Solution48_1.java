package jianzhiOffer.hash;

import java.util.HashMap;

public class Solution48_1 {

    public int lengthOfLongestSubstring(String s) {

        int max = 0; // 最大字符串长度
        int start = 0; // 当前字符串的起点
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if(map.containsKey(c) && map.get(c) >= start)
                start = map.get(c) + 1;

            max = Math.max(i - start + 1, max);
            map.put(chars[i], i);
        }

        return max;
    }
}
