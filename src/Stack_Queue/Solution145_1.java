package Stack_Queue;

// 递归
import java.util.ArrayList;
import java.util.List;

public class Solution145_1 {

    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        postorder(root, res);

        return res;
    }

    public void postorder(TreeNode root, List<Integer> res) {

        if(root != null) {
            postorder(root.left, res);
            postorder(root.right, res);
            res.add(root.val);
        }
    }
}


