package jianzhiOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution6_2 {

    public int[] reversePrint(ListNode head) {

        ArrayList<Integer> arr = new ArrayList<>();
        helper(head, arr);
        int[] res = new int[arr.size()];
        for(int i = 0; i < res.length; i++)
            res[i] = arr.get(i);

        return res;
    }

    private void helper(ListNode head, ArrayList<Integer> arr) {

        if(head == null) return;
        helper(head.next, arr);
        arr.add(head.val);
    }
}
