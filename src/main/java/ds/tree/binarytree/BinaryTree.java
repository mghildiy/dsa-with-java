package ds.tree.binarytree;

import java.util.*;

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
        if(node == null) {
            return sumSoFar;
        }

        return Math.max(maxRootToLeafSumRecursively(node.getLeft(), sumSoFar + node.getValue()),
                maxRootToLeafSumRecursively(node.getRight(), sumSoFar + node.getValue()));
    }

    public List<Node> findAncestors(int value) {
        if(this.root == null) {
            throw new IllegalStateException("Tree is empty");
        }

        List<Node> ancestors = findAncestorsRecursively(this.root, value);
        if(!ancestors.isEmpty()) {
            ancestors.remove(0);
            return ancestors;
        }
        throw new IllegalStateException("Element not found");
    }

    public List<Node> findAncestorsRecursively(Node node, int value) {
        if(node == null) {
            return new ArrayList<>();
        }
        if(node.getValue() == value){
            return new ArrayList<>(Arrays.asList(node));
        }

        List<Node> leftSideAncestors = findAncestorsRecursively(node.getLeft(), value);
        if(!leftSideAncestors.isEmpty()) {
            leftSideAncestors.add(node);
            return leftSideAncestors;
        } else {
            List<Node> rightSideAncestors = findAncestorsRecursively(node.getRight(), value);
            if(!rightSideAncestors.isEmpty()) {
                rightSideAncestors.add(node);
                return rightSideAncestors;
            }
        }

        return new ArrayList<>();
    }

    public List<Node>  findLeftView() {
        List<Node> leftView = new ArrayList<>();
        return findLeftViewRecursively(this.root);
    }

    private List<Node> findLeftViewRecursively(Node node) {
        List<Node> leftView = new ArrayList<>();
        if(node == null) return leftView;

        leftView.add(node);
        List<Node> leftLeftView = findLeftViewRecursively(node.getLeft());
        List<Node> rightLeftView = findLeftViewRecursively(node.getRight());

        // merge if needed
        if(leftLeftView.size() < rightLeftView.size()) {
            leftLeftView.addAll(rightLeftView.subList(leftLeftView.size(), rightLeftView.size()));
        }
        leftView.addAll(leftLeftView);

        return leftView;
    }

    /*private void findLeftViewRecursively(Node node, List<Node> li) {
        if(node == null) return;
        li.add(node);
        if(node.getLeft() == null && node.getRight() == null) return;

        if(node.getLeft() != null) {
            findLeftViewRecursively(node.getLeft(), li);
        } else {
            //li.add(node.getRight());
            findLeftViewRecursively(node.getRight(), li);
        }
    }*/

    public List<Node> findLeftViewIterativeLy() {
        Map<Integer, List<Node>> levelByNodes = new HashMap<>();
        List<Node> leftView = new ArrayList<>();
        if(this.root == null) return leftView;
        levelByNodes.put(1, Arrays.asList(this.root));
        int currentLevel = 1;
        while(levelByNodes.get(currentLevel).size() != 0) {
            List<Node> nodesInCurrentLevel = levelByNodes.get(currentLevel);
            leftView.add(nodesInCurrentLevel.get(0));
            List<Node> nodesInNextLevel = new ArrayList<>();
            for(Node node : nodesInCurrentLevel) {
                if(node.getLeft() != null) {
                    nodesInNextLevel.add(node.getLeft());
                }
                if(node.getRight() != null) {
                    nodesInNextLevel.add(node.getRight());
                }
            }
            ++currentLevel;
            levelByNodes.put(currentLevel, nodesInNextLevel);
        }

        return leftView;
    }
}
