package algos.exercises;

public class ReverseLinkedLIst {
}

class LinkedList<T> {
    Node<T> head;

    LinkedList(Node<T> first) {
        head = first;
    }

    void add(Node<T> element) {
        element.next = head.next;
        head = element;
    }
}

class Node<T> {
    T val;
    Node<T> next;
    Node(T v) {
        val = v;
    }
}
