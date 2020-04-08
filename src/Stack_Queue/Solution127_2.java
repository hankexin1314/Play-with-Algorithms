package Stack_Queue;

import javafx.util.Pair;

import java.util.*;

public class Solution127_2 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // 1. 预处理wordList
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        int L = beginWord.length(); // 为单词的长度

        for(String str: wordList) {
            for(int i = 0; i < L; i++) { // 一个单词所有可能的配对 hot -> *ot h*t ho*
                String newWord = str.substring(0, i) + "*" + str.substring(i + 1, L); // 配对的单词
                ArrayList<String> val = map.getOrDefault(newWord, new ArrayList<String>());
                val.add(str);
                map.put(newWord, val);
            }
        }

        // 2. 广度优先遍历
        Queue<Pair<String, Integer>> q = new LinkedList<>(); // Pair中存储字符串和对应的序列的长度
        q.offer(new Pair<>(beginWord, 1));
        HashMap<String, Boolean> visited = new HashMap<>(); // 是否访问的辅助数组
        visited.put(beginWord, true);

        while(!q.isEmpty()) {

            Pair<String, Integer> tmp = q.poll();
            int len = tmp.getValue();
            String str = tmp.getKey();
            // 对于队列中得到的单词，遍历所有可能的配对
            for(int i = 0; i < L; i++) {
                String newWord = str.substring(0, i) + "*" + str.substring(i + 1, L);
                // 得到配对所对应的单词的列表
                ArrayList<String> val = map.getOrDefault(newWord, new ArrayList<String>());
                for(String s : val) {
                    // 如果配对对应的单词列表中含有endWord 那么str只需要再替换一个单词就可以得到结果
                    if(s.equals(endWord))
                        return len + 1;
                    // 如果配对对应的单词 还未访问过
                    if(!visited.getOrDefault(s, false)) {
                        visited.put(s, true);
                        q.offer(new Pair<>(s, len + 1));
                    }
                }
            }
        }

        return 0;
    }

}
