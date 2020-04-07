package Stack_Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution103_2 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if(root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<>();
        int lTr = 1; // 1表示从左往右，-1表示从右往左 第一行从左往右 所以第二行从右往左
        q.offer(root);
        while(!q.isEmpty()) {

            LinkedList<Integer> subRes = new LinkedList<>(); // 改为LinkedList
            int len = q.size();
            for(int i = 0; i < len; i++) {
                TreeNode node = q.poll();
                if(lTr == 1) subRes.addLast(node.val);
                else subRes.addFirst(node.val);  // 降低时间复杂度

                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
            res.add(subRes);
            lTr *= -1;
        }
        return res;
    }
}
