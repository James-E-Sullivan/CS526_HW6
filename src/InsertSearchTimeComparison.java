import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

public class InsertSearchTimeComparison {

    public void testDataStructures(){
        HashMap<Integer, Integer> myMap = new HashMap<>();
        ArrayList<Integer> myArrayList = new ArrayList<>();
        LinkedList<Integer> myLinkedList = new LinkedList<>();

        for(int i=0; i<10; i++){
            int[] keys = new int[100000];

            // generate 100,000 random numbers
            Random r = new Random();
            r.setSeed(System.currentTimeMillis());

            int j = 0;
            while (j < 100000){
                keys[j] = r.nextInt(100000);
                j++;
            }

            for(int x : keys){
                myMap.put(x, keys[x]);
                myArrayList.add(x);
                myLinkedList.add(x);
            }

            //test
            System.out.println("HashMap Size: " + myMap.size());
            System.out.println("ArrayList Size: " + myArrayList.size());
            System.out.println("LinkedList Size: " + myLinkedList.size());
        }


    }



}
