package Stack_Queue;

// 递归
import java.util.ArrayList;
import java.util.List;

public class Solution144_1 {

    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        preorder(root, res);

        return res;
    }

    public void preorder(TreeNode root, List<Integer> res) {

        if(root != null) {
            res.add(root.val);
            preorder(root.left, res);
            preorder(root.right, res);
        }
    }
}


