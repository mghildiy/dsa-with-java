package ds.queue;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
A queue is a data structure which follows FIFO. Elements are remvoed in roder in which they were put.
*/
public class Playground {

    public static void main(String[] args) {
        /*
         A priority queue inserts elements with some priority associated with them. So when elements are removed,
         one with highest priority is removed first. PriorityQueue class in java is general purpose queue implementation in java.
         It uses heap data structure and hence has O(log n) performance for adding and retrieval.
         Priority of elements is decided by natural ordering of elements(Comparable interface),OR imposed by an implementation of Comparator interface.
         If 2 elements have same priority then FIFO is followed.
         */
        System.out.println("Priority queue");
        Queue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.add("Totti");
        priorityQueue.add("Messi");
        priorityQueue.add("Ronaldo");
        priorityQueue.add("Pele");
        priorityQueue.add("Maradona");
        iterateAndRemove(priorityQueue); // Messi, Maradona removed as they are of higher priority
    }

    public static void iterateAndRemove(Queue<String> queue) {
        Iterator itr = queue.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        queue.remove();
        queue.remove();
        System.out.println("Iterating after removing elements");
        itr = queue.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
