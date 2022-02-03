package ds.tree.binarytree;

import ds.tree.binarytree.bst.BSTree;

public class Test {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.add(25);
        tree.add(20);
        tree.add(15);
        tree.add(27);
        tree.add(30);
        tree.add(29);
        tree.add(26);
        tree.add(22);
        tree.add(32);
        System.out.println("Max root to leaf sum:"+ tree.maxRootToLeafSum());

        tree = new BinaryTree();
        tree.add(5);
        tree.add(11);
        tree.add(3);
        tree.add(4);
        tree.add(2);
        tree.add(1);
        System.out.println("Max root to leaf sum:"+ tree.maxRootToLeafSum());
    }
}
