/**
 * File: LinkedList.java
 * Author: Domnika Popov
 * Date: 03/11/2019
 */
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random; 

public class LinkedList<T> implements Iterable<T>
{

	private Node head; 
	private int numItems; 

	//constructor
	private class Node
	{
	
	private Node next; 
	private T data; 
		
		//constructor
		public Node(T item)
		{
			this.next = null; 
			this.data = item; 
		}
		//gets value
		public T getValue() 
		{
			return this.data; 		
		}
		//sets the next node
		public void setNext(Node n)
		{
			this.next = n; 
		}
		//gets the next node
		public Node getNext() 
		{
			return this.next; 
		}
	}
	
	//iterator
	public Iterator <T> iterator()
	{
		return new LLIterator(this.head); 
	}
	//sets head to null and number of items to 0(size)
	public LinkedList()
	{
		this.head = null;
		this.numItems = 0;
	}
	//clears list
	public void clear()
	{
		this.head = null;
		this.numItems = 0;
	}
	//gets size of list
	public int size()
	{
		return this.numItems; 
	}
	
	//adds an item to the beginning of the list
	public void addFirst(T item)
	{
		Node n = new Node( item );
		if (this.numItems == 0)
		{
			this.head = n; 
			this.numItems++;
		}
		else
		{
			Node old = this.head; 
			this.head = n; 
			this.head.setNext(old); 
			this.numItems++; 
		}
	}
	//adds an item to the end of the list
	public void addLast(T item) 
	{
		if (this.numItems == 0) {
			addFirst( item );
			return;			
		}    	
		Node n = new Node( item );
		Node last = this.head;
		while ( last.next != null ) {
			last = last.next;
		}
		last.next = n;
		this.numItems++;	
	}
	//adds at any index
	public void add(int index, T item)
	{
		if (index == 0)
		{
			this.addFirst(item); 
			return; 
			
		}
		Node n = new Node( item); 
		Node p = this.head; 
		for ( int i = 0; i < index-1; i++)
		{
			p.getNext(); 
			p = p.getNext(); 
		}
			Node stored = p.getNext(); 
			p.next = n; 
			n.setNext(stored); 
			this.numItems++; 
				
	}
	//removes at any index
	public T remove (int index) 
	{
		if (index >= this.numItems)
		{
			return null; 
		}
		if (index ==0)
		{
			//remove the head node
			T val = this.head.getValue(); 
			this.head = this.head.getNext(); 
			this.numItems--; 
			return val; 
		}
		//remove a node other than head 
		//make p a point to the node before the one we want to remove 
		else
		{
			Node p = this.head; 
			for (int i= 0; i<index-1; i++)
			{
				p = p.getNext(); 
			}
			T val = p.getNext().getValue(); 
			p.setNext(p.getNext().getNext()); 
			return val; 
			
		}
	}
	//returns an ArrayList of the list contents in order.
	public ArrayList<T> toArrayList() 
	{
		ArrayList<T> list = new ArrayList<T>(); 
		Node pointer = this.head;  
		while (pointer != null)
		{
			pointer = pointer.getNext(); 
			list.add(pointer.getValue()); 
		}
		return list; 
		
	}
	//returns an ArrayList of the list contents in shuffled order
	public ArrayList<T> toShuffledList()
	{
		ArrayList<T> firstList = new ArrayList<T>(); 
		ArrayList <T> shuffledList = new ArrayList<T>(); 
		Random rand = new Random(); 
		
		while (firstList.size() > 0)
		{
			int randomNumber = rand.nextInt(firstList.size()); 
			shuffledList.add(firstList.remove(randomNumber)); 
		}
		return shuffledList; 
		
	}
	//Iterator subclass that handles traversing the lis
	private class LLIterator implements Iterator <T>
	{
	
		private Node current; 
	
		public LLIterator(Node head)
		{
			this.current = head; 
		}
	
		public boolean hasNext()
		{
			return this.current != null; 
		}
	
		public T next()
		{
			T ret = this.current.getValue(); 
			this.current = this.current.getNext(); 
			return ret; 
		}
		public void remove()
		{
			;
		}
	}

}
