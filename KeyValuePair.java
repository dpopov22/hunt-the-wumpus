/**
 * File: KeyValuePair.java
 * Author: Domnika Popov
 * Date: 04/08/2019
 */
public class KeyValuePair<Key,Value>
{
	private Key k; 
	private Value v; 

	
	//the constructor initializing the key and value fields.
	public KeyValuePair( Key k, Value v ) 
	{
		this.k = k; 
		this.v = v; 
	}
	//returns the key 
	public Key getKey()
	{
		return this.k; 
	}
	//returns the value
	public Value getValue()
	{
		return this.v; 
	}
	//sets the value
	public void setValue( Value v ) 
	{
		this.v = v; 
	}
	
	//returns a String containing both the key and value.
	public String toString() 
	{
		String s = ""; 
		s+= ""; 
		s+= this.getKey();
		s+= ":"; 
		s+= this.getValue(); 
		return s; 
	}
}