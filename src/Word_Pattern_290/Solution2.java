package Word_Pattern_290;

public class Solution2 {

    public boolean wordPattern(String pattern, String str) {

        String[] s = str.split(" ");
        if(pattern.length() != s.length)
            return false;

        for(int i = 0; i < s.length; i++) {
            if(pattern.indexOf(pattern.charAt(i)) != indexOf(s, s[i]))
                return false;
        }

        return true;
    }


    // 返回第一次出现str的索引，如果没有返回-1
    private int indexOf(String[] s, String str) {

        int res = -1;
        for(int i = 0; i < s.length; i++)
            if(s[i].equals(str)) {
                res = i;
                break;
            }

        return res;
    }
}
