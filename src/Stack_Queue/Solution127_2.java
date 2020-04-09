package Stack_Queue;

import javafx.util.Pair;

import java.util.*;

public class Solution127_2 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // 预处理wordList
        HashMap<String, ArrayList<String>> worldMap = new HashMap<>();
        int L = beginWord.length(); // 单词的长度
        for (String str : wordList) {
            for (int i = 0; i < L; i++) {
                String newWorld = str.substring(0, i) + "*" + str.substring(i + 1, L);
                ArrayList<String> tmp = worldMap.getOrDefault(newWorld, new ArrayList<String>());
                tmp.add(str);
                worldMap.put(newWorld, tmp);
            }
        }

        // 广度优先遍历
        Queue<Pair<String, Integer>> q = new LinkedList<>();
        q.offer(new Pair<>(beginWord, 1));
        HashSet<String> visited = new HashSet<>(); // 在set中说明被访问过
        visited.add(beginWord);
        while (!q.isEmpty()) {
            Pair<String, Integer> tmp = q.poll();
            String str = tmp.getKey();
            int len = tmp.getValue();

            for (int i = 0; i < L; i++) {
                String newWorld = str.substring(0, i) + "*" + str.substring(i + 1, L);
                ArrayList<String> tmpList = worldMap.getOrDefault(newWorld, new ArrayList<String>());
                for (String s : tmpList) {
                    if (s.equals(endWord))
                        return len + 1;
                    if (!visited.contains(s)) {
                        q.offer(new Pair<>(s, len + 1));
                        visited.add(s);
                    }
                }
            }
        }

        return 0;
    }
}
