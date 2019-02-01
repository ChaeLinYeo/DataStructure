package hw2;

public class DLinkedList<T> {

    private Node<T> header;
    private Node<T> trailer;
    private int size = 0;

    public DLinkedList() {
	    	header = new Node<>(null, null, null);
	    	trailer = new Node<>(null, header,null);
	    	header.setNext(trailer);
    }

    public void setHeaderInfo(T info) {
        header.setItem(info);
    }

    public void setTrailerInfo(T info) {
        trailer.setItem(info);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() { return size; }

    public Node<T> getFirst() {
        return header.getNext();
    }

    public Node<T> getLast() {
        return trailer.getPrev();
    }

    public void addFirst(Node<T> n) {
       addBetween(n.getItem(), header, header.getNext());
    }

    public void addLast(Node<T> n) {
        addBetween(n.getItem(), trailer.getPrev(), trailer);
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        return remove(header.getNext());
    }

    public T removeLast() {
        if (isEmpty()) return null;
        return remove(trailer.getPrev());
    }

    public void addAfter(Node<T> p, Node<T> n) {
       n.setNext(p.getNext());
       n.setPrev(p);
       p.getNext().setPrev(n);
       p.setNext(n);
       size++;
    }

    public void addBefore(Node<T> p, Node<T> n) {
        n.setNext(p);
        n.setPrev(p.getPrev());
        p.getPrev().setNext(n);
        p.setPrev(n);
        size++;
    }

    //추가한 메소드!! 
    private void addBetween(T e, Node<T> predecessor, Node<T> successor) {
    		Node<T> newest = new Node<>(e, predecessor, successor);
    		predecessor.setNext(newest);
    		successor.setPrev(newest);
    		size++;
    }
    
    public T remove(Node<T> n) {
        Node<T> predecessor = n.getPrev();
        Node<T> successor = n.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return n.getItem();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(
            "List: size = " + size + " [");
        Node<T> current = header.getNext();

        while (current != trailer) {
            builder.append(current.getItem().toString());
            current = current.getNext();
        }
        builder.append("]");

        return builder.toString();
    }
}
