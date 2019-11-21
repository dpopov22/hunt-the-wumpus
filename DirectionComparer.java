/**
 * File: DirectionComparer.java
 * Author: Domnika Popov
 * Date: 05/10/2019
 */
 
import java.util.Comparator;
//comparator for direction
class DirectionComparer implements Comparator<Direction> {
		public int compare( Direction a, Direction b ) {
				return a.compareTo(b);
		}
}