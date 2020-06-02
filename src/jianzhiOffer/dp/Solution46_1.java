package jianzhiOffer.dp;

public class Solution46_1 {

    public int translateNum(int num) {

        int a = 0, b = 1;
        int pre = 0; // 前一个数字
        String str = Integer.toString(num);
        for(int i = 0; i < str.length(); i++) {
            int n = str.charAt(i) - '0';
            if(pre * 10 + n > 25 || pre == 0) {
                a = b;
            }
            else {
                int tmp = b;
                b = a + b;
                a = tmp;
            }
            pre = n;
        }
        return b;
    }

    public static void main(String[] args) {
        String str1 = "a";
        String str2 = "b";
        String str3 = "ab";
        String str4 = str1 + str2;
        String str5 = new String("ab");

        System.out.println(str5.equals(str3));
        System.out.println(str3 == str4);
        System.out.println(str5 == str3);
        System.out.println(str5 == str4);
        System.out.println(str5.intern() == str3);
        System.out.println(str5.intern() == str4);
    }
}
