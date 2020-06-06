package jianzhiOffer.string;

public class Solution58_1_1 {

    public String reverseWords(String s) {

        if(s == null || s.length() == 0) return "";
        StringBuilder res = new StringBuilder();
        int index = s.length() - 1;
        while(index >= 0) {
            while(index >= 0 && s.charAt(index) == ' ') index--;
            if(index < 0) break;
            int end = index + 1;
            while(index >= 0 && s.charAt(index) != ' ') index--;
            int start = index + 1;
            res.append(s, start, end);
            res.append(" ");
        }
        if(res.length() > 0) res.setLength(res.length() - 1);
        return res.toString();
    }
}
