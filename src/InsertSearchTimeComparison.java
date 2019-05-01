import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

public class InsertSearchTimeComparison {

    public static void testDataStructures(){

        // variables to track total insertion time
        Long hashMapTotalInsertionTime = 0L;
        Long arrayListTotalInsertionTime = 0L;
        Long linkedListTotalInsertionTime = 0L;

        // variables to track total search time
        Long hashMapTotalSearchTime = 0L;
        Long arrayListTotalSearchTime = 0L;
        Long linkedListTotalSearchTime = 0L;

        for(int i=0; i<10; i++){

            // create new data structures for each loop
            HashMap<Integer, Integer> myMap = new HashMap<>();
            ArrayList<Integer> myArrayList = new ArrayList<>();
            LinkedList<Integer> myLinkedList = new LinkedList<>();

            int[] keys = new int[100000];   // holds input integers to be inserted
            Long startTime;                 // used to track start time of insert/search
            Long endTime;                   // used to track end time of insert/search


            // ---------- Insertion Time ----------

            // generate 100,000 random numbers
            Random r = new Random(System.currentTimeMillis());
            for (int j=0; j<100000; j++)
                keys[j] = r.nextInt(100000) + 1;

            // test total time of HashMap insertion from keys[]
            startTime = System.currentTimeMillis();
            for(int x=0; x<keys.length; x++)
                myMap.put(keys[x], x);
            endTime = System.currentTimeMillis();
            Long hashMapInsertionTime = endTime - startTime;

            // test total time of ArrayList insertion from keys[]
            startTime = System.currentTimeMillis();
            for(int x : keys)
                myArrayList.add(x);
            endTime = System.currentTimeMillis();
            Long arrayListInsertionTime = endTime - startTime;

            // test total time of LinkedLIst insertion from keys[]
            startTime = System.currentTimeMillis();
            for(int x : keys)
                myLinkedList.add(x);
            endTime = System.currentTimeMillis();
            Long linkedListInsertionTime = endTime - startTime;

            // add insertion times to total variables
            hashMapTotalInsertionTime += hashMapInsertionTime;
            arrayListTotalInsertionTime += arrayListInsertionTime;
            linkedListTotalInsertionTime += linkedListInsertionTime;


            // ---------- Search Time ----------

            // generate 100,000 random integers in range [1, 2,000,000]
            r = new Random(System.currentTimeMillis());
            for (int j=0; j<100000; j++)
                keys[j] = r.nextInt(2000000) + 1;

            // test total time of HashMap search from keys[]
            startTime = System.currentTimeMillis();
            for(int x : keys)
                myMap.containsKey(x);
            endTime = System.currentTimeMillis();
            Long hashMapSearchTime = endTime - startTime;

            // test total time of ArrayList search from keys[]
            startTime = System.currentTimeMillis();
            for(int x : keys)
                myArrayList.contains(x);
            endTime = System.currentTimeMillis();
            Long arrayListSearchTime = endTime - startTime;

            // test total time of LinkedList search from keys[]
            startTime = System.currentTimeMillis();
            for(int x : keys)
                myLinkedList.contains(x);
            endTime = System.currentTimeMillis();
            Long linkedListSearchTime = endTime - startTime;

            // add search time to total variables
            hashMapTotalSearchTime += hashMapSearchTime;
            arrayListTotalSearchTime += arrayListSearchTime;
            linkedListTotalSearchTime += linkedListSearchTime;

            // clear data structures
            myMap.clear();
            myArrayList.clear();
            myLinkedList.clear();
        }

        // print results of insertion/search time test
        System.out.println("Number of keys = 100000");
        System.out.println("\nHashMap average total insert time = " + (hashMapTotalInsertionTime / 10));
        System.out.println("ArrayList average total insert time = " + (arrayListTotalInsertionTime / 10));
        System.out.println("LinkedList average total insert time = " + (linkedListTotalInsertionTime / 10));
        System.out.println("\nHashMap average total search time = " + (hashMapTotalSearchTime / 10));
        System.out.println("ArrayList average total search time = " + (arrayListTotalSearchTime / 10));
        System.out.println("LinkedList average total search time = " + (linkedListTotalSearchTime / 10));
    }

    public static void main(String[] args) {
        testDataStructures();
    }

}
