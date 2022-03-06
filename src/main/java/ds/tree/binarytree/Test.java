package ds.tree.binarytree;

import ds.tree.binarytree.bst.BSTree;

import java.util.Arrays;
import java.util.List;

public class Test {

    private static BinaryTree tree(List<Integer> values) {
        BinaryTree tree = new BinaryTree();
        for(Integer value : values) {
            tree.add(value);
        }

        return tree;
    }

    public static void main(String[] args) {
        BinaryTree tree = tree(Arrays.asList(25, 20, 15, 27, 30, 29, 26, 22, 32));
        /*System.out.println("Max root to leaf sum:"+ tree.maxRootToLeafSum());
        System.out.println("Height:"+ tree.height());
        tree.add(32);
        List leftView = tree.findLeftView();
        tree = tree(Arrays.asList(5, 11, 3, 4, 2, 1));
        System.out.println("Max root to leaf sum:"+ tree.maxRootToLeafSum());
        System.out.println("Ancestors");
        try {
            List<Node> ancestors = tree.findAncestors(4);
            printList(ancestors);
            ancestors = tree.findAncestors(5);
            printList(ancestors);
            ancestors = tree.findAncestors(2);
            printList(ancestors);
            ancestors = tree.findAncestors(3);
            printList(ancestors);
            ancestors = tree.findAncestors(1);
            printList(ancestors);
            ancestors = tree.findAncestors(40);
            printList(ancestors);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        // check identical
        System.out.println("Are identical:"+ tree.isIdentical(tree(Arrays.asList(25, 20, 15, 27, 30, 29, 26, 22, 32))));
        System.out.println("Are identical:"+ tree.isIdentical(tree(Arrays.asList(25, 20, 15, 27, 30, 29, 26, 22, 31))));
        System.out.println("Are identical:"+ tree.isIdentical(tree(Arrays.asList(25, 20, 15, 27, 30, 29, 26, 22))));

        // mirror
        BinaryTree treeToMirror = tree(Arrays.asList(25, 20, 15, 27, 30, 29, 26, 22, 32));
        treeToMirror.mirror();
        System.out.println("Mirrored:"+ treeToMirror);

        //1 2 N N 3
        //1 10 39 5 N 15 20 N N 50 N 30 31 N N N 32
        tree = new BinaryTree();
        tree.add(1);
        tree.search(1).setLeft(new Node(2));
        tree.search(2).setRight(new Node(3));
        /*tree.add(10);
        tree.add(39);
        tree.search(10).setLeft(new Node(5));
        tree.search(39).setLeft(new Node(15));
        tree.search(39).setRight(new Node(20));
        tree.search(15).setLeft(new Node(50));
        tree.search(20).setLeft(new Node(30));
        tree.search(20).setRight(new Node(31));
        tree.search(30).setRight(new Node(32));*/
        System.out.println("Balanced:"+tree.isBalanced());

        tree = new BinaryTree();
        tree.add(10);
        tree.search(10).setLeft(new Node(10));
        System.out.println("IsSumProperty:"+tree.isSumProperty());
    }

    private static void printList(List<Node> nodes) {
        nodes.forEach(node -> System.out.print(node.getValue()+ "<-"));
        System.out.println("null");
    }
}
