package Dynamic_Programming;

// 记忆化搜索


import java.util.HashMap;

public class Solution337_2 {

    public int rob(TreeNode root) {
        HashMap<TreeNode, Integer> map = new HashMap<>();
        return helper(root, map);
    }

    // 从root节点开始能抢多少钱
    private int helper(TreeNode root, HashMap<TreeNode, Integer> map) {

        if(root == null) return 0;
        if(map.containsKey(root)) return map.get(root);
        int money = root.val; // 选择root节点
        if(root.left != null) money += (helper(root.left.right, map) + helper(root.left.left, map));
        if(root.right != null) money += (helper(root.right.right, map) + helper(root.right.left, map));

        int res = Math.max(money, helper(root.left, map) + helper(root.right, map));
        map.put(root, res);
        return res;
    }



}
