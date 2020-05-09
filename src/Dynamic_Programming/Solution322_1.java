package Dynamic_Programming;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 暴力
public class Solution322_1 {

    public int coinChange(int[] coins, int amount) {

        Queue<Pair<Integer, Integer>> queue = new LinkedList<>(); // key为所需金额，val为硬币个数
        queue.offer(new Pair<>(amount, 0));
        while(!queue.isEmpty()) {
            Pair<Integer, Integer> p = queue.poll();
            int target = p.getKey(), count = p.getValue();
            if(target == 0) return count;
            for(int coin: coins) {
                if(target - coin == 0) return count + 1;
                else if(target - coin > 0) queue.offer(new Pair<>(target - coin, count + 1));
            }
        }
        return -1;
    }
}
