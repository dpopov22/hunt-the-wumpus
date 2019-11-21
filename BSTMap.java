/**
 * File: BSTMap.java
 * Author: Domnika Popov
 * Date: 04/08/2019
 */

import java.util.ArrayList;
import java.util.Comparator;

public class BSTMap<K, V> implements MapSet<K, V> {
	private TNode root; 
	private Comparator<K> comp;
	private int size; 

	// constructor: takes in a Comparator object
	public BSTMap( Comparator<K> comp ) {
		this.root = root; 
		this.comp = comp; 
		this.size = size; 
	}
	
	//toString function for the BSTMap 
	public String toString() 
	{
		String ret = "root:";
		if (this.root != null) {
			ret += this.root.preOrderString( " " );
		}
		return ret;
	}
	//returns the depth of the tree
	public int depth() {
		if (this.root != null) {
			return this.root.depth();
		}
		return 0;
	}
	//puts a key and value in the tree
	public V put( K key, V value ) {
				// check for and handle the special case
				if (this.root == null)
				{
					size = 1; 
					this.root = new TNode(key, value); 
					return null; 
					
				}
				// call the root node's put method
				return this.root.put(key, value, comp); 
        }
    //Removes a key value pair in the binary tree by changing the tree to a list and 
    //then taking out that pair in the list. After doing so this method rebuilds the tree
	public void remove(KeyValuePair<K, V> dataPair)
	{
		if(this.root == null)
		{
		
		}
		else
		{
			ArrayList<KeyValuePair<K, V>> rList = entrySet(); 
			BSTMap<K, V> map = new BSTMap<K, V>(comp); 
			int counter = 0; 
			for(KeyValuePair<K, V> pair : rList)
			{
				if(pair.getKey() == dataPair.getKey())
				{
					break; 
				}
				counter++; 
			}
			rList.remove(counter); 
			this.root = null; 
			
			for(KeyValuePair<K, V> keyValue: rList)
			{
				map.put(keyValue.getKey(), keyValue.getValue()); 
			}
			this.root = map.root; 
		}
	}
	
    // gets the value at the specified key or null
    public V get( K key ) {
            // check for and handle the special case
            if (this.root == null)
            {
            	return null; 
            }
            // call the root node's get method
            return this.root.get(key, comp); 
            // stub code
            // return null;
    }
    //returns the size
    public int size()
    {
    	return size; 
    }
    //returns the containsKey method called on the root if it is not null
    public boolean containsKey( K key )
    {
    	if (this.root != null) 
    	{
			return this.root.containsKey(key);
		}
		return false;
	}
	
	//returns the array list of keys 
	public ArrayList<K> keySet()
    {
		ArrayList<K> key = new ArrayList<K>();
		if (this.root != null)
		{
			this.root.keySet(key); 
		}  
		return key;    	
    }

	//returns the array list of values
	public ArrayList<V> values()
	{
		ArrayList<V> value = new ArrayList<V>();
		if (this.root != null)
		{
			this.root.values(value); 
		}  
		return value;  	
	}

	//returns an array list of key value pairs
	public ArrayList<KeyValuePair<K,V>> entrySet()
	{
		ArrayList<KeyValuePair<K,V>> entry = new ArrayList<KeyValuePair<K,V>>();
		if (this.root != null)
		{
			this.root.entrySet(entry); 
		}  
		return entry;  
	}
	
	//sets the root to null
	public void clear()
	{
		this.size = 0; 
		this.root = null; 
	}

	//node class
    private class TNode {
        TNode left; 
		TNode right;
		KeyValuePair<K, V> pair; 

            // constructor, given a key and a value
            public TNode( K k, V v ) {
                    this.left = null; 
                    this.right = null;
                    this.pair = new KeyValuePair<K, V>(k, v);  
                    
            }

            // Takes in a key, a value, and a comparator and inserts the TNode
            // Returns the old value of the node, if replaced, or null if inserted
            public V put( K key, V value, Comparator<K> comp ) 
            {
                    int c = comp.compare(key, pair.getKey()); 
                    if (c == 0)
                    {
                    	this.pair.setValue(value);  
                    }
                    if (c< 0)
                    {
 						if (this.left == null) 
 						{
							this.left = new TNode( key, value );
							size++; 
							return null; 
						}
						else 
						{
					 		return this.left.put( key, value, comp );
					 		
						}
                    }
                    else if (c>0)
                    {
						if (this.right == null) 
						{
							this.right = new TNode( key, value);
							size++; 
							return null; 
						}
						else 
						{
					 		return this.right.put( key, value, comp );
					 	}
					}
                    return null;
            }

