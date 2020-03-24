package Two_Sum_2_167;

public class Solution {

    public int[] twoSum(int[] numbers, int target) {

        int head = 0, tail = numbers.length - 1;
        int[] res = new int[2];

        while(true) {
            if(numbers[head] + numbers[tail] == target) {
                res[0] = head + 1;
                res[1] = tail + 1;
                break;
            }
            else if (numbers[head] + numbers[tail] < target)
                head++;
            else
                tail--;
        }

        return res;
    }
}
