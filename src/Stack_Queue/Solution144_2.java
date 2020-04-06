package Stack_Queue;

// 迭代
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution144_2 {

    public List<Integer> preorderTraversal(TreeNode root) {


        List<Integer> res = new ArrayList<>();
        if(root == null)
            return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if(cur.right != null) stack.push(cur.right);
            if(cur.left != null) stack.push(cur.left);
        }

        return res;
    }

}


