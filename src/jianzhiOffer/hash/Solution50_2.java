package jianzhiOffer.hash;

import java.util.HashMap;

public class Solution50_2 {

    public char firstUniqChar(String s) {

        if(s == null || s.length() == 0) return ' ';
        int[] map = new int[26];
        char[] chars = s.toCharArray();
        for(char c: chars) {
            map[c - 'a'] += 1;
        }
        for(char c: chars) {
            if(map[c - 'a'] == 1) return c;
        }
        return ' ';
    }
}
