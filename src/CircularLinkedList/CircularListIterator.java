package CircularLinkedList;

import java.util.ListIterator;

public class CircularListIterator<E> implements ListIterator<E> {

	// next and prev together represent the "cursor position" of the ListIterator
	private CircularList<E> circ;
	private CircularNode<E> next; private CircularNode<E> OGnext;
	private CircularNode<E> prev; private CircularNode<E> OGprev;
	
	boolean iteratedFirst;
	boolean iteratedLast;
	boolean lastCallNextOrPrev;
	
	int nextIndex;
	
	private CircularNode<E> lastReturned;
	
	public CircularListIterator (CircularList<E> circle) {
		this(0, circle);
	}
	
	public CircularListIterator (int index, CircularList<E> circle) {
		if (index < 0 || index > circle.size()) 
			throw new IndexOutOfBoundsException();
		
		CircularNode<E> target = circle.getFirst();
		
		for (int i = 0; i < index; i++) 
			target = target.getNext();
		
		circ = circle;
		next = target;
		prev = next.getPrev();
		OGnext = next;
		OGprev = prev;
		iteratedFirst = false;
		iteratedLast = false;
		lastCallNextOrPrev = false;
		nextIndex = index;
	}
	
	@Override
	public void add(E e) {
		if (!lastCallNextOrPrev)
			throw new IllegalStateException();
		
		lastCallNextOrPrev = false;
		
		CircularNode<E> newNode = new CircularNode<E>(e);
		circ.insert(next, newNode);
		prev = newNode;
	}
	
	@Override
	public boolean hasNext() {
		if (next == OGnext && iteratedFirst)
			return false;
		else 
			return true;
	}
	
	@Override
	public boolean hasPrevious() {
		if (prev == OGprev && iteratedLast)
			return false;
		else 
			return true;
	}
	
	@Override
	public E next() {
		if (next == circ.getFirst())
			iteratedFirst = true;
		
		lastReturned = next;
		
		next = next.getNext();
		prev = next.getPrev();
		
		nextIndex++;
		nextIndex = nextIndex % circ.size();
		
		lastCallNextOrPrev = true;
		
		return lastReturned.getElement();
	}
	
	@Override
	public int nextIndex() {
		return nextIndex;
	}
	
	@Override
	public E previous() {
		if (prev == circ.getFirst())
			iteratedLast = true;
		
		lastReturned = prev;
		
		prev = prev.getPrev();
		next = prev.getNext();
		
		nextIndex--;
		nextIndex = nextIndex % circ.size();
		
		lastCallNextOrPrev = true;
		
		return lastReturned.getElement();
	}
	@Override
	public int previousIndex() {
		return (nextIndex - 1) % circ.size();
	}
	
	@Override
	public void remove() {
		if (!lastCallNextOrPrev)
			throw new IllegalStateException();
		
		lastCallNextOrPrev = false;
		
		circ.delete(lastReturned);
		
		if (prev == lastReturned) prev = next.getPrev();
		else next = prev.getNext();
	}
	
	@Override
	public void set(E e) {
		if (lastCallNextOrPrev)
			lastReturned.setElement(e);
		else
			throw new IllegalStateException();
	}

}
