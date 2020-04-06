package Stack_Queue;

// 迭代 模拟系统栈
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution94_3 {

    class Command {
        String s; // go visit 分别代表
        TreeNode t;
        Command(String s, TreeNode t) {
            this.s = s;
            this.t = t;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {


        List<Integer> res = new ArrayList<>();
        if(root == null)
            return res;
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));

        while(!stack.isEmpty()) {
            Command cur = stack.pop();
            if(cur.s.equals("visit"))
                res.add(cur.t.val);
            else {
                assert cur.s.equals("go");
                if(cur.t.right != null)
                    stack.push(new Command("go", cur.t.right));
                stack.push(new Command("visit", cur.t));
                if(cur.t.left != null)
                    stack.push(new Command("go", cur.t.left));

            }
        }

        return res;
    }

}


