package Stack_Queue;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 不使用辅助类
public class Solution102_2 {

    public List<List<Integer>> levelOrder(TreeNode root) {

        Queue<TreeNode> list = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root == null)
            return res;
        list.offer(root);
        while(!list.isEmpty()) {

            List<Integer> subRes = new ArrayList<>();
            int len = list.size();
            for(int i = 0; i < len; i++) {
                TreeNode node = list.poll();
                subRes.add(node.val);
                if(node.left != null)  list.offer(node.left);
                if(node.right != null) list.offer(node.right);
            }
            res.add(subRes);
        }

        return res;
    }

}
