package jianzhiOffer;

// 二分法
public class Solution11_2 {

    public int minArray(int[] numbers) {

        if(numbers == null || numbers.length == 0) return -1;
        int l = 0, r = numbers.length - 1;
        while(l < r) {
            int mid = (r - l) / 2 + l;
            if(numbers[mid] > numbers[l]) l = mid + 1;
            else if(numbers[mid] < numbers[l]) r = mid - 1;
            else l++;
        }

        return numbers[l];
    }
}
