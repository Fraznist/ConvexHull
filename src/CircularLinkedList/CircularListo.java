package CircularLinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CircularListo<E> implements List<E> {

	@Override
	public boolean remove(Object arg0) {
		for (E elem : this) {
			if (elem.equals(arg0)) {
				deleteCurrent();
				return true;
			}
		}		
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		boolean hasChanged = false;
		
		for (Object del : arg0) {
			if (remove(del))
				hasChanged = true;
		}
		
		return hasChanged;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E[] toArray(E[] arg0) {
		int size = this.size();
		E[] arr;
		
		if (arg0.length < size)
			arr = new E[size];	
	}

	private void deleteCurrent() {
		if (this.size == 1) 
			this.current = null;
		else {
			CircularNode<E> prev = this.current.getPrev();
			CircularNode<E>	next = this.current.getNext();
			
			this.current = prev;
			
			prev.appendToNext(next);
			next.appendToPrev(prev);
		}
		
		this.size--;
		
	}

}
