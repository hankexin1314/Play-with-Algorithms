package jianzhiOffer;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution40_1 {

    public int[] getLeastNumbers(int[] arr, int k) {

        if(k > arr.length) return null;
        if(k == 0) return new int[0];
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for(int i = 0; i < k; i++) pq.offer(arr[i]);
        for(int i = k; i < arr.length; i++) {
            if(arr[i] < pq.peek()) {
                pq.poll();
                pq.offer(arr[i]);
            }
        }
        int[] res = new int[k];
        int index = 0;
        while(!pq.isEmpty()) {
            res[index] = pq.poll();
            index++;
        }

        return res;
    }
}
