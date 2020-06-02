package jianzhiOffer;

import java.util.Arrays;

// 字符串加法

public class Solution17_3 {

    public void printNumbers(int n) {

        char[] chars = new char[n];
        Arrays.fill(chars, '0');
        while(increaseOne(chars)) {
            printNum(chars);
        }

    }

    // 字符数组代表的数加一 返回值是是否还可以继续增加
    private boolean increaseOne(char[] chars) {
        int L = chars.length;
        int addition = 1; // 进位值 初始值为1 表示在原来的基础上加1
        for(int i = L - 1; i >= 0; i--) {
            int num = chars[i] - '0';
            num = num + addition;
            addition = 0;
            if(num >= 10) {
                addition = 1;
                num -= 10;
            }
            chars[i] = (char)('0' + num);
        }
        return addition == 0;
    }

    // 打印字符串
    private void printNum(char[] chars) {

        boolean isStart = false; // 是否可以开始输出
        for(char c: chars) {
            if(!isStart && c != '0')
                isStart = true;
            if(isStart)
                System.out.print(c);
        }
        System.out.println();
    }



    public static void main(String[] args) {
        (new Solution17_3()).printNumbers(3);
    }

}
