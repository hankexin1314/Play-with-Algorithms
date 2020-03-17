package Sorting;

import java.util.Arrays;
import java.util.Comparator;

public class InsertionSort {

    private InsertionSort(){}

    public static void sort(Comparable[] arr) {

        for(int i=1; i<arr.length; i++) {
            for(int j=i; j>0 && arr[j].compareTo(arr[j-1]) < 0; j--) {
                swap(arr, j, j-1);
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
        SortTestHelper.testSort("Sorting.InsertionSort", arr);
        arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("Sorting.SelectionSort", arr);

        return;
    }
}
