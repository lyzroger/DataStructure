public class PartIV {
	
	public static void main(String[] args) {
		subclassiv sub = new subclassiv();
		//superclassiv sup = new superclassiv();
		System.out.println(((superclassiv)sub).testFunc(3, 4));
		System.out.println(sub.testFuncSuper(3, 4));
		//System.out.println(((subclassiv)sup).testFunc(3, 4));
	}
}


class superclassiv {
	public static final int NUMBER = 9;
	public int testFunc(int a, int b) {
		int test = a + b;
		return test;
	}
}

class subclassiv extends superclassiv{
	public int testFunc(int a, int b) {
		int test = a * b ;
		return test;
	}
	public int testFuncSuper(int a, int b) {
		return super.testFunc(a, b);
	}
}