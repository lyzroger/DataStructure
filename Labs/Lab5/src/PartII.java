public class PartII {
	
	public static void main(String[] args) {
		subclass sub = new subclass();
		System.out.println(sub.testFunc(3, 4));
	}
}

interface partii {
	public int testFunc(int c, int d);
}

class superclass {
	public int testFunc(int a, int b) {
		int test = a + b;
		return test;
	}
}

class subclass extends superclass implements partii {
	
}