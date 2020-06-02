package jianzhiOffer;

public class Solution33_1 {

    public boolean verifyPostorder(int[] postorder) {

        if(postorder == null) return false;
        return postOrder(postorder, 0, postorder.length - 1);
    }


    // 后序遍历的结果为[start, end]，返回是否可以拼成一个二叉搜索树
    private boolean postOrder(int[] postorder, int start, int end) {

        if(start >= end) return true;
        int root = postorder[end];
        int index = start; // 寻找第一个 >= root的值的下标
        for(; index < end; index++) {
            if(postorder[index] > root)
                break;
        }
        for(int i = index + 1; i < end; i++) {
            if(postorder[i] < root)
                return false;
        }

        return postOrder(postorder, start, index - 1) && postOrder(postorder, index, end - 1);
    }
}
