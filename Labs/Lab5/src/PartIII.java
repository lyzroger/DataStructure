public class PartIII {
	
	public static void main(String[] args) {
		subclassiii sub = new subclassiii();
		System.out.println(sub.testFunc(3, 4));
	}
}

interface partiii {
	public static final int NUMBER = 99;
	//public int testFunc(int c, int d);
}

class superclassiii {
	public static final int NUMBER = 9;
	public int testFunc(int a, int b) {
		int test = a + b;
		return test;
	}
}

class subclassiii extends superclassiii implements partiii {
	public int testFunc(int a, int b) {
		int test = a + b + superclassiii.NUMBER;
		return test;
	}
}