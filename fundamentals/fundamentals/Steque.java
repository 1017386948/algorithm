package fundamentals;

import java.util.Iterator;

public class Steque<E> implements Iterable<E> {
	private Node<E> head;

	private static class Node<E> {
		E element;
		Node<E> next;

		Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
	}

	public void push(E e) {
		if (head == null)
			head = new Node<E>(e, null);
		else {
			Node<E> begin = new Node<E>(e, head);
			head = begin;
		}
	}

	public E pop() {
		if (head == null)
			return null;
		E element = head.element;
		head = head.next;
		return element;
	}

	public void enqueue(E e) {
		if (head == null)
			head = new Node<E>(e, null);
		else {
			Node<E> end = head;
			while (end.next != null)
				end = end.next;
			end.next = new Node<E>(e, null);
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			Node<E> nextIndex = head;

			@Override
			public boolean hasNext() {
				return nextIndex != null;
			}

			@Override
			public E next() {
				E element = nextIndex.element;
				nextIndex = nextIndex.next;
				return element;
			}

		};
	}

	public static void main(String[] args) {
		Steque<Integer> steque = new Steque<>();
		int i = 0;
		while (++i < 5) {
			steque.push(i);
			steque.enqueue(i);
		}
		// while (i-- > 0)
		// steque.pop();
		for (Integer e : steque) {
			System.out.print(e + " ");
		}
	}

}
