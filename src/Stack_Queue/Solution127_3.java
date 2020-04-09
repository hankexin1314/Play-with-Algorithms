package Stack_Queue;

import javafx.util.Pair;

import java.util.*;


// 双端BFS
public class Solution127_3 {

    private int L;
    private HashMap<String, ArrayList<String>> worldMap;

    public Solution127_3() {
        this.L = 0;
        this.worldMap = new HashMap<>();
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if(!wordList.contains(endWord))
            return 0;

        // 预处理wordList
        this.L = beginWord.length(); // 单词的长度
        for (String str : wordList) {
            for (int i = 0; i < L; i++) {
                String newWorld = str.substring(0, i) + "*" + str.substring(i + 1, L);
                ArrayList<String> tmp = this.worldMap.getOrDefault(newWorld, new ArrayList<String>());
                tmp.add(str);
                this.worldMap.put(newWorld, tmp);
            }
        }

        // 双向BFS
        Queue<Pair<String, Integer>> beginQ = new LinkedList<>();
        HashMap<String, Integer> beginVisited = new HashMap<>();
        beginQ.offer(new Pair<>(beginWord, 1));
        beginVisited.put(beginWord, 1);

        Queue<Pair<String, Integer>> endQ = new LinkedList<>();
        HashMap<String, Integer> endVisited = new HashMap<>();
        endQ.offer(new Pair<>(endWord, 1));
        endVisited.put(endWord, 1);


        while(!beginQ.isEmpty() && !endQ.isEmpty()) {

            int ans = visitWorldNode(beginQ, beginVisited, endVisited);
            if(ans > -1)
                return ans;

            ans = visitWorldNode(endQ, endVisited, beginVisited);
            if(ans > -1)
                return ans;
        }

        return 0;
    }

    private int visitWorldNode(
            Queue<Pair<String, Integer>> q,
            HashMap<String, Integer> visited,
            HashMap<String, Integer> otherVisited) {

        Pair<String, Integer> node = q.poll();
        String str = node.getKey();
        int len = node.getValue();

        for(int i = 0; i < this.L; i++) {
            String newWord = str.substring(0, i) + "*" + str.substring(i + 1, this.L);
            ArrayList<String> arr = this.worldMap.getOrDefault(newWord, new ArrayList<String>());
            for(String s: arr) {
                if(otherVisited.containsKey(s))
                    return len + otherVisited.get(s);
                if(!visited.containsKey(s)) {
                    q.offer(new Pair<>(s, len + 1));
                    visited.put(s, len + 1);
                }
            }
        }
        return -1;
    }



}
