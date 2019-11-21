/**
 * File: StringAscending.java
 * Author: Domnika Popov
 * Date: 04/08/2019
 */
 
import java.util.Comparator;
//allows for comparator to be used 
class StringAscending implements Comparator<String> {
		public int compare( String a, String b ) {
				return a.compareTo(b);
		}
}