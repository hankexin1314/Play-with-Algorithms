package Group_Anagrams_49;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for(String str: strs) {
            String arr = strSort(str);
            if(map.containsKey(arr))
                map.get(arr).add(str);
            else {
                ArrayList<String> a = new ArrayList<>();
                a.add(str);
                map.put(arr, a);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for(String key: map.keySet()) {
            List<String> subRes;
            subRes = map.get(key);
            res.add(subRes);
        }

        return res;

    }

    private String strSort(String str) {

        int[] count = new int[26];
        char[] c = str.toCharArray();
        for(char i: c)
            count[i - 'a']++;

        StringBuilder sb = new StringBuilder("");
        for(int i = 0 ; i < 26; i++) {
            sb.append('#');
            sb.append(count[i]);
        }

        return sb.toString();
    }


}