            // Takes in a key and a comparator
            // Returns the value associated with the key or null
            public V get( K key, Comparator<K> comp ) 
            {
           		int c = comp.compare(key, pair.getKey());
            	if ( c == 0)
            	{
            		return pair.getValue(); 
            	}
            	if (c < 0)
            	{
            		if (this.left == null)
            		{
            			return null; 
            		}
            		else
            		{
            			return this.left.get(key, comp); 	
            		}
            	}
            	else
            	{
            		if (this.right == null)
            		{
            			
            			return null; 
            		}
            		else
            		{
            			return this.right.get(key, comp); 
            		}
            	}
            }
            // Returns true if the map contains a key-value pair with the given key
            public boolean containsKey( K key )
            {
				int c = comp.compare(key, pair.getKey()); 
				if (c == 0) { return true; }
				if (c < 0) 
				{
					if (this.left == null) 
					{
						return false;
					}
					else 
					{
						return this.left.containsKey( key );
					}
				}
				else 
				{
					if (this.right == null) 
					{
						return false;
					}
					else 
					{
						 return this.right.containsKey( key );
					}
				}  
            }
             
             // Returns an ArrayList of all the keys in the map.
            public void keySet (ArrayList<K> keys)
            {
            	keys.add(this.pair.getKey()); 
            	if (this.left != null)
            	{
            		this.left.keySet(keys); 
            	}
            	if (this.right != null)
            	{
            		this.right.keySet(keys); 
            	}	
            }

    		// Returns an ArrayList of all the values in the map
    		public void values (ArrayList<V> value)
    		{
    			value.add(this.pair.getValue()); 
    			if (this.left != null)
            	{
            		this.left.values(value); 
            	}
            	if (this.right != null)
            	{
            		this.right.values(value); 
            	}	
    		
    		}
    
    		// Return an ArrayList of pairs in a pre-order traversal.
    		public void entrySet(ArrayList<KeyValuePair<K,V>> valuePair)
    		{
    			valuePair.add(this.pair); 
    			if (this.left != null)
            	{
            		this.left.entrySet(valuePair); 
            	}
            	if (this.right != null)
            	{
            		this.right.entrySet(valuePair); 
            	}	
    		}
         
         //toString method that creates the structure of the tree   
        public String preOrderString( String indentation ) {
			String ret = indentation + this.pair.getKey() + ":" + this.pair.getValue() + "\n";
			if (this.left != null) {
				ret += "left: " + this.left.preOrderString( indentation + "     " );
			}
			if (this.right != null) {
				ret += "right:" + this.right.preOrderString( indentation + "    " );
			}
			return ret;
		}
		 //returns the depth of the tree, accounts for if the depth of the left or right
		 //side of the tree are 0   
		public int depth() 
    	{
			int ldepth;
			int rdepth;
			if (this.left == null) 
			{
				ldepth = 0;
			}
			else 
			{
				ldepth = this.left.depth();
			}
			if (this.right == null) 
			{
				rdepth = 0;
			}
			else 
			{
				rdepth = this.right.depth();
			}
			return Math.max( ldepth, rdepth ) + 1;
			}
    	}

    // test function
    public static void main( String[] argv ) {
            // create a BSTMap
            BSTMap<String, Integer> map = new BSTMap<String, Integer>( new StringAscending() );

            map.put( "twenty", 20 );
            map.put( "ten", 10 );
            map.put( "eleven", 11 );
            map.put( "five", 5 );
            map.put( "six", 6 );
            System.out.println("Original BST Map:" +"\n" + map); 

//             System.out.println( bst.get( "eleven" ) );
// 			System.out.println(bst.containsKey("twenty")); 
// 			System.out.println(bst.containsKey("three")); 
// 			System.out.println(bst.values()); 
// 			System.out.println(bst.entrySet()); 
// 			System.out.println(bst.size()); 
// 			
// 			bst.clear(); 
// 			System.out.println(bst.size()); 
// 	// 		
// 			System.out.println( bst.get( "eleven" ) );
// 			System.out.println(bst.containsKey("twenty")); 
// 			System.out.println(bst.containsKey("three")); 
// 			System.out.println(bst.values()); 
// 			System.out.println(bst.entrySet()); 
// 			System.out.println(bst.size()); 
			map.remove(new KeyValuePair<String, Integer>("twenty", 20)); 
			System.out.println("Removed 'twenty 20' from BST Map: "); 	
			System.out.println(map); 
			
			map.remove(new KeyValuePair<String, Integer>("eleven", 11)); 
			System.out.println("Removed 'eleven 11' from BST Map: "); 	
			System.out.println(map); 
			
			
    }

}

// comparator class separate 
