package Frequency_Sort_451;

import java.util.PriorityQueue;

public class Solution {

    public String frequencySort(String s) {

        int[] freq = new int[256]; // 频率
        for(char c: s.toCharArray())
            freq[c]++;

        PriorityQueue<Letter> p = new PriorityQueue<>();
        for(int i = 0; i < 255; i++) {
            if(freq[i] > 0)
                p.add(new Letter((char) i, freq[i]));
        }

        StringBuilder res = new StringBuilder();
        while(p.peek() != null) {
            for(int i = 0; i < p.peek().freq; i++)
                res.append(p.peek().c);
            p.poll();
        }

        return res.toString();

    }

    private class Letter implements Comparable<Letter> {

        public char c;
        public int freq;

        public Letter(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }

        @Override
        public int compareTo(Letter other) {
            return other.freq - this.freq;
        }
    }
}
