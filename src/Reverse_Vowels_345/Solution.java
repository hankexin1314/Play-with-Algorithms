package Reverse_Vowels_345;

public class Solution {

    public String reverseVowels(String s) {

        char[] c = s.toCharArray();
        int l = 0, r = c.length - 1;
        while(l < r) {
            while(l < r && !isVowels(c[l])) l++;
            while(l < r && !isVowels(c[r])) r--;

            if(l >= r)
                break;
            swap(c, l, r);
            l++;
            r--;
        }

        return new String(c);
    }

    private void swap(char[] c, int i, int j) {

        char tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }

    private boolean isVowels(char c) {

        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i'
                || c == 'o' || c == 'u';
    }
}
