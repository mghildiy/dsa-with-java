package ds.tree.heap;

import java.util.Optional;

public interface MyPriorityQueue<T> {

    void insert(T data);

    Optional<T> peek();

    Optional<T> poll();
}
