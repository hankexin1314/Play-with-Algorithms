package Sorting;

import java.util.Arrays;
import java.util.Comparator;

public class BubbleSort {

    private BubbleSort(){}

    public static void sort(Comparable[] arr) {

        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length-1-i; j++) {
                if(arr[j].compareTo(arr[j+1])>0)
                    swap(arr, j, j+1);
            }
        }
    }

    private static void swap(Object[] arr, int a, int b) {
        Object t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    public static void main(String[] args) {

        // 测试排序算法辅助函数
        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("Sorting.BubbleSort", arr);

        return;
    }
}
