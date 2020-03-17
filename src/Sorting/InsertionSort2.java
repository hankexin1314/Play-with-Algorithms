package Sorting;

public class InsertionSort2 {

    private InsertionSort2(){}

    public static void sort(Comparable[] arr) {

        for(int i=1; i<arr.length; i++) {
            Comparable e = arr[i];
            int j = i;
            for(; j>0 && e.compareTo(arr[j-1]) < 0; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = e;
        }
    }

    public static void sort(Comparable[] arr, int l, int r) {

        for(int i=l+1; i<=r; i++) {
            Comparable e = arr[i];
            int j = i;
            for(; j>l && e.compareTo(arr[j-1]) < 0; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = e;
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
        SortTestHelper.testSort("Sorting.InsertionSort2", arr);
        arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("Sorting.SelectionSort", arr);


        return;
    }
}
