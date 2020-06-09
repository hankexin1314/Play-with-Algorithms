package 排序.p215_数组中的第K个最大元素;

import java.util.PriorityQueue;

public class Solution {

    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num: nums) {
            pq.offer(num);
            if(pq.size() > k) pq.poll();
        }
        return pq.peek();
    }
}
