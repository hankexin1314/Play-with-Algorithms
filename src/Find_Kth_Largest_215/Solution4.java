package Find_Kth_Largest_215;

import java.util.PriorityQueue;

// 优先队列
// 空间复杂度 O(k)
// 平均时间复杂度 O(Nlogk) 最差为n^2
public class Solution4 {

    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> a - b);
        for(int i : nums) {
            q.add(i);
            if(q.size() > k)
                q.poll();
        }

        return q.peek();
    }

}
