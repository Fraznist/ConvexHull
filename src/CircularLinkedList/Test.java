package CircularLinkedList;

import java.util.LinkedList;
import java.util.ListIterator;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(2);list.add(3);list.add(4);
		
		CircularList<String> circle;
		circle = new CircularList<String>();
		
		for (int i = 0; i < 15; i++)
			circle.add(String.valueOf(i));
		
		circle.remove("3");
		
		System.out.println(circle);
	}

}
