package list;

public class LockDList extends DList {
	
	public void lockNode(DListNode node) {
		((LockDListNode)node).locked = true;
	}
	
	public boolean getLockStatus(DListNode node) {
		return ((LockDListNode)node).locked;
	}
	
	protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
	    return new LockDListNode(item, prev, next);
	  }
	
	public void remove(DListNode node) {
	    // Your solution here.
		if(((LockDListNode)node).locked == false) {
			super.remove(node);
		} 
	  }
}