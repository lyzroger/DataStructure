/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
	protected List lst;
	//protected ListNode lstnd;
	protected int size;
  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    // Your solution here.
	  lst = new DList();
	  size = 0;
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return size;
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) {//throws InvalidNodeException{
    // Your solution here.
	  if(lst.isEmpty()) {
		  lst.insertFront(c);
		  size++;
	  } else {
		  tryBlock: //break label
		  try{
			  ListNode current = lst.front(); //creat a ListNode which is the first node of the list
			  while(current.isValidNode()) { //iterate the list
				  //if current node's item is larger than c. Insert c before the current node.
				  //After completion, break the whole try block.
				  if(((Comparable)current.item()).compareTo(c) > 0) { 
					current.insertBefore(c); 
					size++;
					break tryBlock;
				  } else if(((Comparable)current.item()).compareTo(c) < 0) {
					  //if current node's item is smaller than c, then go to next node
					  current = current.next();
				  } else {
					  //if current node's item is same as c, do not insert c,
					  //because we do not accept duplicate nodes, just break the whole try block.
					  break tryBlock;
				  }
			  }
			  //if we have checked all the nodes in the list and c is larger than all of them.
			  //insert c as the last node of the list.
			  lst.insertBack(c);
			  size++;
		  } catch (InvalidNodeException e) {
			  System.out.println(e);
		  }
	  }
	  
	  
  }

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
    // Your solution here.
	  try{
		  
		  ListNode curr1 = lst.front(); //first node of this set.
		  ListNode curr2 = s.lst.front(); //first node of set s.
		  //Both this set and set s have valid nodes to go through
		  while(curr1.isValidNode() && curr2.isValidNode()) {
			  if(((Comparable)curr1.item()).compareTo(((Comparable)curr2.item())) < 0) {
				  //if this set's current node smaller than that of set s.
				  //we go to the next node of this set. Because our set's in ascending order.
				  curr1 = curr1.next();
			  } else if(((Comparable)curr1.item()).compareTo(((Comparable)curr2.item())) > 0){
				  //if this set's current node larger than that of set s.
				  //insert the node of set s right before the current node of this set.
				  //go to the next node of set s.
				  curr1.insertBefore(curr2.item());
				  curr2 = curr2.next();
				  size++;
			  } else {
				  //if two nodes in these two sets are same, we do not accept duplicate nodes
				  //so just go to check the next node of set s.
				  curr2 = curr2.next();
			  }
		  }
		  //if we have already go through all nodes in this set, 
		  //we just simply insert all left nodes in set s at the back of set s.
		  //because set s is sorted. so we can guarantee the ascending order.
		  while(curr2.isValidNode()) {
			  lst.insertBack(curr2.item());
			  curr2 = curr2.next();
			  size++;
		  }
		  //if we have already go through all nodes in set s,
		  //we just leave all remaining nodes in this set.
	  } catch (InvalidNodeException e) {
		  System.out.println(e);
	  }
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
    // Your solution here.
	  try{
		  ListNode curr1 = lst.front();
		  ListNode curr2 = s.lst.front();
		  ListNode temp;
		//Both this set and set s have valid nodes to go through
		  while(curr1.isValidNode() && curr2.isValidNode()) {
			  if(((Comparable)curr1.item()).compareTo(((Comparable)curr2.item())) < 0) {
				  //if this set's current node is smaller than that of set s.
				  //we can remove if from this set. Because all sets are in ascending order. 
				  //there will be no nodes that are same with it.
				  temp = curr1.next();
				  curr1.remove();
				  curr1 = temp;
				  size--;
			  } else if(((Comparable)curr1.item()).compareTo(((Comparable)curr2.item())) > 0){
				  //if this set's current node is larger than that of set s.
				  //go to check the next node of set s.
				  curr2 = curr2.next();
			  } else {
				  //if two nodes are the same.
				  //we keep the node in the this set and go to next node of both sets.
				  curr1 = curr1.next();
				  curr2 = curr2.next();
			  }
		  }
		  //if we have already go through all nodes in set s.
		  //remove all nodes that are not compared in this set, 
		  //because there will be no intersection any more.
		  while(curr1.isValidNode()) {
			  temp = curr1.next();
			  curr1.remove();
			  curr1 = temp;
			  size--;
		  }
		  //if we have already go through all nodes in this set.
		  //we do not need to do anything, because there will be no more intersections.
	  } catch (InvalidNodeException e) {
		  System.out.println(e);
	  }
	  
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString(){
    // Replace the following line with your solution.
	  String result = "{  ";
	    ListNode current = lst.front();
	    while (current.isValidNode()) {
	    	try{
	      result = result + current.item() + "  ";
	      current = current.next();
	    	} catch(InvalidNodeException e){
	    		System.out.println("haha");
	    	}
	    }
	    return result + "}";
  }

  public static void main(String[] argv) throws InvalidNodeException{
    Set s = new Set();
    s.insert(new Integer(3));
    s.insert(new Integer(4));
    s.insert(new Integer(3));
    System.out.println("Set s = " + s);

    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(5));
    System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    System.out.println("Set s3 = " + s3);

    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);

    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);

    System.out.println("s.cardinality() = " + s.cardinality());
    // You may want to add more (ungraded) test code here.
    Set s4 = new Set();
    Set s5 = new Set();
    s4.insert(1);
    s4.insert(2);
    s4.insert(3);
    s4.insert(4);
    s4.insert(5);
    s4.insert(6);
    s5.insert(2);
    s4.union(s5);
    System.out.println(s4);
    s4.intersect(s5);
    System.out.println(s4);
    
    
  }
}
