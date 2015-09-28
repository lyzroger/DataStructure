package list;

public class DListTest {
	public static void main(String[] args) {
		
		testEmpty();
		testNodeReturn();
		testRemove();
		testLockDList();
		
		
	}
	
	protected static void testEmpty() {
		DList list1 = new DList();
		DList list2 = new DList();
		System.out.println("###Test empty list and insertFront & insertBack to an empty list###");
		System.out.println("This is a list after construction" + list1.toString());
		TestHelper.verify(list1.toString().equals("[  ]"), "new list constructed failed!");
		System.out.println("isEmpty() should be true. It is: " + list1.isEmpty());
		TestHelper.verify(list1.isEmpty() == true, "isEmpty() on newly constructed list is failed!");
		System.out.println("length() should be 0. It is: " + list1.length());
		TestHelper.verify(list1.length() == 0, "length() on newly construced list is failed!");
		list1.insertFront(1);
		System.out.println("Here is a list after insertFront(1) to an empty list: " + list1.toString());
		TestHelper.verify(list1.toString().equals("[  1  ]"), "insertFront to an empty list is failed!");
		list2.insertBack(2);
		System.out.println("Here is a list after insertBack(2) to an empty list: " + list2.toString());
		TestHelper.verify(list2.toString().equals("[  2  ]"), "insertBack to an empty list is failed!");
		
		
	}
	
	protected static void testNodeReturn() {
		DList list1 = new DList();
		System.out.println("###Test retrieve node from empty list###");
		System.out.println("Front node should be null. It is: " + list1.front());
		TestHelper.verify(list1.front() == null, "front() on empty list is failed");
		System.out.println("back node should be null. It is: " + list1.back());
		TestHelper.verify(list1.back() == null, "back() on empty list is failed");
		System.out.println("###Test front and back of a list with one item###");
		list1.insertFront(1);
		System.out.println("Front node should be 1. It is: " + list1.front().item);
		TestHelper.verify(list1.front().item.equals(1), "front() on one item list is failed");
		System.out.println("Back node should be 1. It is: " + list1.back().item);
		TestHelper.verify(list1.back().item.equals(1), "back() on one item list is failed");
		System.out.println("###Test next node and previous node###");
		System.out.println("next node should be null. It is: " + list1.next(list1.back()));
		TestHelper.verify(list1.next(list1.back()) == null, "next node of a list with one item is failed");
		System.out.println("previous node should be null. It is: " + list1.prev(list1.front()));
		TestHelper.verify(list1.prev(list1.front()) == null, "next node of a list with one item is failed");
		list1.insertBack(2);
		System.out.println("next node should be not null. It is: " + list1.prev(list1.back()));
		TestHelper.verify(list1.prev(list1.back()) != null, "next node of a list with one item is failed");
		System.out.println("previous node should be not null. It is: " + list1.next(list1.front()));
		TestHelper.verify(list1.next(list1.front()) != null, "next node of a list with one item is failed");
		
	}
	
	protected static void testRemove() {
		DList list = new DList();
		System.out.println("###Test insertAfter, insertBefore and remove###");
		list.insertFront(1);
		list.insertAfter(3, list.front());
		System.out.println("Insert 3 after 1, it should be[  1  3  ]. It is: "  + list.toString());
		TestHelper.verify(list.toString().equals("[  1  3  ]"), "InsertAfter failed");
		list.insertBefore(2, list.back());
		System.out.println("Insert 2 before 3, it should be[  1  2  3  ]. It is: "  + list.toString());
		TestHelper.verify(list.toString().equals("[  1  2  3  ]"), "Insertefore failed");
		list.remove(list.back());
		System.out.println("Remove the last item of the list. The list should be [  1  2  ]. It is: " + list.toString());
		TestHelper.verify(list.toString().equals("[  1  2  ]"), "Remove the last item failed");
		list.remove(list.front());
		System.out.println("Remove the first item of the list. The list should be [   2  ]. It is: " + list.toString());
		TestHelper.verify(list.toString().equals("[  2  ]"), "Remove the first item failed");
	}
	
	
	protected static void testLockDList() {
		LockDList list = new LockDList();
		System.out.println("###Test empty lock list and insertFront & insertBack to an empty list###");
		System.out.println("This is a list after construction" + list.toString());
		TestHelper.verify(list.toString().equals("[  ]"), "new list constructed failed!");
		System.out.println("isEmpty() should be true. It is: " + list.isEmpty());
		TestHelper.verify(list.isEmpty() == true, "isEmpty() on newly constructed list is failed!");
		System.out.println("length() should be 0. It is: " + list.length());
		TestHelper.verify(list.length() == 0, "length() on newly construced list is failed!");
		list.insertFront(1);
		System.out.println("Here is a list after insertFront(1) to an empty list: " + list.toString());
		TestHelper.verify(list.toString().equals("[  1  ]"), "insertFront to an empty list is failed!");
		System.out.println("The lock status of the front node should be false. It is: " + list.getLockStatus(list.front()));
		TestHelper.verify(list.getLockStatus(list.front()) == false, "The default locked status is wrong");
		list.insertBack(3);
		System.out.println("Here is a list after insertBack(3): " + list.toString());
		TestHelper.verify(list.toString().equals("[  1  3  ]"), "insertBack to an empty list is failed!");
		list.lockNode(list.front());
		System.out.println("The lock status of the front node should be true. It is: " + list.getLockStatus(list.front()));
		TestHelper.verify(list.getLockStatus(list.front()) == true, "The locked status is wrong");
		list.insertBefore(2, list.back());
		System.out.println("Here is a list after insertBefore(2): " + list.toString());
		TestHelper.verify(list.toString().equals("[  1  2  3  ]"), "insertBefore failed");
		list.insertAfter(4, list.back());
		System.out.println("Here is a list after insertAfter(4): " + list.toString());
		TestHelper.verify(list.toString().equals("[  1  2  3  4  ]"), "insertAfter failed");
		list.remove(list.back());
		System.out.println("Here is a list after remove last item: " + list.toString());
		TestHelper.verify(list.toString().equals("[  1  2  3  ]"), "remove last item failed");
		list.remove(list.front());
		System.out.println("Here is a list after remove first item which is locked node: " + list.toString());
		TestHelper.verify(list.toString().equals("[  1  2  3  ]"), "remove locked item failed");
		
		
	}
	
}