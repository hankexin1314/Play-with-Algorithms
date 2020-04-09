package Stack_Queue;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Solution347_1 {

    public List<Integer> topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int n: nums) {
            int freq = map.getOrDefault(n, 0);
            map.put(n, freq + 1);
        }

        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        for(int n: map.keySet()) {
            int freq = map.get(n);
            pq.offer(new Pair<>(n, freq));
            if(pq.size() > k)
                pq.poll();
        }

        List<Integer> res = new ArrayList<>();
        for(Pair<Integer, Integer> pair: pq)
            res.add(pair.getKey());

        return res;
    }
}
