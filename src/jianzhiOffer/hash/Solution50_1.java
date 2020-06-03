package jianzhiOffer.hash;

import java.util.HashMap;

public class Solution50_1 {

    public char firstUniqChar(String s) {

        if(s == null || s.length() == 0) return ' ';
        HashMap<Character, Boolean> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for(char c: chars) {
            if(map.containsKey(c)) map.put(c, false);
            else map.put(c, true);
        }
        for(char c: chars) {
            if(map.get(c)) return c;
        }
        return ' ';
    }
}
