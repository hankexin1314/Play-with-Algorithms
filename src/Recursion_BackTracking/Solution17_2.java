package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// 迭代实现
// 避免使用辅助空间
public class Solution17_2 {
    public List<String> letterCombinations(String digits) {
        HashMap<Character, List<Character>> map = new HashMap<>();
        List<String> res = new LinkedList<>();
        if(digits.length() == 0)
            return res;
        res.add("");
        int charCount = 0;
        for(int i = 2; i <= 9; i++) {
            List<Character> l = new ArrayList<>();
            l.add((char)('a' + charCount++));
            l.add((char)('a' + charCount++));
            l.add((char)('a' + charCount++));
            if(i == 9 || i == 7)
                l.add((char)('a' + charCount++));
            map.put((char)('0' + i), l);
        }

        char[] cs = digits.toCharArray();
        for(char c: cs) {
            List<Character> l = map.get(c);
            int i = res.size();
            int count = 0;
            while(count != i) {
                String curRes = res.get(0);
                res.remove(0);
                count ++;
                for(char cl: l) {
                    StringBuilder sb = new StringBuilder(curRes);
                    sb.append(cl);
                    res.add(sb.toString());
                }
            }
        }

        return res;
    }
}
