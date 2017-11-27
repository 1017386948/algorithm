package sorting;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
	private int N;
	private int[] pq; // through item seek its index
	private int[] qp;
	private Key[] keys;

	@SuppressWarnings("unchecked")
	public IndexMinPQ(int maxN) {
		pq = new int[maxN + 1];
		qp = new int[maxN + 1];
		keys = (Key[]) new Comparable[maxN + 1];
		Arrays.fill(qp, -1);
	}

	void insert(int k, Key key) {
		if (contains(k))
			throw new RuntimeException("item is already in pq");
		keys[++N] = key;
		pq[N] = k;
		qp[k] = N;
		swim(N);
	}

	void change(int k, Key key) {
		if (!contains(k))
			throw new RuntimeException("item is not in pq");
		int pos = qp[k];
		if (less(key, keys[pos])) {
			keys[pos] = key;
			swim(k);
		} else {
			keys[pos] = key;
			sink(k);
		}
	}

	boolean contains(int k) {
		return qp[k] != -1;
	}

	void delete(int k) {
		contains(k);
		int pos = qp[k];
		exch(pos, N);
		keys[N] = null;
		pq[N--] = -1;
		swim(N);
		qp[k] = -1;
	}

	Key min() {
		return keys[1];
	}

	int minIndex() {
		return pq[1];
	}

	int delMin() {
		int index = pq[1];
		delete(1);
		return index;
	}

	boolean isEmpty() {
		return N == 0;
	}

	int size() {
		return N;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new HeapIterator();
	}

	private void swim(int i) {
		while (i > 1) {
			if (less(keys[i], keys[i / 2])) {
				exch(i / 2, i);
				i /= 2;
			}
		}
	}

	private void sink(int i) {
		while (i <= N / 2) {
			if ((2 * i + 1 > N || less(keys[2 * i], keys[2 * i + 1]))//
					&& less(keys[2 * i], keys[i])) {
				exch(i, 2 * i);
				i *= 2;
			} else if (2 * i + 1 <= N && less(keys[2 * i + 1], keys[i])) {
				exch(i, 2 * i + 1);
				i = 2 * i + 1;
			}
		}
	}

	private void exch(int i, int j) {
		int temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;

	}

	private boolean less(Key k1, Key k2) {
		return ((Comparable<Key>) k1).compareTo(k2) < 0;
	}

	private class HeapIterator implements Iterator<Integer> {

		private IndexMinPQ<Key> copy;

		public HeapIterator() {
			copy = new IndexMinPQ<Key>(qp.length - 1);
			for (int i = 1; i <= N + 1; i++) {
				copy.insert(qp[i], keys[qp[i]]);
			}
		}

		@Override
		public boolean hasNext() {
			return !copy.isEmpty();
		}

		@Override
		public Integer next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return copy.delMin();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public static void main(String[] args) {

		// insert a bunch of strings
		String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

		IndexMinPQ<String> pq = new IndexMinPQ<String>(strings.length);
		for (int i = 0; i < strings.length; i++) {
			pq.insert(i, strings[i]);
		}

		// delete and print each key
		while (!pq.isEmpty()) {
			int i = pq.delMin();
			System.out.println(i + " " + strings[i]);
		}
		System.out.println();

		// reinsert the same strings
		for (int i = 0; i < strings.length; i++) {
			pq.insert(i, strings[i]);
		}

		// print each key using the iterator
		for (int i : pq) {
			System.out.println(i + " " + strings[i]);
		}
		while (!pq.isEmpty()) {
			pq.delMin();
		}

	}

}
