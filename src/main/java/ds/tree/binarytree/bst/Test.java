package ds.tree.binarytree.bst;

import ds.tree.binarytree.Node;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        BSTree tree = new BSTree();
        tree.add(25);
        tree.add(20);
        tree.add(15);
        tree.add(27);
        tree.add(30);
        tree.add(29);
        tree.add(26);
        tree.add(22);
        tree.add(32);

        List<Node> leftView = tree.findLeftViewIterativeLy();
        leftView = tree.findLeftView();

        // tree.levelTraverse();
        // tree.preOrderTraverse();
        tree.inOrderTraverse();
        // tree.postOrderTraverse();

        int valueToSearch = 22;
        if(tree.search(valueToSearch) != null) {
            System.out.println("Value found");
        } else {
            System.out.println("Value not found");
        }

        System.out.println("Minimum value:"+ tree.min());
        System.out.println("Minimum value:"+ tree.max());

        tree.invert();
        System.out.println("Inverted");

        System.out.println("Tree sum:"+ tree.sum());

        System.out.println("Max root to leaf sum:"+ tree.maxRootToLeafSum());
    }
}
