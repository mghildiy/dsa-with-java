package ds.tree.binarytree;

import java.util.*;

// https://www.geeksforgeeks.org/top-50-tree-coding-problems-for-interviews/
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

    public void add(Integer value) {
        if(this.root == null) {
            this.root = new Node(value);
        } else {
            addNodeIteratively(root, value);
        }
    }

    public Node search(int value) {
        if(this.root == null)
            return null;

        return searchRecursively(this.root, value);
    }

    private Node searchRecursively(Node node, int value) {
        if(node == null)
            return null;
        if(node.getValue() == value) {
            return node;
        }

        Node nodeInLeft = searchRecursively(node.getLeft(), value);
        if(nodeInLeft != null)
            return nodeInLeft;
        else
            return searchRecursively(node.getRight(), value);
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

    public int height() {
        int res1 = findHeightRecursivelyAlt(this.root, -1);

        int res2 = findHeightRecursively(this.root);

        if(res1 != res2) {
            System.out.println("Code not working as expected");
            return -1;
        }

        return res2;
    }

    public int findHeightRecursivelyAlt(Node node, int heightSoFar) {
        if(node == null)
            return heightSoFar;

        return Math.max(findHeightRecursivelyAlt(node.getLeft(), heightSoFar+1),
                findHeightRecursivelyAlt(node.getRight(), heightSoFar+1));
    }

    public int findHeightRecursively(Node node) {
        if(node == null) return 0;
        /*if(node.getRight() == null && node.getLeft() == null)
            return 1;
        if(node.getLeft() == null)
            return 1 + findHeightRecursively(node.getRight());
        if(node.getRight() == null)
            return 1 + findHeightRecursively(node.getLeft());*/

        return 1 + Math.max(findHeightRecursively(node.getLeft()), findHeightRecursively(node.getRight()));
    }

    public boolean isIdentical(BinaryTree other) {
        return checkIfIdenticalRecursively(this.root, other.root);
    }

    private boolean checkIfIdenticalRecursively(Node node1, Node node2) {
        if(node1 == null && node2 == null)
            return true;
        if(node1 == null && node2 != null)
            return false;
        if(node1 != null && node2 == null)
            return false;
        if(node1.getValue() == node2.getValue()) {
            return checkIfIdenticalRecursively(node1.getLeft(), node2.getLeft()) &&
                    checkIfIdenticalRecursively(node1.getRight(), node2.getRight());
        } else {
            return false;
        }
    }

    public void mirror() {
        mirrorRecursively(this.root);
    }

    private void mirrorRecursively(Node node) {
        if(node == null)
            return;

        Node left = node.getLeft();
        Node right = node.getRight();
        node.setLeft(right);
        node.setRight(left);
        mirrorRecursively(node.getLeft());
        mirrorRecursively(node.getRight());
    }

    public boolean isSymmetric() {
        if(this.root == null)
            return true; // debatable
        return isSymmetricRecursively(this.root.getLeft(), this.root.getRight());
    }

    private boolean isSymmetricRecursively(Node left, Node right) {
        if(left == null && right == null)
            return true;

        if(left != null && right != null) {
            if(left.getValue() == right.getValue()) {
                return isSymmetricRecursively(left.getLeft(), right.getRight())
                        && isSymmetricRecursively(left.getRight(), right.getLeft());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // check that height diff between left and right subtree for every node is not greater than 1
    public boolean isBalanced() {
        if(this.root == null) return true;

        return isBalancedRecursively(this.root, new NodeHeight());
    }

    class NodeHeight {
        int height;
    }

    public boolean isBalancedRecursively(Node node, NodeHeight nodeHeight) {
        if(node == null) return true;
        NodeHeight leftHeight = new NodeHeight();
        NodeHeight rightHeight = new NodeHeight();
        boolean isLeftSideBalanced = isBalancedRecursively(node.getLeft(), leftHeight);
        boolean isRightSideBalanced = isBalancedRecursively(node.getRight(), rightHeight);
        nodeHeight.height = 1 + Math.max(leftHeight.height, rightHeight.height);

        if(Math.abs(leftHeight.height - rightHeight.height) > 1){
            return false;
        }
        if(isLeftSideBalanced && isRightSideBalanced) {
            return true;
        }
        return false;
    }

    //Function to check whether all nodes of a tree have the value equal to the sum of their child nodes.
    public int isSumProperty()
    {
        return isSumPropertyRecursively(this.root);
    }

    private int isSumPropertyRecursively(Node node) {
        if(node == null) return 1;
        if(node.getLeft() == null && node.getRight() == null) return 1;
        int leftValue = (node.getLeft() != null) ? node.getLeft().getValue() : 0;
        int rightValue = (node.getRight() != null) ? node.getRight().getValue() : 0;
        boolean isSumProperty = node.getValue() ==  (leftValue + rightValue);

        if(isSumPropertyRecursively(node.getLeft()) == 1 && isSumPropertyRecursively(node.getRight()) == 1 && isSumProperty) {
            return 1;
        } else
            return 0;
    }

    public boolean isBST() {
        return isBSTRecursively(this.root, new Max());
    }

    class Max {
        int value;
    }

    private boolean isBSTRecursively(Node node, Max max) {
        if (node == null) return true;
        if(node.getLeft() == null && node.getRight() == null) {
            max.value = node.getValue();
            return true;
        }

        if(node.getLeft() == null) {
            Max rightMax = new Max();
            boolean isRightBST = isBSTRecursively(node.getRight(), rightMax);
            max.value = Math.max(node.getValue(), rightMax.value);
            return isRightBST && node.getValue() < rightMax.value;
        } else {
            Max leftMax = new Max();
            boolean isLeftBST = isBSTRecursively(node.getLeft(), leftMax);
            if(node.getRight() == null) {
                max.value = Math.max(node.getValue(), leftMax.value);
                return isLeftBST && node.getValue() > leftMax.value;
            } else {
                Max rightMax = new Max();
                boolean isRightBST = isBSTRecursively(node.getRight(), rightMax);
                max.value = Math.max(Math.max(leftMax.value, node.getValue()), rightMax.value);
                return isLeftBST && isRightBST && leftMax.value < node.getValue() && node.getValue() < rightMax.value;
            }
        }

    }

}
