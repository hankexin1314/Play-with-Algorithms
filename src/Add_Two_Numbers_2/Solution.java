package Add_Two_Numbers_2;

import java.util.Stack;

// 可能造成溢出
public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int num1 = listToNum(l1), num2 = listToNum(l2);
        int sum = num1 + num2;
        ListNode res = new ListNode(0);
        if(sum == 0)
            return res;
        ListNode cur = res;
        int m = 0; // m代表位数
        while(sum / (int) Math.pow(10, m) != 0) {

            int x = (int) (sum % Math.pow(10, m + 1) / Math.pow(10, m));
            cur.next = new ListNode(x);
            cur = cur.next;
            m++;
        }

        return res.next;
    }

    public int listToNum(ListNode l) {

        int num = 0;
        int m = 0; // 代表位数，0就是个位
        while(l != null) {
            num += (l.val * Math.pow(10, m));
            l = l.next;
            m++;
        }

        return num;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 4, 3};
        int[] arr2 = {5, 6, 4};
        ListNode l1 = new ListNode(arr1);
        ListNode l2 = new ListNode(arr2);
        System.out.println((new Solution()).listToNum(l1));
        System.out.println((new Solution()).listToNum(l2));
        ListNode res = (new Solution()).addTwoNumbers(l1, l2);
        System.out.println(res);

        Stack<Integer> s = new Stack<>();
        System.out.println(s.peek());
    }
}
