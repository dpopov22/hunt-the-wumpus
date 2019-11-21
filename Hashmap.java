/**
 * File: Hashmap.java
 * Author: Domnika Popov
 * Date: 04/15/2019
 */
 
import java.util.ArrayList;
import java.util.Comparator;

public class Hashmap<K,V> implements MapSet<K,V>
{

	public int numCollisions; 
	private Object[] hashArray; 
	private Comparator<K> comp;
	private int numElems; 
	
	//constructor 
	public Hashmap(int arraySize, Comparator<K> comp)
	{
		numCollisions = 0; 
		numElems = 0; 
		this.comp = comp; 
		this.hashArray = new Object[arraySize];
		for(int i= 0; i<hashArray.length; i++)
			{
				this.hashArray[i] = null; 
			}	
	}
    
    /**
    *puts a key value pair into the map, making sure to call ensure capacity 
    *to make sure that the hashmap has enough room in it and increments elements and 
    *number of collisions when necessary in order to ensure accurate results 
    */
    public V put( K new_key, V new_value )
    {
    	ensureCapacity(); 
    	int hash = new_key.hashCode(); 
		int index = hash % this.hashArray.length;
		index = Math.abs(index); 
		if (((BSTMap<K,V>)hashArray[index]) == null)
		{
			numElems++; 
			this.hashArray[index] = new BSTMap<K,V>( comp ); 
			((BSTMap<K,V>)this.hashArray[index]).put(new_key, new_value); 
			return null; 	
		}
		else if(((BSTMap<K,V>)hashArray[index]).containsKey(new_key))
		{
			return ((BSTMap<K,V>)hashArray[index]).put(new_key, new_value); 	
		}
		else
		{
			numElems++; 
			numCollisions++; 
			return ((BSTMap<K,V>)hashArray[index]).put(new_key, new_value); 
		}
    }
    
	// Returns true if the map contains a key-value pair with the given key
    public boolean containsKey( K key )
    {
    
    	if (get(key)== null)
    	{
    		return false; 
    	}
    	
    	return true; 
    }
    
    //gets the key in the map 
    public V get( K key )
    {
    	int hash = key.hashCode(); 
    	int index = hash % this.hashArray.length;
		index = Math.abs(index); 
		if (((BSTMap<K,V>)hashArray[index]) == null)
    	{
    		return null; 
    	}
    	else
    	{
    		return ((BSTMap<K,V>)hashArray[index]).get(key);
    	}
		
    }
    //returns the keys in the map 
    public ArrayList<K> keySet()
    {
    	ArrayList<K> key = new ArrayList<K>();  
    	for (int i= 0; i<hashArray.length; i++)
    	{
    		if (((BSTMap<K,V>)hashArray[i]) != null)
    		{
    			key.addAll(((BSTMap<K,V>)hashArray[i]).keySet());
    		} 
    	}
    	return key; 
    }

	//returns the values of each key in the map 
    public ArrayList<V> values()
    {
    	ArrayList<V> value = new ArrayList<V>(); 
    	for (int i= 0; i<hashArray.length; i++)
    	{ 
    		if (((BSTMap<K,V>)hashArray[i]) != null)
    		{

    			value.addAll(((BSTMap<K,V>)hashArray[i]).values()); 
    		}
    	}
    	return value; 
    }
    
    //returns the entry set of key value pairs 
    public ArrayList<KeyValuePair<K,V>> entrySet()
    {
    	ArrayList<KeyValuePair<K,V>> entry = new ArrayList<KeyValuePair<K,V>>(); 
    	for (int i= 0; i<hashArray.length; i++)
    	{
    		if (((BSTMap<K,V>)hashArray[i]) != null)
    		{
    			entry.addAll(((BSTMap<K,V>)hashArray[i]).entrySet()); 
    		}
    	} 
    	return entry; 
    }
    //returns the size of the hashmap
    public int size()
    {
    	int size = 0; 
    	for (int i= 0; i<hashArray.length; i++)
    	{
    		if((BSTMap<K,V>)hashArray[i] != null)
    		{
    			size += ((BSTMap<K,V>)hashArray[i]).size(); 
    		}
    	}	
    	return size; 
	} 
	
	//clears the hashmap 
    public void clear()
    {
    	for (int i= 0; i<hashArray.length; i++)
    	{
    		if (((BSTMap<K,V>)hashArray[i]) != null)
    		{
    			((BSTMap<K,V>)hashArray[i]).clear(); 
    		}
    	}
    }
    //returns -1 as the depth 
    public int depth()
    {
    	return -1; 
    }
    
    ///check to see if the table is more than half full, if it is then make a new table and put 
    //all of the current pairs into it 
    public void ensureCapacity()
    {
    	if (numElems > hashArray.length/2)
    	{
    		Object oldArray[] = this.hashArray; 
    		this.hashArray = new BSTMap[oldArray.length*2]; 	
			for (int i= 0; i<oldArray.length*2; i++)
			{	
				this.hashArray[i] = null; 
			}
			this.numElems = 0; 
			this.numCollisions = 0; 
			for(Object map: oldArray)
			{
				if(map != null)
				{
					ArrayList<KeyValuePair<K,V>> entries = ((BSTMap<K,V>)map).entrySet(); 
					for(KeyValuePair<K,V> elem: entries)
					{
						this.put(elem.getKey(), elem.getValue()); 
					}
				}
			}
		}
    }

	//main method
    public static void main( String[] argv ) 
    {
            // create a BSTMap
            Hashmap<String, Integer>  map = new Hashmap<String, Integer> (10, new StringAscending());

            map.put( "twenty", 20 );
            map.put( "ten", 10 );
            map.put( "eleven", 11 );
            map.put( "five", 5 );
            map.put( "six", 6 );

            System.out.println(map.get( "eleven" ) );
			System.out.println(map.containsKey("twenty")); 
			System.out.println(map.containsKey("three")); 
			System.out.println(map.values()); 
			System.out.println(map.entrySet()); 
			System.out.println(map.size()); 
			
			map.clear(); 
			System.out.println(map.size()); 
			System.out.println(map.entrySet()); 
			
			System.out.println(map.get( "eleven" ) );
			System.out.println(map.containsKey("twenty")); 
			System.out.println(map.containsKey("three")); 
			System.out.println(map.values()); 
			System.out.println(map.entrySet()); 
			System.out.println(map.size()); 
			
    }

}

