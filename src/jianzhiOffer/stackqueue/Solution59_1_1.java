package jianzhiOffer.stackqueue;

import java.util.Deque;
import java.util.LinkedList;

public class Solution59_1_1 {

    public int[] maxSlidingWindow(int[] nums, int k) {

        if(nums == null || nums.length == 0 || k <= 0 || k > nums.length) return new int[0];
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> aux = new LinkedList<>();
        for(int i = 0; i < k; i++) {

            while(!aux.isEmpty() && nums[i] > aux.peekLast())
                aux.removeLast();
            aux.add(nums[i]);
        }
        res[0] = aux.peekFirst();
        for(int i = k; i < nums.length; i++) {
            if(aux.peekFirst() == nums[i - k]) aux.removeFirst();
            while(!aux.isEmpty() && nums[i] > aux.peekLast())
                aux.removeLast();
            aux.addLast(nums[i]);
            res[i - k + 1] = aux.peekFirst();
        }
        return res;
    }

    public static void main(String[] args) {
        (new Solution59_1_1()).maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3);
    }
}
