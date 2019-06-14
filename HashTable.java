/**
 * HashTable.java
 * @author Kexin Shu
 * @author Yunting Lin
 * CIS 22C, Lab 7
 */
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HashTable<T extends Comparable<T>> {

	private int numElements;
	private ArrayList<List<T>> Video;

	/**
	 * Constructor for the HashTable.java class. Initializes the Table to be sized
	 * according to value passed in as a parameter Inserts size empty Lists into the
	 * table. Sets numElements to 0
	 * 
	 * @param size the table size
	 */
	public HashTable(int size) {
		Video = new ArrayList<List<T>>(size);
		int count = size;
		while (size > 0) {
			Video.add(new List<T>());
			size--;
		}
		numElements = 0;
	}

	/** Accessors */

	/**
	 * returns the hash value in the Table for a given Object
	 * 
	 * @param t the Object
	 * @return the index in the Table
	 */
	private int hash(T t) {
		int code = t.hashCode();
		return code % Video.size();
	}
	
	/**
	 * returns total number of elements in the Table
	 * 
	 * @return total number of elements
	 */
	public int getNumElements() {
		return numElements;
	}

	/**
	 * searches for a specified element in the Table
	 * 
	 * @param t the element to search for
	 * @return the index in the Table (0 to Table.length - 1) or -1 if t is not in
	 *         the Table
	 */
	public boolean search(T t) {
		for (int i = 0; i < Video.size(); i++) {
			if (Video.get(i).linearSearch(t) != -1) {
				return true;
			}
		}
		return false;
	}

	/** Manipulation Procedures */

	/**
	 * inserts a new element in the Table calls the hash method to determine
	 * placement
	 * 
	 * @param t the element to insert
	 */
	public void insert(T t) {
		int index = hash(t);
		Video.get(index).addLast(t);
		numElements++;
	}

	/**
	 * removes the element t from the Table calls the hash method on the key to
	 * determine correct placement has no effect if t is not in the Table
	 * 
	 * @param t the key to remove
	 * @precondition t must be in the table
	 * @throws NoSuchElementException when the element is not in the table
	 */
	public void remove(T t) throws NoSuchElementException {
		if (search(t) == false) {
			throw new NoSuchElementException("remove: Cannot remove when the element is not in the table");
		}
		int index = hash(t);
		int indexInList = Video.get(index).linearSearch(t);
		Video.get(index).placeIterator();
		Video.get(index).advanceToIndex(indexInList);
		Video.get(index).removeIterator();
		numElements--;
	}
}
