package BinaryTree_Recursive;


import java.util.ArrayList;
import java.util.List;

// 递归 使用int
public class Solution129_2 {
    public int sumNumbers(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        sumNumbers(root, 0, res);
        int sum = 0;
        for(int i: res)
            sum += i;
        return sum;
    }

    private void sumNumbers(TreeNode root, int curSum, List<Integer> res) {

        if(root == null)
            return;
        int tmp = curSum;
        curSum = curSum * 10 + root.val;
        if(root.left == null && root.right == null)
            res.add(curSum);
        else {
            if(root.left != null) sumNumbers(root.left, curSum, res);
            if(root.right != null) sumNumbers(root.right, curSum, res);
        }
        curSum = tmp;
    }
}
