package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// 迭代实现
// 避免使用辅助空间
public class Solution17_3 {
    static HashMap<Character, List<Character>> map = new HashMap<>();
    static {
        int charCount = 0;
        for (int i = 2; i <= 9; i++) {
            List<Character> l = new ArrayList<>();
            l.add((char) ('a' + charCount++));
            l.add((char) ('a' + charCount++));
            l.add((char) ('a' + charCount++));
            if (i == 9 || i == 7)
                l.add((char) ('a' + charCount++));
            map.put((char) ('0' + i), l);
        }
    }
    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        if(digits.length() == 0)
            return res;
        char[] cs = digits.toCharArray();
        helper(cs, 0, "", res);
        return res;
    }

    // arr 为数字字符串转化成的字符数组 index 表示从第几个数字开始处理 subStr 表示此时组成的字符串
    // res 保存结果
    private void helper(char[] arr, int index, String subStr, List<String> res) {
        if(index == arr.length) {
            res.add(subStr.toString());
            return;
        }
        char c = arr[index];
        List<Character> l = map.get(c);
        for(char lc: l) {
            helper(arr, index + 1, subStr + lc, res);
        }
    }
}
