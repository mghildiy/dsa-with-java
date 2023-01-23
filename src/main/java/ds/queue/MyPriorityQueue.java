package ds.queue;

import java.util.Optional;

public interface MyPriorityQueue<T extends Comparable> {

    void insert(T data);

    Optional<T> peek();

    Optional<T> poll();
}
