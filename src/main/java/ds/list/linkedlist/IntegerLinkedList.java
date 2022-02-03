package ds.list.linkedlist;

public class IntegerLinkedList {

    private IntegerNode head;
    private int size;

    public void addToFront(Integer value) {
        IntegerNode node = new IntegerNode(value);
        node.setNext(head);
        head = node;
        size++;
    }

    public IntegerNode removeFromFront() {
        if (isEmpty()) {
            return null;
        }

        IntegerNode removedNode = head;
        head = head.getNext();
        size--;
        removedNode.setNext(null);
        return removedNode;
    }

    public void insertSorted(Integer value) {
        if(size == 0) {
            IntegerNode nodeToBeInserted = new IntegerNode(value);
            nodeToBeInserted.setNext(null);
            head = nodeToBeInserted;
            ++size;
            return;
        }

        IntegerNode nodeToBeInserted = null;
        IntegerNode currentNode = head;
        IntegerNode prevNode = null;
        while(currentNode != null) {
            if(currentNode.getValue() > value) {
                nodeToBeInserted = new IntegerNode(value);
                nodeToBeInserted.setNext(currentNode);
                break;
            }
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }

        if(nodeToBeInserted == null) {
            nodeToBeInserted = new IntegerNode(value);
            nodeToBeInserted.setNext(null);
        }
        if(prevNode != null)
            prevNode.setNext(nodeToBeInserted);
        else {
            head = nodeToBeInserted;
        }
        ++size;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void printList() {
        IntegerNode current = head;
        System.out.print("HEAD -> ");
        while (current != null) {
            System.out.print(current);
            System.out.print(" -> ");
            current = current.getNext();
        }
        System.out.println("null");
    }

    public IntegerLinkedList reverse() {
        if(this.head == null)
            return null;
        if(this.size == 1)
            return this;

        IntegerNode headOfNewList = null;
        IntegerNode current = this.head;
        IntegerNode previous = null;
        while(current != null) {
            IntegerNode next = current.getNext();
            current.setNext(previous);
            IntegerNode prev = next;
            previous = current;
            current = prev;
        }
        headOfNewList = previous;

        IntegerLinkedList reversed =  new IntegerLinkedList();
        reversed.head = headOfNewList;
        reversed.size = this.size;

        return reversed;
    }

}
