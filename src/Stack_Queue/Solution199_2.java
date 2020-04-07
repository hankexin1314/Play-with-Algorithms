package Stack_Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution199_2 {

    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        if(root == null)
            return res;
        DFS(root, 0, res);
        return res;
    }

    private void DFS(TreeNode root, int level, List<Integer> res) {

        if(root == null)
            return;
        if(level == res.size())
            res.add(root.val);
        DFS(root.right, level + 1, res);
        DFS(root.left, level + 1, res);
    }
}
