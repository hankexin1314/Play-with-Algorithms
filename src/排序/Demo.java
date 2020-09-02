package 排序;

import java.util.Arrays;
import java.util.Comparator;

public class Demo {

    class Card implements Comparable<Card>{
        int val;
        int h;


        @Override
        public int compareTo(Card o) {
            return 0;
        }
    }

    public void solution(Card[] cards) {

        Arrays.sort(cards);

    }

    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE > Integer.MAX_VALUE);
    }
}
