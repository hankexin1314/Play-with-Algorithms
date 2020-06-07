package jianzhiOffer.string;

public class Solution58_2_1 {

    public String reverseLeftWords(String s, int n) {

        if(s == null || s.length() == 0 || n < 0) return "";
        StringBuilder res = new StringBuilder();
        res.append(s, n, s.length());
        res.append(s, 0, n);
        return res.toString();
    }
}
