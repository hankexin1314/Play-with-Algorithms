package jianzhiOffer.sort;

import java.util.Arrays;
import java.util.Comparator;

public class Solution45_1 {

    public String minNumber(int[] nums) {

        Integer[] aux = new Integer[nums.length];
        for(int i = 0; i < nums.length; i++)
            aux[i] = nums[i];

        Arrays.sort(aux, (o1, o2) -> {

            if(o1.equals(o2)) return 0;
            String s1 = o1.toString();
            String s2 = o2.toString();
            for(int i = 0; i < 2 * s1.length() || i < 2 * s2.length(); i++) {
                char c1 = s1.charAt(i % s1.length());
                char c2 = s2.charAt(i % s2.length());
                if(c1 < c2) return -1;
                else if(c1 > c2) return 1;
            }
            return 0;
        });

        StringBuilder sb = new StringBuilder();
        for(Integer num: aux)
            sb.append(num.toString());

        return sb.toString();

    }



}
