/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 10000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
	  LinkedQueue[] queues = new LinkedQueue[q.size() + 1];
	  int i = 1;
	  while(!q.isEmpty()){
		  queues[i] = new LinkedQueue();
		  try {
			  queues[i].enqueue(q.dequeue());
		  } catch(QueueEmptyException e) {
			  System.out.println(e);
		  }
		  i++;
	  }
	  LinkedQueue queue = new LinkedQueue();
	  for(int j = 1; j < queues.length; j++) {
		  queue.enqueue(queues[j]);
	  }
    return queue;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
	  LinkedQueue q = new LinkedQueue();
	  while(q1.size() > 0 && q2.size() > 0) {
		  try{ 
			  if(((Comparable)q1.front()).compareTo(((Comparable)q2.front())) < 0) {
				  q.enqueue(q1.dequeue());
			  } else {
				  q.enqueue(q2.dequeue());
			  }
		  } catch(QueueEmptyException e) {
			  System.out.println(e);
		  }
	  }
	  while(q1.size() > 0) {
		  q.append(q1);
	  }
	  while(q2.size() > 0) {
		  q.append(q2);
	  }
	  return q;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
	  while(qIn.size() > 0) {
		  try{
			  if(((Comparable)qIn.front()).compareTo(pivot) < 0) {
				  qSmall.enqueue(qIn.dequeue());
			  } else if(((Comparable)qIn.front()).compareTo(pivot) > 0) {
				  qLarge.enqueue(qIn.dequeue());
			  } else {
				  qEquals.enqueue(qIn.dequeue());
			  }
		  } catch(QueueEmptyException e) {
			  System.out.println(e);
		  }
	  }
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
	  LinkedQueue queues = makeQueueOfQueues(q);
	  while(queues.size() > 0) {
		  if(queues.size() == 1) {
			  q.append(queues);
		  } else {
			  try{
				  queues.enqueue(mergeSortedQueues((LinkedQueue)queues.dequeue(), (LinkedQueue)queues.dequeue()));
			  } catch(QueueEmptyException e) {
				  System.out.println(e);
			  }
		  }
	  }
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.
	  LinkedQueue qSmall = new LinkedQueue();
	  LinkedQueue qLarge = new LinkedQueue();
	  LinkedQueue qEqual = new LinkedQueue();
	  if(q.size() > 0) {
		  int index = (int)(Math.random() * (q.size() - 1) + 1); //Math.random() generate 0.0 to 1.0
		  Comparable pivot = (Comparable)q.nth(index);
		  partition(q, pivot, qSmall, qEqual, qLarge);
		/*  System.out.println("pivot :" + pivot);
		  System.out.println("small :" + qSmall);
		  System.out.println("equal :" + qEqual);
		  System.out.println("large :" + qLarge);*/
		  quickSort(qSmall);
		  quickSort(qLarge);
	  }
	  q.append(qSmall);
	  q.append(qEqual);
	  q.append(qLarge);
	  
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *Implement mergeSort(), which sorts a LinkedQueue q as follows.  First, use
makeQueueOfQueues() to convert q into a queue of queues.  Repeatedly do the
following:  remove two items from the queue of queues, merge them with
mergeSortedQueues(), and enqueue the resulting queue on the queue of queues.
When there is only one queue left on the queue of queues, move its items back
to q (preferably using the fast append() method).

  public static void mergeSort(LinkedQueue q);  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

      //Test makeQueueOfQueues
	  LinkedQueue q1 = makeRandom(10);
	  System.out.println(q1.toString());
	  System.out.println(makeQueueOfQueues(q1).toString());
	  System.out.println(q1.size());
	  
	  //Test mergeSortedQueues
	  LinkedQueue q2 = makeRandom(10);
	  q1 = makeRandom(10);
	  System.out.println(q1.toString());
	  System.out.println(q2.toString());
	  System.out.println(mergeSortedQueues(q1, q2).toString());
	  System.out.println(q1.size());
	  System.out.println(q2.size());
	  
	  //Test partition
	  LinkedQueue q3 = makeRandom(10);
	  LinkedQueue small = new LinkedQueue();
	  LinkedQueue equal = new LinkedQueue();
	  LinkedQueue large = new LinkedQueue();
	  System.out.println(q3.toString());
	  System.out.println(q3.nth(1));
	  partition(q3, (Comparable)q3.nth(1), small, equal, large);
	  
	  System.out.println(q3);
	  System.out.println(small);
	  System.out.println(equal);
	  System.out.println(large);
	  
	  
	LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    // Remove these comments for Part III.
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    
  }

}
