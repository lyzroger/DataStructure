/* HashTableChained.java */

package dict;
import list.*;
import java.util.*;
/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
	private int num;			//number of entries in the hash table
	private int capacity;		//length of the bucket
	private int prime;			//prime factor of compression function
	//private long scale, shift;  //the scale and shift factors of compression function
	private DList[] table;		//DList Array as the bucket

  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
	  num = 0;
	  capacity = sizeEstimate * 2 + 3;
	  prime = 109345121;
	 // Random rand = new Random();
	  //scale = rand.nextInt(prime - 1) + 1;
	  //shift = rand.nextInt(prime);
	  table = new DList[capacity];
	  
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
	  this(50);
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
	  int bucket_num = (int) ((Math.abs(code * 600 + 99) % prime) % capacity);
    return bucket_num;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return num;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
	  return (num == 0);
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
	  Entry item = new Entry();
	  item.key = key;
	  item.value = value;
	  int index = compFunction(key.hashCode());
	  //System.out.println(key.hashCode());
	  //System.out.println(index);
	  if(table[index] == null) {
		  table[index] = new DList();
	  }
	  table[index].insertBack(item);
	  num++;
	  return item;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
	  int index = compFunction(key.hashCode());
	  if(table[index] != null) {
		  ListNode current = table[index].front();
		  try{
			  while(current != null) {
				  if(((Entry)current.item()).key().equals(key)){
					  return (Entry)current.item();
				  }
				  current = current.next();
			  }
			  return null;
		  } catch(InvalidNodeException e) {
			  System.out.println(e);
		  }
	  }
	  return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
	  int index = compFunction(key.hashCode());
	  if(table[index] != null) {
		  ListNode current = table[index].front();
		  try{
			  while(current != null) {
				  if(((Entry)current.item()).key().equals(key)){
					  current.remove();
					  num--;
					  return (Entry)current.item();
				  }
				  current = current.next();
			  }
			  return null;
		  } catch(InvalidNodeException e) {
			  System.out.println(e);
		  }
	  }
	  return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
	  num = 0;
	  /*
	  for(int i = 0; i < capacity; i++) {
		  table[i] = new DList();
	  }
	  */
	  table = new DList[table.length];
	  
  }
  
  public void sizeOfEachBucket() {
	  int[] entries = new int[capacity];
	  for(int i = 0; i < capacity; i++) {
		  if(table[i] != null) {
			  entries[i] = table[i].size();
		  }
		  System.out.println(entries[i]);
	  }

  }

}



