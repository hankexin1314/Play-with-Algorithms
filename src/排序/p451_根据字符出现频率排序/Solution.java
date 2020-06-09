package 排序.p451_根据字符出现频率排序;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Solution {

    public String frequencySort(String s) {

        if(s == null || s.length() == 0) return "";
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c: chars)
            map.put(c, map.getOrDefault(c, 0) + 1);
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        for(char c: map.keySet())
            pq.offer(c);
        StringBuilder res = new StringBuilder();
        while(!pq.isEmpty()) {
            char c = pq.poll();
            int count = map.get(c);
            for(int i = 0; i < count; i++) res.append(c);
        }
        return res.toString();
    }

    public String frequencySort1(String s) {

        if(s == null || s.length() == 0) return "";
        char[] chars = s.toCharArray();
        int[] freq = new int[256];
        for(char c: chars)
            freq[c] ++;
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> freq[b] - freq[a]);
        for(int i = 0; i < 256; i++) {
            if(freq[i] != 0)
                pq.offer((char)i);
        }
        StringBuilder res = new StringBuilder();
        while(!pq.isEmpty()) {
            char c = pq.poll();
            int count = freq[c];
            for(int i = 0; i < count; i++) res.append(c);
        }
        return res.toString();
    }
}
