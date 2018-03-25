package CircularLinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CircularList<E> implements List<E>{

	private CircularNode<E> current;
	private CircularNode<E> first;
	private int size;
	private boolean dirtySize;
	
	public CircularList() {
		current = null;
		first = null;
		size = 0;
		dirtySize = false;
	}
	
	public CircularList(Collection<? extends E> arg0) {
		for (E elem : arg0) {
			this.add(elem);
			
		}
	}

	// appends an element to the end of the list
	@Override
	public boolean add(E e) {
		CircularNode<E> newNode = new CircularNode<E>(e);

		this.insert(getFirst(), newNode);

		return true;
	}

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size()) 
			throw new IndexOutOfBoundsException();

		CircularNode<E> target = this.getFirst();
		
		for (int i = 0; i < index; i++) 
			target = target.getNext();
		
		CircularNode<E> newNode = new CircularNode<E>(element);

		this.insert(target, newNode);

	}

	@Override
	public boolean addAll(Collection<? extends E> c) {

		for (E elem : c) {
			CircularNode<E> newNode = new CircularNode<E>(elem);
			this.insert(getFirst(), newNode);
		}

		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		if (index < 0 || index > size()) 
			throw new IndexOutOfBoundsException();
		
		CircularNode<E> target = this.getFirst();
		
		for (int i = 0; i < index; i++) 
			target = target.getNext();
		
		for (E elem : c) {
			CircularNode<E> newNode = new CircularNode<E>(elem);
			this.insert(target, newNode);
		}
		
		return true;
	}

	@Override
	public void clear() {
		this.current = null;
		this.first = null;
		this.size = 0;
		this.dirtySize = false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		if (this.size() == 0) 
			return true;
		else
			return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new CircularListIterator<E>(this); 
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		return new CircularListIterator<E>(this); 
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		int size = this.getSize();
		CircularNode<E> target = this.getFirst();
		for (int i = 0; i < size; i++) {
			if (target.getElement().equals(o)) {
				delete(target);
				return true;
			}
			target = target.getNext();
		}
		return false;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index > size()) 
			throw new IndexOutOfBoundsException();
		
		CircularNode<E> target = this.getFirst();
		for (int i = 0; i < index; i++) 
			target = target.getNext();
		
		return delete(target);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		int size = this.getSize();
		boolean changed = false;
		CircularNode<E> target = this.getFirst();
		for (int i = 0; i < size; i++) {
			for (Object o : c) {
				if (target.getElement().equals(o)) {
					delete(target);
					changed = true;
				}
			}
			target = target.getNext();
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		if (dirtySize) {
			int c = 0;
			for (E elem : this) 
				c++;
			this.size = c;
			this.dirtySize = false;
		}
		return this.size;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// inserts newNode between toPrev.getPrev() and toPrev node
	// current node stays the same unless the list was empty,
	// in which case the null value is set to the newly inserted node
	protected void insert(CircularNode<E> toPrev, CircularNode<E> newNode) {
		if (this.isEmpty()) {
			this.current = newNode;
			this.first = newNode;
			toPrev = newNode;
		}
		
		CircularNode<E> prevNode = toPrev.getPrev();
		
		toPrev.appendToPrev(newNode);
		prevNode.appendToNext(newNode);

		this.size++;
	}
	
	protected E delete(CircularNode<E> toDelete) {
		if (this.getSize() == 1) {
			this.current = null;
			this.first = null;
		}
		else {
			CircularNode<E> prev = toDelete.getPrev();
			CircularNode<E>	next = toDelete.getNext();
			
			this.current = prev;
			
			prev.appendToNext(next);
			next.appendToPrev(prev);
		}
		
		this.size--;
		
		return toDelete.getElement();
	}

	protected CircularNode<E> getCurrent() {
		return current;
	}

	protected void setCurrent(CircularNode<E> current) {
		this.current = current;
	}

	protected CircularNode<E> getFirst() {
		return first;
	}

	protected void setFirst(CircularNode<E> first) {
		this.first = first;
	}
	
	protected CircularNode<E> getLast() {
		if (isEmpty()) return null;
		else return this.getLast();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public String toString() {
		String sum = "[";
		
		for (E elem : this)
			sum += elem + ", ";
		
		sum = sum.substring(0, sum.length() - 2);
		
		return sum + "]";
	}
}
