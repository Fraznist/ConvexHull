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
	// current element is then set to the newly added element
	@Override
	public boolean add(E e) {
		CircularNode<E> newNode = new CircularNode<E>(e);
		
		CircularNode<E> cacheCurrent = this.current;

		this.current = this.first;
		
		this.insert(newNode);
		
		if (cacheCurrent != null)
			this.current = cacheCurrent;
		
		return true;
	}

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size()) 
			throw new IndexOutOfBoundsException();
		
		CircularNode<E> cacheCurrent = this.current;

		this.current = this.first;
		
		for (int i = 0; i < index; i++) 
			current = current.getNext();
		
		CircularNode<E> newNode = new CircularNode<E>(element);

		this.insert(newNode);
		
		if (cacheCurrent != null)
			this.current = cacheCurrent;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		CircularNode<E> cacheCurrent = this.current;

		this.current = this.first;
		
		for (E elem : c) {
			CircularNode<E> newNode = new CircularNode<E>(elem);
			this.insert(newNode);
		}
		
		if (cacheCurrent != null)
			this.current = cacheCurrent;
		
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		if (index < 0 || index > size()) 
			throw new IndexOutOfBoundsException();
		
		CircularNode<E> cacheCurrent = this.current;

		this.current = this.first;
		
		for (int i = 0; i < index; i++) 
			current = current.getNext();
		
		for (E elem : c) {
			CircularNode<E> newNode = new CircularNode<E>(elem);
			this.insert(newNode);
		}
		
		if (cacheCurrent != null)
			this.current = cacheCurrent;
		
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
		if (this.size == 0) 
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
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
	
	// inserts newNode between current.getPrev() and current node
	// current node stays the same unless the list was empty,
	// in which case the null value is set to the newly inserted node
	private void insert(CircularNode<E> newNode) {
		if (this.isEmpty()) 
			this.current = newNode;
		
		CircularNode<E> nextNode = this.current.getPrev();
		
		this.current.appendToPrev(newNode);
		nextNode.appendToNext(newNode);

		this.size++;
	}

	public CircularNode<E> getCurrent() {
		return current;
	}

	public void setCurrent(CircularNode<E> current) {
		this.current = current;
	}

	public CircularNode<E> getFirst() {
		return first;
	}

	public void setFirst(CircularNode<E> first) {
		this.first = first;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
