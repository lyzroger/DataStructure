import java.io.*;

/**
 * Nuke2 whose main method reads a string from the keyboard and prints the same string, 
 * with its second character removed.  (That's character number 1, since Java numbers 
 * characters in a string starting from zero.)  In other words, after you run
 * "java Nuke2", if you type in the string "skin", the program will print "sin".
 * 
 */

class Nuke2 {
	public static void main(String[] args) throws Exception {
		BufferedReader keyboard;
		String inputLine;
		
		keyboard = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("Please input a string:");
			inputLine = keyboard.readLine();
			System.out.println(inputLine.substring(0,1) + inputLine.substring(2));
		}
		
	}
	
}