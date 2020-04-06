package Stack_Queue;

// 递归
import java.util.ArrayList;
import java.util.List;

public class Solution94_1 {

    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        inorder(root, res);

        return res;
    }

    public void inorder(TreeNode root, List<Integer> res) {

        if(root != null) {
            inorder(root.left, res);
            res.add(root.val);
            inorder(root.right, res);
        }
    }
}


