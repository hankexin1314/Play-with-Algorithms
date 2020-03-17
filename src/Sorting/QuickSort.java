package Sorting;

import java.util.Arrays;

public class QuickSort {

    private QuickSort(){}

    public static void sort(Comparable[] arr) {

        sort(arr, 0, arr.length-1);
    }

    // 对arr从[l...r]进行排序
    private static void sort(Comparable[] arr, int l, int r) {

//        if (l >= r)
//            return;
        // 改进一 到一定程度使用插入排序
        if(r-l<=15) {
            InsertionSort2.sort(arr, l, r);
            return;
        }

        int p = partition2(arr, l, r);
        sort(arr, l, p-1);
        sort(arr, p+1, r);
    }

    // 对[l...r] 进行Partition  返回arr[l]元素应该所处的位置
    private static int partition(Comparable[] arr, int l, int r) {

        // 正在访问的元素定义为i 分界点定义为j
        // 优化二 随机选取标定元素
        swap( arr, l , (int)(Math.random()*(r-l+1))+l );
        // arr[l+1...j] < v, arr[j+1...i) >= v
        int j = l;
        Comparable v = arr[l];
        for(int i=l+1; i<=r; i++) {
            if(arr[i].compareTo(v) < 0) {
                j++;
                swap(arr, j, i);
            }
        }
        swap(arr, l, j);
        return j;
    }

    // 优化三 二路快速排序
    private static int partition2 (Comparable[] arr, int l, int r) {

        // 正在访问的元素定义为i 分界点定义为j
        // 优化二 随机选取标定元素
        swap( arr, l , (int)(Math.random()*(r-l+1))+l );
        Comparable v = arr[l];

        //  arr[l+1...i) <= v, arr(j, r] >= v
        int i = l+1, j = r;

        while(true) {
            while(arr[i].compareTo(v) < 0 && i <= r) // 注意这里没有等于 为了避免不均衡显现出现
                i++;
            while(arr[j].compareTo(v) > 0 && j >= l)
                j--;
            if(i > j)
                break;
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j); // 循环结束后，arr[j] <= v, arr[i] >= v
        return j;
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

        SortTestHelper.testSort("Sorting.MergeSort3", arr1);
        SortTestHelper.testSort("Sorting.QuickSort", arr2);

        int swapTimes = 100;
        assert swapTimes >= 0;

        System.out.println("Test for nearly ordered array, size = " + N + " , swap time = " + swapTimes);

        arr1 = SortTestHelper.generateNearlyOrderedArray(N, swapTimes);
        arr2 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("Sorting.MergeSort3", arr1);
        SortTestHelper.testSort("Sorting.QuickSort", arr2);

        System.out.println();

        return;
    }
}
