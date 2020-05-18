package greedy;

public class Solution392_1 {

    public boolean isSubsequence(String s, String t) {

        if(s.length() == 0) return true;
        int j = 0;
        for(int i = 0; i < t.length() && j < s.length(); i++) {
            if(s.charAt(j) == t.charAt(i)) j++;
        }

        return j == s.length();
    }
}
