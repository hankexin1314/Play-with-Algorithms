package jianzhiOffer;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFinder2 {

    private ArrayList<Integer> A;


    /** initialize your data structure here. */
    public MedianFinder2() {
        A = new ArrayList<>();
    }

    public void addNum(int num) {

        A.add(getIndex(num), num);
    }

    private int getIndex(int num) {

        int l = 0, r = A.size() - 1;
        int mid = 0;
        while(l <= r) {
            mid = l + (r - l) / 2;
            if(num > A.get(mid))
                l = mid + 1;
            else if(num < A.get(mid))
                r = mid - 1;
            else return mid;
        }
        return l;
    }

    public double findMedian() {

        if(A.isEmpty()) throw new IllegalStateException("空");
        int l = A.size();
        return l % 2 == 0 ? (A.get(l / 2) + A.get(l / 2 - 1)) / 2.0 : A.get(l / 2);
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(0);
        System.out.println(arr);
        arr.add(0, 1);
        System.out.println(arr);
    }
}
