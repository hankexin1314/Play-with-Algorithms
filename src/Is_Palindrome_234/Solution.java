package Is_Palindrome_234;

import java.util.ArrayList;

public class Solution {

    public boolean isPalindrome(ListNode head) {

        if(head == null || head.next == null)
            return true;
        ArrayList<Integer> arr = new ArrayList<>();
        while(head != null) {
            arr.add(head.val);
            head = head.next;
        }

        for(int i = 0; i < arr.size() / 2; i++) {
            if(!arr.get(i).equals(arr.get(arr.size() - 1 - i)))
                return false;
        }

        return true;
    }
}
