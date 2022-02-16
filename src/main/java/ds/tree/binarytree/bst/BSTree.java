package ds.tree.binarytree.bst;

import ds.tree.binarytree.BinaryTree;
import ds.tree.binarytree.Node;
import io.vavr.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BSTree extends BinaryTree {

    //private Node root;

    public void add(int value) {
        if(this.root == null) {
            this.root = new Node(value);
        } else {
            addNodeRecursively(root, value);
        }
    }

    private void addNodeRecursively(Node node, int value) {
        if(node.getValue() == value)
            return;
        if(node.getValue() > value) {
            if(node.getLeft() == null) {
                Node newNode = new Node(value);
                node.setLeft(newNode);
            } else {
                addNodeRecursively(node.getLeft(), value);
            }
        } else {
            if(node.getRight() == null) {
                Node newNode = new Node(value);
                node.setRight(newNode);
            } else {
                addNodeRecursively(node.getRight(), value);
            }
        }
    }

    public void delete(int value) {
        if(this.root == null)
            return;

        Tuple2<Node, Node> parentAndChild = searchWithParent(root, value, null);
        if(parentAndChild != null) {
            if(parentAndChild._1 == null) {
                this.root = null;
            } else {
                Node parent = parentAndChild._1;
                Node child = parentAndChild._2;
                if(parent.getLeft() == child) {
                    parent.setLeft(rearrangeNodesForDeletion(child));
                } else {
                    parent.setRight(rearrangeNodesForDeletion(child));
                }
            }
        }
    }

    private Node rearrangeNodesForDeletion(Node node) {
        // both child are absent, so parent of input node would get null child
        if(node.getLeft() == null && node.getRight() == null) {
            return null;
        } else {
            // if one of the child is absent, other child would become child of parent of input node
            return node.getLeft() == null ? node.getRight() : node.getLeft();
            // WIP
            // if both child are present, left child of input node would now become child of parent of input node
        }
    }

    private Tuple2<Node, Node> searchWithParent(Node node, int value, Node parent) {
        if(node.getValue() == value) {
            Tuple2<Node, Node> parentAndChild = new Tuple2<>(parent, node);
            return parentAndChild;
        }

        if(node.getValue() > value) {
            if(node.getLeft() != null) {
                return searchWithParent(node.getLeft(), value, node);
            }
        } else {
            if(node.getRight() != null) {
                return searchWithParent(node.getRight(), value, node);
            }
        }

        return null;
    }

    public Node search(int value) {
        if(this.root == null)
            return null;

        return searchRecursively(this.root, value);
    }

    private Node searchRecursively(Node node, int value) {
        if(node.getValue() == value) {
            return node;
        }

        if(node.getValue() > value) {
            if(node.getLeft() != null) {
                return searchRecursively(node.getLeft(), value);
            }
        } else {
            if(node.getRight() != null) {
                return searchRecursively(node.getRight(), value);
            }
        }

        return null;
    }

    public int min() {
        if(this.root == null) {
            throw new IllegalStateException("Tree has no data");
        }

        return searchFroMinRecursively(this.root);
    }

    private int searchFroMinRecursively(Node node) {
        if(node.getLeft() != null) {
            return searchFroMinRecursively(node.getLeft());
        } else {
            return node.getValue();
        }
    }

    public int max() {
        if(this.root == null) {
            throw new IllegalStateException("Tree has no data");
        }

        return searchFroMaxRecursively(this.root);
    }

    private int searchFroMaxRecursively(Node node) {
        if(node.getRight() != null) {
            return searchFroMaxRecursively(node.getRight());
        } else {
            return node.getValue();
        }
    }

    // access all nodes at a level and then next level and so on
    public void levelTraverse() {
        if(this.root == null) {
            return;
        }

        levelTraverse(new ArrayList<>(Arrays.asList(this.root)), 1);
    }

     private void levelTraverse(List<Node> nodes, int level) {
        if(nodes.size() == 0) return;
        System.out.println(String.format("At level %s", level));
        List<Node> children = new ArrayList<>();
        nodes.forEach(node -> {
            System.out.println(node.getValue());
            if(node.getLeft() != null) children.add(node.getLeft());
            if(node.getRight() != null) children.add(node.getRight());
        });
         levelTraverse(children, level+1);
     }

     // pre-order traverse: one of the Depth First approach
     // access node, then left and then right, and then their children
     public void preOrderTraverse() {
         if(this.root == null) {
             return;
         }

         preOrderTraverse(this.root);
     }

     private void preOrderTraverse(Node node) {
        if(node == null) return;
        System.out.println(node.getValue());
        preOrderTraverse(node.getLeft());
        preOrderTraverse(node.getRight());
     }

     // in-order traverse: one of the Depth First approach
     // access left first, then root, then right
     // as we move from left to right, so data is accessed in sorted manner
     public void inOrderTraverse() {
         if(this.root == null) {
             return;
         }
         inOrderTraverse(this.root);
     }

    private void inOrderTraverse(Node node) {
        if(node == null) return;
        inOrderTraverse(node.getLeft());
        System.out.println(node.getValue());
        inOrderTraverse(node.getRight());
    }

    // post-order traverse: one of the Depth First approach
    // access left first, then right, then root
    public void postOrderTraverse() {
        if(this.root == null) {
            return;
        }
        postOrderTraverse(this.root);
    }

    public void postOrderTraverse(Node node) {
        if(node == null) return;
        postOrderTraverse(node.getLeft());
        postOrderTraverse(node.getRight());
        System.out.println(node.getValue());
    }

    public void invert() {
        if(this.root == null || (this.root.getLeft() == null && this.root.getRight() == null))
            return;

        invertRecursively(this.root);
    }

    public void invertRecursively(Node node) {
        if(node == null)
            return;

        Node right = node.getRight();
        node.setRight(node.getLeft());
        node.setLeft(right);
        invertRecursively(node.getLeft());
        invertRecursively(node.getRight());
    }

    public int sum() {
        if(this.root == null) return 0;

        return sumRecursively(this.root, 0);
    }

    private int sumRecursively(Node node, int sumSoFar) {
        if(node == null) return sumSoFar;

        int leftSum = sumRecursively(node.getLeft(), sumSoFar + node.getValue());
        return sumRecursively(node.getRight(), leftSum);
    }
}
