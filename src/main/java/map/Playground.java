package map;

import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Playground {

    public static void main(String[] args) {
        System.out.println("Hello maps");

        ExecutorService executor = Executors.newFixedThreadPool(10);

        Map<String, String> myMap = new HashMap<>();
        myMap.put("India", "New Delhi");
        myMap.put("USA", "Washington DC");
        // iterator is backed by underlying collection, so any change(increase or decrease) in size of collection would cause java.util.ConcurrentModificationException
        // To avoid it, remove elements using Iterator.remove()
        // Updating an existing mapping is harmless
        Iterator itr = myMap.entrySet().iterator();
        System.out.println(myMap.size());
        while(itr.hasNext()) {
            Map.Entry e = (Map.Entry) itr.next();
            System.out.println(e.getKey());
            System.out.println(e.getValue());
            //myMap.put("UK", "London");
            //itr.remove();
            System.out.println(myMap.size());
        }


        IntStream.rangeClosed(1, 10).forEach(index -> {
            Runnable runnable = () -> {
                try {
                    Random rn = new Random();
                    //TimeUnit.MILLISECONDS.sleep(50 + rn.nextInt(100 - 50 + 1));
                    byte[] array = new byte[7];
                    rn.nextBytes(array);
                    String generatedString = new String(array, Charset.forName("US-ASCII"));
                    System.out.println(generatedString);
                    myMap.put("India", generatedString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            executor.execute(runnable);
        });
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*
         Concurrent map provides thread safety(unlike Map) with better performance(compared to thread saf options like HashTable, Collections.synchronizedMap).
         Basic mechanism is to maintain a list of locks(number of locks is configured using concurrencyLevel which has default value of 16).
         So at a given time number of threads which can manipulate(add, update) map is decided by concurrencyLevel.
         If there are  than threads than concurrencyLevel it would lead to contention, meaning performance degradation.
         Underlying data-structure used is an array of Node<K, V> where Node is an inner class and it has structure to support linked list.
         When a key-value pair is added,key is hashed to determine index in array. If another key-value addition maps
         key to an already occupied index position in array(collision), Node is added at end of linked list, which means performance of retrieval goes
         down as more and more collisions happen, and it means resizing is required.
         When a request arrives to remove based on an input key, value for it is found which is then used as synchronization lock.
         This way only that bucket is locked for modification which contains the value.
         Read operations don't lock though.
         null is not allowed as key or value to avoid ambiguity. If user first calls containsKey which returns true and then
         calls get, there may be ambiguous result as between 2 calls map may change and null key may have been removed
          which means get would return null, thus causing ambiguity.
        */
        ConcurrentMap<String, String> myConcurrentMap = new ConcurrentHashMap<>();
        myConcurrentMap.put("India", "New Delhi");
        myConcurrentMap.putIfAbsent("India", "New Delhi");


        System.out.println("FINISH");
    }
}
