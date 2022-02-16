package ds.list.linkedlist;

public class Main {

    public static void main(String[] args) {

        Integer one = 1;
        Integer two = 2;
        Integer three = 3;
        Integer four = 4;

        IntegerLinkedList list = new IntegerLinkedList();
        list.insertSorted(three);
        //list.traverseIteratively();
        list.insertSorted(two);
        //list.traverseIteratively();
        list.insertSorted(one);
        //list.traverseIteratively();
        list.insertSorted(four);
        list.traverseIteratively();
        list.traverseRecursively();

        list = list.reverse();
        list.traverseIteratively();

        System.out.println("Iterative sum:"+list.sumIteratively());
        System.out.println("Recursive sum:"+list.sumRecursively());

        list = list.reverseRecursively();
        System.out.println("Recursive sum:"+list.sumRecursively());
    }
}
