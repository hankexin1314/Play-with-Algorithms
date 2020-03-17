package Sorting;

import java.util.Arrays;
import java.util.Comparator;

public class SelectionSort {

    private SelectionSort(){}

    public static void sort(Comparable[] arr) {

        for(int i=0; i<arr.length; i++) {
            int minIndex = i;
            for(int j=i+1; j<arr.length; j++) {
                if(arr[j].compareTo(arr[minIndex]) < 0)
                    minIndex = j;
            }
            swap(arr, i, minIndex);
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
        SortTestHelper.testSort("Sorting.SelectionSort", arr);

        return;
    }
}
