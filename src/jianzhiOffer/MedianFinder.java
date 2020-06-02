package jianzhiOffer;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFinder {

    private Queue<Integer> A, B;


    /** initialize your data structure here. */
    public MedianFinder() {
        A = new PriorityQueue<>((a, b) -> b - a);
        B = new PriorityQueue<>();
    }

    public void addNum(int num) {

        if(A.size() != B.size()) {
            A.offer(num);
            B.offer(A.poll());
        }
        else {
            B.offer(num);
            A.offer(B.poll());
        }
    }

    public double findMedian() {

        if(A.isEmpty()) throw new IllegalStateException("空");
        return A.size() == B.size() ? (A.peek() + B.peek()) / 2.0 : A.peek();
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(0);
        System.out.println(arr);
        arr.add(0, 1);
        System.out.println(arr);
    }
}
