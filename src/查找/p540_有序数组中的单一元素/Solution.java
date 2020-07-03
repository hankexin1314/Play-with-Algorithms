package 查找.p540_有序数组中的单一元素;

public class Solution {

    public int singleNonDuplicate(int[] nums) {

        if(nums == null || nums.length == 0)
            throw new IllegalStateException("no solution");

        int l = 0, r = nums.length - 1;
        while(l < r) {
            int mid = (r - l) / 2 + l;
            if(nums[mid] == nums[mid - 1]) {
                if(mid % 2 == 0)
                    r = mid - 2;
                else
                    l = mid + 2;
            }
            else if(nums[mid] == nums[mid + 1]) {
                if(mid % 2 == 0)
                    l = mid + 2;
                else
                    r = mid - 2;
            }
            else
                return nums[mid];
        }
        return nums[r];
    }


    public static void main(String []args) {

        Integer x = 10;
        String str1 = "abc";
        String str2 = new String("def");
        String str3 = "abc";
        String str4 = str2.intern();
        String str5 = "def";
        System.out.println(str1 == str3);//true
        System.out.println(str2 == str4);//false
        System.out.println(str3 == str5);//true
    }
}
