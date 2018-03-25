package CircularLinkedList;

import java.util.ListIterator;

public class CircularListIterator<E> implements ListIterator<E> {

	private CircularNode<E> start;
	private CircularList<E> circ;
	boolean iteratedStart = false;
	
	public CircularListIterator (CircularList<E> circle) {
		start = circle.getFirst();
		circ = circle;
	}
	
	@Override
	public void add(E e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean hasNext() {
		if (iteratedStart == true)
			return false;
		else
			return true;
	}
	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public E next() {
		CircularNode<E> curr = circ.getCurrent().getNext();
		circ.setCurrent(curr);
		E ret = curr.getElement();
		if (curr.equals(start))
			iteratedStart = true;		
		return ret;
	}
	@Override
	public int nextIndex() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public E previous() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int previousIndex() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void set(E e) {
		// TODO Auto-generated method stub
		
	}

}
