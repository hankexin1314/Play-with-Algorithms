package jianzhiOffer.math;

import java.util.ArrayList;

public class Solution62_1 {

    public int lastRemaining(int n, int m) {

        if(m <= 0) return -1;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) list.add(i);
        int index = 0;
        while(n > 1) {
            index = (index + m - 1) % n;
            list.remove(index);
            n--;
        }
        return list.get(0);
    }
}
