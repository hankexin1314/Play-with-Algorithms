package jianzhiOffer;

import java.util.Arrays;

// 排列组合
public class Solution17_2 {


    public void printNumbers(int n) {

        char[] chars = new char[n];
        Arrays.fill(chars, '0');
        helper(chars, 0);
    }

    // index表示该处理第几位了
    private void helper(char[] chars, int index) {
        int L = chars.length;
        if(index == L) {
            printNum(chars);
            return;
        }
        for(int i = 0; i < 10; i++) {
            chars[index] = (char)('0' + i);
            helper(chars, index + 1);
        }
    }
    // 从第一个非0的数开始打印
    private void printNum(char[] chars) {

        boolean isStart = true;
        for(char c: chars) {
            if(isStart && c != '0')
                isStart = false;
            if(!isStart)
                System.out.print(c);
        }
        if(!isStart)
            System.out.println();
    }

    public static void main(String[] args) {
        (new Solution17_2()).printNumbers(3);
    }

}
