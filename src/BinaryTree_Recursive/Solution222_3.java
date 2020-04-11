package BinaryTree_Recursive;


import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 优化解法
public class Solution222_3 {

    public int countNodes(TreeNode root) {

        // 首先我们计算最大深度
        if(root == null)
            return 0;
        int maxDepth = 0;
        TreeNode cur = root;
        while(cur.left != null) {
            cur = cur.left;
            maxDepth ++;
        }
        if(maxDepth == 0)
            return 1;

        // 最后一层的节点个数为2^d d为最大深度
        int l = 0, r = (int)Math.pow(2, maxDepth) - 1;
        while(l <= r) { // 找到的是第一个null

            int mid = l + (r - l) / 2;
            if(isNull(mid, maxDepth, root)) r = mid - 1;
            else l = mid + 1;
        }

        return l + (int)(Math.pow(2, maxDepth)) - 1;
    }

    // 判断最后一行的指定位置节点是否为null
    private boolean isNull(int index, int maxDepth, TreeNode root) {

        TreeNode node = root;
        int depth = 0; // 表示深度
        int l = 0, r = (int)Math.pow(2, maxDepth) - 1;
        while(depth < maxDepth) {
            int mid = l + (r - l) / 2;
            if(index <= mid) {
                node = node.left;
                r = mid;
            }
            else {
                node = node.right;
                l = mid + 1;
            }
            depth ++;
        }

        return node == null;
    }
}
