package Is_Anagram_242;

import java.util.HashMap;
import java.util.HashSet;

public class Solution {

    public boolean isAnagram(String s, String t) {

        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();

        if(c1.length != c2.length)
            return false;

        HashMap<Character, Integer> map = new HashMap<>();
        for(char c: c1) {
            int count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
        }

        for(char c: c2) {
            int count = map.getOrDefault(c, 0);
            if(count == 0)
                return false;
            map.put(c, count - 1);
        }

        return true;
    }
}
