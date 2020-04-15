package BinaryTree_Recursive;

// 递归实现
public class Solution437_1 {
    public int pathSum(TreeNode root, int sum) {

        if(root == null)
            return 0;

        return findPath(root, sum) + pathSum(root.right, sum) + pathSum(root.left, sum);
    }

    // 寻找从根节点开始(包含root)，到叶子或者非叶子节点的和为sum 的路径的条数
    private int findPath(TreeNode root, int sum) {
        if(root == null)
            return 0;
        int res = 0;
        if(root.val == sum)
            res += 1;
        return res + findPath(root.left, sum - root.val) + findPath(root.right, sum - root.val);
    }
}
