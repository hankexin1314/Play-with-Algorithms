package jianzhiOffer.stackqueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MaxQueue {

    private Queue<Integer> queue;
    private Deque<Integer> max;

    public MaxQueue() {

        queue = new LinkedList<>();
        max = new LinkedList<>();
    }

    public int max_value() {
        return max.isEmpty() ? -1: max.peekFirst();
    }

    public void push_back(int value) {
        queue.offer(value);
        while(!max.isEmpty() && max.peekLast() < value) max.removeLast();
        max.addLast(value);
    }

    public int pop_front() {
        if(!max.isEmpty() && max.peekFirst().equals(queue.peek())) max.removeFirst();
        return queue.isEmpty() ? -1: queue.poll();
    }
}
