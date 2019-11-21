// PQHeapTester.java
// Stephanie Taylor
// Test the PQHeap. Uses a heap of integers, then a heap of key-value pairs.
import java.util.Comparator;

public class PQHeapTester {
    public static void main( String[] args ) {
        PQHeap<Integer> pq = new PQHeap<Integer>(new TestIntComparator());
        for (int i = 10; i <= 100; i += 10 ) {
            pq.add( i );
        }
        Integer itg = null;
        Integer hmm = 100;
        while (true) {
            itg = pq.remove();
            if (itg == null)
                break;
            System.out.println( "Removing a number, it should be " + hmm + " and it is " + itg );
            hmm -= 10;
        }

        int test[] = {20,10,40,90,50,100,30,60,70,80};
        pq = new PQHeap<Integer>(new TestIntComparator());
        for (int i = 0; i < test.length; i++)
            pq.add( test[i] );
        hmm = 100;
        while (true) {
            itg = pq.remove();
            if (itg == null)
                break;
            System.out.println( "Removing a number, it should be " + hmm + " and it is " + itg );
            hmm -= 10;
        }
            
        // Here begins the test code involving KeyValue pairs. You can begin with it commented out,
        // if you want to.
        PQHeap<KeyValuePair<Integer,Float>> ppq = new PQHeap<KeyValuePair<Integer,Float>>(new KVTestComparator());
        ppq.add( new KeyValuePair<Integer,Float>( new Integer(1), new Float(2.0) ) );
        ppq.add( new KeyValuePair<Integer,Float>( new Integer(3), new Float(1.0) ) );
        ppq.add( new KeyValuePair<Integer,Float>( new Integer(4), new Float(0.0) ) );
        KeyValuePair<Integer,Float> pair = ppq.remove();
        System.out.println( "Removing a pair, which should be (4, 0.0) and is " + pair  );
        pair = ppq.remove();
        System.out.println( "Removing a pair, which should be (3, 1.0) and is " + pair  );
        pair = ppq.remove();
        System.out.println( "Removing a pair, which should be (1, 2.0) and is " + pair  );
        pair = ppq.remove();
        System.out.println( "Removing a pair, which should be null and is " + pair  );
        
    } // end main

} // end class PQHeapTester

// for testing purposes!!!
class TestIntComparator implements Comparator<Integer> {
    public TestIntComparator() {;}
    
    public int compare(Integer o1, Integer o2) {
        return o1-o2;
    }   
}

// More code that uses the KeyValuePair!!
// To sort by Value in to put smallest as the first removed
class KVTestComparator implements Comparator<KeyValuePair<Integer,Float>> {
    public int compare( KeyValuePair<Integer,Float> i1, KeyValuePair<Integer,Float> i2 ) {
        // returns negative number if i2 comes after i1 lexicographically
        float diff = i1.getValue() - i2.getValue();
        if (diff == 0.0)
            return 0;
        if (diff < 0.0)
            return 1;
        else
            return -1;
    }
}
