package question2;


import java.util.LinkedList;
import java.util.Queue;

class BinaryTreeNode{
    BinaryTreeNode left;
    BinaryTreeNode right;
    int data;

    BinaryTreeNode(int data){
        this.data=data;
        this.left=this.right=null;
    }
    BinaryTreeNode(){

    }

    public BinaryTreeNode addToTree(Object[] input) {
        if (input == null || input.length == 0) {
            return null;
        }

        BinaryTreeNode root = new BinaryTreeNode((int) input[0]);
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        for (int i = 1; i < input.length; i += 2) {
            BinaryTreeNode curr = queue.poll();
            if (input[i] != null) {
                curr.left = new BinaryTreeNode((int) input[i]);
                queue.offer(curr.left);
            }
            if (i+1 < input.length && input[i+1] != null) {
                curr.right = new BinaryTreeNode((int) input[i+1]);
                queue.offer(curr.right);
            }
        }

        return root;
    }


}



class ConstructionServiceCenter{
    int res = 0;
    public int minCameraCover(BinaryTreeNode root) {

        return (dfs(root) < 1 ? 1 : 0) + res;
    }

    public int dfs(BinaryTreeNode root) {

        if (root == null) return 2;
        int left = dfs(root.left), right = dfs(root.right);
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }
        return left == 1 || right == 1 ? 2 : 0;
    }

    public static void main(String[] args) {
        Object[] tree= {0,0, null, 0, null, 0, null, null, 0 , 0 ,null,0};
        BinaryTreeNode root = new BinaryTreeNode().addToTree(tree);
        int ans = new ConstructionServiceCenter().minCameraCover(root);
        System.out.println(ans);


    }

}
