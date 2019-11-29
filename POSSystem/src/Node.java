public class Node<T> {
	private Node<T> next;
	private Node<T> prev;
	private T element;


	public Node(T element) {
		next=null;
		prev=null;
		this.element=element;
	}

	public Node<T> getNext(){
		return next;
	}
	public Node<T> getPrev(){
		return prev;
	}

	public void setNext(Node<T> node) {
		next = node;
	}
	public void setPrev(Node<T> node) {
		prev = node;
	}

	public T getElem() {
		return element;
	}

	public void setElem(T element) {
		this.element = element;
	}
}
