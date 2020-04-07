package Stack_Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution107_2 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        LinkedList<List<Integer>> res = new LinkedList<>(); // 改为linkedlist
        if(root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            List<Integer> subRes = new ArrayList<>();
            int len = q.size();
            for(int i = 0; i < len; i++) {
                TreeNode node = q.poll();
                subRes.add(node.val);
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
            res.addFirst(subRes); // 使用addFirst降低时间复杂度
        }
        return res;
    }
}
