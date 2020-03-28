package Is_Happy_202;

import java.util.HashSet;

public class Solution {

    public boolean isHappy(int n) {

        HashSet<Integer> set = new HashSet<>(); // 保存出现过的数字
        while(!set.contains(n)) {
            set.add(n);
            n = powSum(n);
            if(n == 1)
                return true;
        }
        return false;
    }

    private int powSum(int num) {

        String str = String.valueOf(num);
        char[] c = str.toCharArray();
        int sum = 0;
        for(char i: c)
            sum += Math.pow(i - '0', 2);

        return sum;
    }

    public static void main(String[] args) {

        System.out.println((new Solution()).isHappy(19));
    }

}
