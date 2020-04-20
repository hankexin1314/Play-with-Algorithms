package Recursion_BackTracking;

import java.util.ArrayList;
import java.util.List;

public class Solution93_1 {
    public List<String> restoreIpAddresses(String s) {

        List<String> res = new ArrayList<>();
        if(s.length() < 4 || s.length() > 12)
            return res;
        int L = s.length();
        for(int i = 1; i <= 3; i++) { // 第一部分几个字符
            if(L - i > 9 || L - i < 3) // 后面还有3部分
                continue;
            String is = s.substring(0, i);
            if(i > 1 && is.charAt(0) == '0')
                continue;
            if(i == 3 && Integer.parseInt(is) > 255)
                continue;
            for (int j = 1; j <= 3; j++) {
                if(L - i - j > 6 || L - i - j < 2) // 后面还有2部分
                    continue;
                String js = s.substring(i, i + j);
                if(j > 1 && js.charAt(0) == '0')
                    continue;
                if(j == 3 && Integer.parseInt(js) > 255)
                    continue;
                for(int k = 1; k <= 3; k++) {
                    if(L - i - j - k > 3 || L - i - j - k < 1) // 后面还有1部分
                        continue;
                    String ks = s.substring(i + j, i + j + k);
                    if(k > 1 && ks.charAt(0) == '0')
                        continue;
                    String ms = s.substring(i + j + k, L);
                    if(ms.length() > 1 && ms.charAt(0) == '0')
                        continue;
                    if(k == 3 && Integer.parseInt(ks) > 255)
                        continue;
                    if(ms.length() == 3 && Integer.parseInt(ms) > 255)
                        continue;
                    StringBuilder sb = new StringBuilder(is);
                    sb.append(".");
                    sb.append(js);
                    sb.append(".");
                    sb.append(ks);
                    sb.append(".");
                    sb.append(ms);
                    res.add(sb.toString());
                }
            }
        }

        return res;
    }

}
