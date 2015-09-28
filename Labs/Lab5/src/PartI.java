
public class PartI{
	public static void main(String args[]) {
		Parent x1 = new Parent();
		Child y1 = new Child();
		x1 = y1;
		
		Parent x2 = new Parent();
		Child y2 = new Child();
		//y2 = (Child) x2;
				
		Parent[] xa1 = new Parent[3];
		Child[] ya1 = new Child[3];
		xa1 = ya1;
		ya1 = (Child[])xa1;
		
		Parent[] xa = new Parent[3];
		Child[] ya = new Child[3];
		//ya = (Child[])xa;
		xa = ya;
		
		Parent[] xa2 = new Child[3];
		Child[] ya2 = new Child[3];
		ya2 = (Child[])xa2;
		xa2 = ya2;
	}
	
	
}

class Parent{
	
}

class Child extends Parent{
	
}