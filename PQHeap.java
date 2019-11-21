import java.util.Comparator;
public class PQHeap<T>
{

	private int numElems; 
	private  Comparator comp;
	private Object[] heap; 
	
	//a constructor that initializes the empty heap, sets the size to zero, and stores the comparator.
	public PQHeap(Comparator comp)
	{
		this.comp = comp; 
		this.heap = new Object[2000000];	
		numElems = 0; 
	}
	//returns the number of elements in the heap 
	public int size() 
	{
		return numElems; 
	}
	
	//reheaps the heap after something is added, swapping the parent with the child if necessary 
	public void reheapUp()
	{
		int childIndex = numElems - 1; 
		int parentIndex; 
		while (childIndex >0)
		{
			parentIndex = (childIndex - 1)/2; 
			if(this.comp.compare(this.heap[parentIndex], this.heap[childIndex] ) <0)
			{
				T obj = (T)heap[childIndex]; 
				this.heap[childIndex] = this.heap[parentIndex]; 
				this.heap[parentIndex] = obj; 
			}
			childIndex = parentIndex; 
		}
	}
	/**reheaps the heap after something is removed, comparing the left and right kids with 
	**eachother as well as with the parent in order to make sure that the heap is in the
	correct order*/
	
	private void reheapDown() {
		int parentIdx = 0;
		int lChildIdx = 2*parentIdx+1;
		int rChildIdx = lChildIdx+1;
		while ( lChildIdx < this.numElems ) {
			int plcomp = this.comp.compare( this.heap[parentIdx], this.heap[lChildIdx] );
			int prcomp = 0;
			int lrcomp = 0;
			if (rChildIdx < this.numElems) {
				prcomp = this.comp.compare( this.heap[parentIdx], this.heap[rChildIdx] );
				lrcomp = this.comp.compare( this.heap[lChildIdx], this.heap[rChildIdx] );
			}
			//swap with left kid
			if (plcomp < 0 && lrcomp >= 0) {
				T tmp = (T)this.heap[parentIdx];
				this.heap[parentIdx] = this.heap[lChildIdx];
				this.heap[lChildIdx] = tmp;
				parentIdx = lChildIdx;
			}
			//swap with right kid
			else if (prcomp < 0 && lrcomp < 0) {
				T tmp = (T)this.heap[parentIdx];
				this.heap[parentIdx] = this.heap[rChildIdx];
				this.heap[rChildIdx] = tmp;
				parentIdx = rChildIdx;
			}
			else {break;}
			lChildIdx = 2*parentIdx+1;
			rChildIdx = lChildIdx+1;
		}
	}
	//adds the value to the heap and increments the size
	public void add(T obj)
	{
		this.heap[numElems] = obj; 
		this.numElems++; 	
		reheapUp(); 
	}
	
	//removes and returns the highest priority element from the heap.
	public T remove()
	{
		if(numElems == 0)
		{
			return null; 
		}
		T obj = (T)this.heap[0]; 
		this.heap[0] = null; 
		numElems--; 
		if (numElems == 0)
		{
			return obj; 
		}
		this.heap[0] = this.heap[numElems]; 
		this.heap[numElems] = null; 
		reheapDown(); 
		return obj; 
	}
	//prints the heap
	public void printHeap() 
	{
		for (int i = 0; i < this.numElems; i++) 
		{
			System.out.print( this.heap[i] + " " );
		}
	}
	
	public static void main(String[] args ) {
		IntegerComparing comp = new IntegerComparing(); 
		PQHeap<Integer> pq = new PQHeap(comp);
		pq.printHeap();
		pq.add( new Integer(1) );
		pq.printHeap();
		pq.add( new Integer(10) );
		pq.printHeap();
		pq.add( new Integer(12) );
		pq.printHeap();
		pq.add( new Integer(7) );
		pq.printHeap();
		pq.add( new Integer(8) );
		pq.printHeap();
		pq.remove();
		pq.printHeap();
	}

}
//comparator
class IntegerComparing implements Comparator<Integer> {
		public int compare( Integer a, Integer b ) {
				return b.compareTo(b);
		}
}