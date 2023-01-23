package ds.tree.heap;


import java.util.*;

/*
https://courses.cs.washington.edu/courses/cse373/02au/lectures/lecture11l.pdf
 */

record Data(int x){};
public class MyHeap<T>  implements MyPriorityQueue<T> {

    private List<T> nodes = new ArrayList<T>();
    private Comparator<T> comparator;

    public MyHeap(Comparator<T> compr) {
        comparator = compr;
    }

    // O(log(n))
    @Override
    public void insert(T data) {
        nodes.add(data);
        moveUp(nodes.size() - 1);
    }

    private void moveUp(int child) {
        int parent = (child - 1) / 2;
        if(comparator.compare(nodeAt(parent), nodeAt(child)) > 0) {
            swap(parent, child);
            moveUp(parent);
        }
    }

    private void swap(int i, int j) {
        T temp = nodes.get(i);
        nodes.set(i, nodes.get(j));
        nodes.set(j, temp);
    }

    private boolean isLeaf(int index) {
        return 2*index+1 > nodes.size() - 1 &&  2*index+2 > nodes.size() - 1;
    }

    // O(n * log(n))
    public void makeHeap(List<T> inputs) {
        inputs.forEach(input -> insert(input));
    }

    // O(1)
    public Optional<T> peek() {
        if(nodes.size() == 0)
            return Optional.empty();
        else
            return Optional.of(nodes.get(0));
    }

    // O(log(n))
    @Override
    public Optional<T> poll() {
        if(nodes.size() == 0) {
            return Optional.empty();
        } else {
            Optional<T> root = Optional.of(nodes.get(0));
            maintainHeapOrder();
            return root;
        }
    }

    private void maintainHeapOrder() {
        if (nodes.size() == 1) {
            nodes.clear();
        } else {
            nodes.set(0, nodes.get(nodes.size() - 1));
            nodes.remove(nodes.size() - 1);
            moveDown(0);
        }
    }

    private void moveDown(int index) {
        if(isLeaf(index)) return;

        T root = nodeAt(index);
        T left = nodeAt(2 * index + 1);
        T right = nodeAt(2 * index + 2);
        if(comparator.compare(root, left) < 1) {
            if(comparator.compare(root, right) < 1) {
                return;
            } else {
                swap(index, 2 * index + 2);
                moveDown(2 * index + 2);
            }
        } else {
            if(comparator.compare(left, right) < 1) {
                swap(index, 2 * index + 1);
                moveDown(2 * index + 1);
            } else {
                swap(index, 2 * index + 2);
                moveDown(2 * index + 2);
            }
        }
    }

    private T nodeAt(int index) {
        if(nodes.size() - 1 >= index) {
            return nodes.get(index);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Comparator<Data> compr = new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                if(o1 == null && o2 == null)
                    return 0;
                if(null == o1) return 1;
                if(null == o2) return -1;
                if(o1.x() == o2.x()) return 0;
                if(o1.x() < o2.x()) return -1;
                else return 1;
            }
        };
        MyHeap<Data> heap = new MyHeap(compr);
        List<Data> inputs = Arrays.asList(new Data(10), new Data(9), new Data(11), new Data(7));
        heap.makeHeap(inputs);

        heap.nodes.forEach(data -> System.out.println(data));
        System.out.println(heap.peek());

        System.out.println("Sorted");
        Optional<Data> root = heap.poll();
        while(root.isPresent()) {
            System.out.println(root);
            root = heap.poll();
        }
    }
}
