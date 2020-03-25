package Min_Window_76;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public String minWindow(String s, String t) {

        int len = s.length() + 1;
        int start = 0;
        int l = 0, r = -1; // 滑动窗口[l...r]
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();

        for(int i = 0; i < t.length(); i++) {
            int count = need.getOrDefault(t.charAt(i), 0);
            need.put(t.charAt(i), count + 1);
        }

        int match = 0;
        while(r + 1 < s.length()) {

            r++;
            char c1 = s.charAt(r);
            int count = window.getOrDefault(c1, 0);
            window.put(c1, count + 1);
            if(need.containsKey(c1) && window.get(c1).intValue() == need.get(c1).intValue())
                match++;

            // match == t.length()
            while(match == need.size()) {

                if(r - l + 1 < len) {
                    len = r - l + 1;
                    start = l;
                }
                char c2 = s.charAt(l);
                l++;
                window.put(c2, window.get(c2) - 1);
                if(need.containsKey(c2) && window.get(c2) < need.get(c2))
                    match--;
            }
        }
        if(len == s.length() + 1)
            return "";

        return s.substring(start, start + len);
    }

    public static void main(String [] args) {

        String res = (new Solution().minWindow("aa", "aa"));
    }
}
