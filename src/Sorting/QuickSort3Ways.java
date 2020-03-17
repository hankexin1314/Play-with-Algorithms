package Sorting;

import java.util.Arrays;

public class QuickSort3Ways {

    private QuickSort3Ways(){}

    public static void sort(Comparable[] arr) {

        sort(arr, 0, arr.length-1);
    }

    // 对arr从[l...r]进行排序
    // 优化四 三路快排
    private static void sort(Comparable[] arr, int l, int r) {

//        if (l >= r)
//            return;
        // 改进一 到一定程度使用插入排序
// 对于小规模数组, 使用插入排序
        if( r - l <= 15 ){
            InsertionSort2.sort(arr, l, r);
            return;
        }

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap( arr, l, (int)(Math.random()*(r-l+1)) + l );

        Comparable v = arr[l];

        int lt = l;     // arr[l+1...lt] < v
        int gt = r + 1; // arr[gt...r] > v
        int i = l+1;    // arr[lt+1...i) == v
        while( i < gt ){
            if( arr[i].compareTo(v) < 0 ){
                swap( arr, i, lt+1);
                i ++;
                lt ++;
            }
            else if( arr[i].compareTo(v) > 0 ){
                swap( arr, i, gt-1);
                gt --;
            }
            else{ // arr[i] == v
                i ++;
            }
        }

        swap( arr, l, lt );

        sort(arr, l, lt-1);
        sort(arr, gt, r);
    }

    // 返回值不止一个数  就不设计为单独的函数了
    private static int partition (Comparable[] arr, int l, int r) {

        // 优化二 随机选取标定元素
        swap( arr, l , (int)(Math.random()*(r-l+1))+l );
        Comparable v = arr[l];

        //  arr[l+1...lt] < v, arr[gt...r] > v, arr[lt+1...i-1] == v
        int lt = l, gt = r + 1; // less than, great than

        for(int i=l+1; i <= r; i++) {
            if(arr[i].compareTo(v) < 0) {
                lt++;
                swap(arr, lt, i);
            }
            else if(arr[i].compareTo(v) > 0) {
                gt--;
                swap(arr, i, gt);
                i--;
            }
            else
                continue;
        }
        return lt;
    }

    // 将索引i和j上的元素进行交换
    private static void swap(Object[] arr, int i, int j) {
        Object tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {

        // Quick Sort也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int N = 1000000;
        System.out.println("Test for random array, size = " + N + " , random range [0, " + N + "]");

        Integer[] arr1 = SortTestHelper.generateRandomArray(N, 0, N);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("Sorting.MergeSort3", arr1);
        SortTestHelper.testSort("Sorting.QuickSort", arr2);
        SortTestHelper.testSort("Sorting.QuickSort3Ways", arr3);

        int swapTimes = 100;
        assert swapTimes >= 0;

        System.out.println("Test for nearly ordered array, size = " + N + " , swap time = " + swapTimes);

        arr1 = SortTestHelper.generateNearlyOrderedArray(N, swapTimes);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("Sorting.MergeSort3", arr1);
        SortTestHelper.testSort("Sorting.QuickSort", arr2);
        SortTestHelper.testSort("Sorting.QuickSort3Ways", arr3);

        System.out.println("Test for random array, size = " + N + " , random range [0,10]");

        arr1 = SortTestHelper.generateRandomArray(N, 0, 10);
        arr2 = Arrays.copyOf(arr1, arr1.length);
        arr3 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("Sorting.MergeSort", arr1);
        SortTestHelper.testSort("Sorting.QuickSort", arr2);
        SortTestHelper.testSort("Sorting.QuickSort3Ways", arr3);

        System.out.println();

        return;
    }
}
