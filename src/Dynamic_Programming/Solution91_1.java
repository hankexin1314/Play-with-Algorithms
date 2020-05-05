package Dynamic_Programming;

// 递归
public class Solution91_1 {


    public int numDecodings(String s) {

        if(s == null) return 0;
        char[] cs = s.toCharArray();
        return numDecodings(cs, 0, cs.length - 1);
    }

    // [l, r]
    private int numDecodings(char[] cs, int l, int r) {

        if(l > r || cs[l] == '0') return 0;
        else {
            if(l == r) return 1; // 剩最后一位 且不为0
            else { // r > l 剩两位或以上 且 第一位不为0
                int num = 10 * (cs[l] - '0') + cs[l + 1] - '0';
                if(l + 1 == r) { // 剩2位
                    if (num != 10 && num != 20 && num <= 26) return 2;
                    if (num == 10 || num == 20) return 1;
                    if (num % 10 == 0) return 0; // num > 26 且为整十数
                    return 1; // > 26 但是不为整十数
                }
                else {
                    if (num != 10 && num != 20 && num <= 26) return numDecodings(cs, l + 1, r) + numDecodings(cs, l + 2, r);
                    if (num == 10 || num == 20) return numDecodings(cs, l + 2, r);
                    if (num % 10 == 0) return 0; // num > 26 且为整十数
                    return numDecodings(cs, l + 1, r); // > 26 但是不为整十数
                }
            }
        }
    }
}

