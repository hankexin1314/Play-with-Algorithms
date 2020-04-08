package Stack_Queue;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution127_1 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if(!wordList.contains(endWord))
            return 0;
        Queue<Pair<String, Integer>> q = new LinkedList<>();
        q.offer(new Pair<>(endWord, 1));
        boolean[] visited = new boolean[wordList.size()];
        visited[wordList.indexOf(endWord)] = true;

        while(!q.isEmpty()) {

            Pair<String, Integer> tmp = q.poll();
            String str = tmp.getKey();
            int step = tmp.getValue();

            if(isLadder(beginWord, str))
                return step + 1;

            for(int i = 0; i < wordList.size(); i++) {
                if(!visited[i]) {
                    String a = wordList.get(i);
                    if (isLadder(str, a)){
                        if (isLadder(beginWord, a)) return step + 2; // 可以提高速度
                        q.offer(new Pair<>(a, step + 1));
                        visited[i] = true;
                    }
                }
            }
        }

        return 0;
    }

    private boolean isLadder(String s1, String s2) { // 检验两个单词是否可以接龙（是否只有一个字母不相同）

        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int diff = 0; // 不相同的字母的个数

        for(int i = 0; i < arr1.length; i++) {
            if(diff > 1)
                return false;
            if(arr1[i] != arr2[i])
                diff++;
        }
        return diff == 1;
    }
}
