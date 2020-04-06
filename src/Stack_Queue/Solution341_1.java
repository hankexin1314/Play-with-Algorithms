package Stack_Queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 递归
public class Solution341_1 {

    public class NestedIterator implements Iterator<Integer> {

        List<Integer> list = new ArrayList<>();
        int index = 0;

        public NestedIterator(List<NestedInteger> nestedList) {
            prepareList(nestedList);
        }

        @Override
        public Integer next() {
            Integer res = list.get(index);
            index ++;
            return res;
        }

        @Override
        public boolean hasNext() {
            return index < list.size();
        }

        private void prepareList(List<NestedInteger> nestedList) {
            for(NestedInteger n: nestedList) {
                if(n.isInteger())
                    list.add(n.getInteger());
                else
                    prepareList(n.getList());
            }
        }
    }
}
