package week4;
	   
public class My_class {
//This file is only to show how to use multiple classes in a single file
	public static void main(String args[]) {
	   // Instantiating the outer class 
	   Outer_Demo outer = new Outer_Demo();
	      
	   // Accessing the display_Inner() method.
	   outer.display_Inner();
	}
}

class Outer_Demo {
	int num;	   
	// Accessing he inner class from the method within
	void display_Inner() {
	   Inner_Demo inner = new Inner_Demo();
	   inner.print();
	}
	
	// inner class
	public class Inner_Demo {
	   public void print() {
	      System.out.println("This is an inner class");
	   }
	}
}
