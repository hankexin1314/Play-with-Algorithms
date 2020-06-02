package jianzhiOffer;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution40_2 {

    public int[] getLeastNumbers(int[] arr, int k) {

        if(k == 0 || arr.length == 0) return new int[0];
        return quickSort(arr, 0, arr.length - 1, k - 1);
    }

    private int[] quickSort(int[] arr, int l, int r, int k) {

        int j = partition(arr, l, r);
        if(j == k) return Arrays.copyOfRange(arr, 0, k + 1);
        return j > k ? quickSort(arr, l, j - 1, k) : quickSort(arr, j + 1, r, k);
    }

    private int partition(int[] arr, int l, int r) {

        int val = arr[l];
        int i = l + 1, j = r;
        while(true) {
            while(i <= r && arr[i] < val) i++;
            while(j >= l + 1 && arr[j] > val) j--;
            if(i > j) break;
            swap(arr, i, j);
            i ++;
            j --;
        }
        swap(arr, l, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


}
