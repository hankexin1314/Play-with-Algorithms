package BinaryTree_Recursive;

import java.util.HashMap;

// 递归实现
public class Solution437_2 {
    public int pathSum(TreeNode root, int sum) {

        if(root == null)
            return 0;
        HashMap<Integer, Integer> map = new HashMap<>(); // key为从根节点开始，到某一节点的路径和， map为和为这个值的路径有几条
        // 比如对于 1 2 3 4 5 map维护的是 0,1; 1,1; 1+2,1; 1+2+3,1;...
        map.put(0, 1); // root不为null，和为0的路径目前只有一条——没有任何节点
        return helper(root, sum, 0, map);
    }

    // 从node节点开始，target为要求的目标  curSum为从根节点到当前节点的和（不包括当前节点的值），map
    private int helper(TreeNode node, int target, int curSum, HashMap<Integer, Integer> map) {

        if(node == null) return 0;
        curSum += node.val;
        // 解释下 curSum是root->node的节点和，在map中寻找curSum - target 就是寻找 curSum - map.keySet 是否有和target相等的
        // 前面相减的含义是，map的key是root到node之前的节点的路径和，所以这是包含node的所有路径
        // node是从root开始不断往深遍历的，所以这样可以完全遍历所有路径
        int res = map.getOrDefault(curSum - target, 0);
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        res += helper(node.left, target, curSum, map) + helper(node.right, target, curSum, map);
        // 这一步是状态重置，将node这一条路径上的路径和去掉，避免影响其他路径上的结果
        map.put(curSum, map.get(curSum) - 1);
        return res;

    }
}
