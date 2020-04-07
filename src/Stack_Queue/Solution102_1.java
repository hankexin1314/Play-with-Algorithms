package Stack_Queue;

// 使用辅助类
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution102_1 {

    public List<List<Integer>> levelOrder(TreeNode root) {

        Queue<ElementLevel> list = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root == null)
            return res;
        int level = 0;
        ElementLevel cur;
        list.offer(new ElementLevel(root, level));
        while(!list.isEmpty()) {
            cur = list.poll();
            if(cur.level == res.size()) { // 没有这一层的信息
                ArrayList<Integer> subRes = new ArrayList<>();
                subRes.add(cur.node.val);
                res.add(subRes);
            }
            else
                res.get(cur.level).add(cur.node.val);
            level = cur.level + 1;
            if(cur.node.left != null) list.offer(new ElementLevel(cur.node.left, level));
            if(cur.node.right != null) list.offer(new ElementLevel(cur.node.right, level));
        }

        return res;
    }

    class ElementLevel {

        int level;
        TreeNode node;
        public ElementLevel(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
}
