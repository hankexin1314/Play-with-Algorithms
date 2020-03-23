package Array;

public class BinarySearch {

    private BinarySearch() {
    }

    public static int binarySearch(Comparable[] arr, int n, Comparable target) {

        int l = 0, r = n - 1; // 在[l...r]的范围中寻找target
        while (l <= r) { // 这里由于上面的定义为左闭右闭，所以这里写等于
            int mid = l + (r - l) / 2;
            if (target.compareTo(arr[mid]) == 0)
                return mid;
            else if (target.compareTo(arr[mid]) < 0)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return -1;
    }

    public static void main(String[] args) {

        int n = (int) Math.pow(10, 7);
        Integer data[] = Util.generateOrderedArray(n);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++)
            if (i != binarySearch(data, n, i))
                throw new IllegalStateException("find i failed!");
        long endTime = System.currentTimeMillis();

        System.out.println("Binary Search test complete.");
        System.out.println("Time cost: " + (endTime - startTime) + " ms");
    }
}
