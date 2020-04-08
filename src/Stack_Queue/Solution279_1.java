package Stack_Queue;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

// 超出时间限制
public class Solution279_1 {

    public int numSquares(int n) {

        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        q.offer(new Pair<Integer, Integer>(n, 0)); // n代表数字n，0代表从n到达这个数字所需要几步（也就是几个完全平方数）

        while(!q.isEmpty()) {
            Pair<Integer, Integer> tmp = q.poll();
            int num = tmp.getKey();
            int step = tmp.getValue();

            if(num == 0) // 因为是广度优先，所以第一次到达0就是最短路径
                return step;

            for(int i = 1; num - i * i >= 0; i++) {
                q.offer(new Pair<Integer, Integer>(num - i * i, step + 1));
            }
        }
        throw new IllegalStateException ("No Solution!"); // 在没有显式返回值的时候可以这么写
    }
}
