package CircularLinkedList;

public class CircularNode<E> {
	
	private E element;
	private CircularNode<E> next;
	private CircularNode<E> prev;
	
	public CircularNode (E data) {
		element = data;
		next = this;
		prev = this;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public CircularNode<E> getNext() {
		return next;
	}

	public void setNext(CircularNode<E> next) {
		this.next = next;
	}

	public CircularNode<E> getPrev() {
		return prev;
	}

	public void setPrev(CircularNode<E> prev) {
		this.prev = prev;
	}
	
	public void appendToNext(CircularNode<E> newNode) {
		this.next = newNode;
		newNode.prev = this;
	}
	
	public void appendToPrev(CircularNode<E> appendant) {
		this.prev = appendant;
		appendant.next = this;
	}
	
	public String toString() {
		return "CNode: " + element.toString();
	}
}
