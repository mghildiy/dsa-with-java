package ds.tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    protected Node root;

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void add(int value) {
        if(this.root == null) {
            this.root = new Node(value);
        } else {
            addNodeIteratively(root, value);
        }
    }

    private void addNodeIteratively(Node node, int value) {
        if(node.getValue() == value)
            return;

        Queue<Node> queue = new LinkedList();
        queue.add(node);
        while(!queue.isEmpty()) {
            Node head = queue.remove();
            if(head.getLeft() == null) {
                head.setLeft(new Node(value));
                return;
            } else{
                queue.add(head.getLeft());
            }
            if(head.getRight() == null) {
                head.setRight(new Node(value));
                return;
            } else{
                queue.add(head.getRight());
            }
        }
    }

    public  int maxRootToLeafSum() {
        if(this.root == null) return 0;

        return maxRootToLeafSumRecursively(this.root, 0);
    }

    private int maxRootToLeafSumRecursively(Node node, int sumSoFar) {
        if(node == null) return sumSoFar;

        return Math.max(maxRootToLeafSumRecursively(node.getLeft(), sumSoFar + node.getValue()),
                maxRootToLeafSumRecursively(node.getRight(), sumSoFar + node.getValue()));
    }
}
