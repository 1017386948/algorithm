package searching;

import java.util.ArrayList;
import java.util.Collection;

import com.algs4.stdlib.StdIn;
import com.algs4.stdlib.StdOut;

public class SequentialSearchST<Key, Value> {
	private int N;
	private Node first;
	private class Node {
		Key key;
		Value value;
		Node next;
		public Node(Key key, Value value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	public Value get(Key key) {
		for (Node x = first; x != null; x = x.next)
			if (x.key.equals(key))
				return x.value;
		return null;
	}

	public void put(Key key, Value value) {
		for (Node x = first; x != null; x = x.next)
			if (x.key.equals(key)) {
				x.value = value;
				return;
			}
		first = new Node(key, value, first);
		N++;
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	public void delete(Key key) {
		first = delete(first, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null)
			return null;
		if (x.key.equals(key)) {
			N--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}

	public Iterable<Key> keys() {
		Collection<Key> queue = new ArrayList<Key>();
		for (Node x = first; x != null; x = x.next)
			queue.add(x.key);
		return queue;
	}

	public static void main(String[] args) {
		SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();

			st.put(key, i);
		}
		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));
	}
}
