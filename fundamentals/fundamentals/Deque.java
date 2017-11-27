package fundamentals;

import java.util.NoSuchElementException;

public class Deque<E> {
	private int size;
	private Node<E> first;
	private Node<E> last;

	private static class Node<E> {
		E element;
		Node<E> next;
		Node<E> pre;

		Node(Node<E> pre, E e, Node<E> next) {
			this.pre = pre;
			this.element = e;
			this.next = next;
		}
	}

	public void pushLeft(E e) {
		final Node<E> f = first;
		final Node<E> newNode = new Node<E>(null, e, first);
		first = newNode;
		if (last == null)
			last = first;
		else
			f.pre = first;
		size++;
	}

	public void pushRight(E e) {
		final Node<E> l = last;
		final Node<E> newNode = new Node<E>(last, e, null);
		last = newNode;
		if (first == null)
			first = last;
		else
			l.next = last;
		size++;
	}

	public int size() {
		return size;
	}

	public E popLeft() {
		if (size < 1)
			throw new NoSuchElementException();
		final Node<E> f = first;
		first = first.next;
		f.pre = f.next = null;
		if (first == null)
			last = null;
		else
			first.pre = null;
		size--;
		return f.element;
	}

	public E popRight() {
		if (size < 1)
			throw new NoSuchElementException();
		final Node<E> l = last;
		last = last.pre;
		l.pre = l.next = null;
		if (last == null)
			first = null;
		else
			last.next = null;
		size--;
		return l.element;
	}

	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<>();
		for (int i = 0; i < 5; i++) {
			deque.pushLeft(i);
			deque.pushRight(i);
		}
		while (deque.size() > 0)
			System.out.print(deque.popLeft() + " ");

	}

}
