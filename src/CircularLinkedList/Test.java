package CircularLinkedList;

import java.util.LinkedList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CircularList<Integer> circle = new CircularList<Integer>();
		
		for (int i = 0; i < 10; i++) {
			circle.add(i);
		}
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(2);list.add(3);list.add(4);
		
		for (Integer i : circle) {
			System.out.println(i);
		}
	}

}
