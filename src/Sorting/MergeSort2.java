package Sorting;

import java.util.Arrays;

public class MergeSort2 {

    private MergeSort2(){};

    // 合并[l...mid] [mid+1...r]
    private static void merge(Comparable[] arr, int l, int mid, int r) {

        Comparable[] aux = Arrays.copyOfRange(arr, l, r+1);

        int i = l, j = mid + 1;
        for(int k = l; k<=r; k++) {
            if(i > mid) {// 左边处理完毕
                arr[k] = aux[j-l];
                j++;
            }
            else if(j > r) {// 右边处理完毕
                arr[k] = aux[i-l];
                i++;
            }
            else if(arr[i].compareTo(arr[j]) < 0) {
                arr[k] = aux[i-l];
                i++;
            }
            else {
                arr[k] = aux[j-l];
                j++;
            }
        }
    }

    private static void sort(Comparable[] arr, int l, int r) {

        if(l >= r)
            return;

        int mid = l + (r - l) / 2;
        sort(arr, l, mid);
        sort(arr, mid+1, r);
        if(arr[mid].compareTo(arr[mid+1]) > 0)
            merge(arr, l, mid, r);
    }

    public static void sort(Comparable[] arr){

        int n = arr.length;
        sort(arr, 0, n-1);
    }

    // 测试MergeSort
    public static void main(String[] args) {

        // Merge Sort是我们学习的第一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        // 注意：不要轻易尝试使用SelectionSort, InsertionSort或者BubbleSort处理100万级的数据
        // 否则，你就见识了O(n^2)的算法和O(nlogn)算法的本质差异：）
        int N = 500000;
        int swapTimes = 100;
        Integer[] arr1 = SortTestHelper.generateNearlyOrderedArray(N, swapTimes);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        SortTestHelper.testSort("Sorting.InsertionSort2", arr1);
        SortTestHelper.testSort("Sorting.MergeSort", arr2);
        SortTestHelper.testSort("Sorting.MergeSort2", arr3);

        return;
    }
}
