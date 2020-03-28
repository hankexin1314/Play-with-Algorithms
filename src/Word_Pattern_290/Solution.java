package Word_Pattern_290;

import java.util.HashMap;

public class Solution {

    public boolean wordPattern(String pattern, String str) {

        char[] c = pattern.toCharArray();
        String[] s = str.split(" ");
        if(c.length != s.length)
            return false;

        HashMap<Character, String> map = new HashMap<>();
        for(int i = 0; i < c.length; i++) {
            if(!map.containsKey(c[i]))
                map.put(c[i], s[i]);
            else {
                if(!map.get(c[i]).equals(s[i]))
                    return false;
            }
        }

        HashMap<String, Character> map2 = new HashMap<>();
        for(int i = 0; i < s.length; i++) {
            if(!map2.containsKey(s[i]))
                map2.put(s[i], c[i]);
            else {
                if(!map2.get(s[i]).equals(c[i]))
                    return false;
            }
        }

        return true;
    }
}
