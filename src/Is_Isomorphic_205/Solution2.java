package Is_Isomorphic_205;

import java.util.HashMap;

public class Solution2 {

    public boolean isIsomorphic(String s, String t) {

        HashMap<Character, Character> m1 = new HashMap<>();
        HashMap<Character, Character> m2 = new HashMap<>();

        for(int i = 0; i < s.length(); i++) {

            if(!m1.containsKey(s.charAt(i)))
                m1.put(s.charAt(i), t.charAt(i));
            else if(m1.get(s.charAt(i)) != t.charAt(i))
                return false;

            if(!m2.containsKey(t.charAt(i)))
                m2.put(t.charAt(i), s.charAt(i));
            else if(m2.get(t.charAt(i)) != s.charAt(i))
                return false;
        }

        return true;
    }
}
