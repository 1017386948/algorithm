package sorting;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import com.algs4.stdlib.StdOut;

public class MaxPQ<Key> implements Iterable<Key> {
	private Key[] pq;
	private int N;
	private Comparator<Key> comparator;

	public MaxPQ() {
		this(1);
	}

	@SuppressWarnings("unchecked")
	public MaxPQ(int capacity) {
		this.pq = (Key[]) new Object[capacity + 1];
		this.N = 0;
	}

	public MaxPQ(int capacity, Comparator<Key> comparator) {
		this(capacity);
		this.comparator = comparator;
	}

	public MaxPQ(Comparator<Key> comparator) {
		this.comparator = comparator;
	}

	public MaxPQ(Key[] keys) {
		resize(keys.length + 1);
		N = keys.length;
		for (int i = 0; i < N; i++)
			pq[i + 1] = keys[i];
		for (int i = N / 2; i > 0; i--)
			sink(i);
		assert (isMaxHeap());
	}

	public void insert(Key v) {
		if (N >= pq.length - 1)
			resize(pq.length * 2);
		pq[++N] = v;
		swim(N);
	}

	public Key delMax() {
		if (isEmpty())
			throw new RuntimeException("Priority queue underflow");
		Key max = pq[1];
		exch(1, N);
		pq[N--] = null;
		sink(1);
		if (N > 0 && N == (pq.length - 1) / 4)
			resize(pq.length / 2);
		return max;
	}

	public Key max() {
		return pq[1];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public Key get(int i) {
		if (0 < i && i <= N)
			return pq[i];
		else
			throw new ArrayIndexOutOfBoundsException(i);
	}

	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		assert (capacity > N);
		Key[] newPQ = (Key[]) new Object[capacity];
		for (int i = 1; i <= N; i++)
			newPQ[i] = pq[i];
		this.pq = newPQ;
	}

	private void swim(int i) {
		while (i > 1 && less(i / 2, i)) {
			exch(i / 2, i);
			i /= 2;
		}
	}

	private void sink(int i) {
		while (i <= N / 2) {
			int bigger = 2 * i + 1 > N ? 2 * i : //
					less(2 * i, 2 * i + 1) ? 2 * i + 1 : 2 * i;
			if (less(i, bigger))
				exch(i, bigger);
			i = bigger;
		}
	}

	private boolean isMaxHeap() {
		return isMaxHeap(1);
	}

	private boolean isMaxHeap(int i) {
		if (i > N / 2)
			return true;
		if (less(i, 2 * i) || (2 * i + 1 <= N && less(i, 2 * i + 1)))
			return false;
		return isMaxHeap(2 * i) && isMaxHeap(2 * i + 1);
	}

	@SuppressWarnings("unchecked")
	private boolean less(int i, int j) {
		if (comparator == null)
			return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
		else
			return comparator.compare(pq[i], pq[j]) < 0;
	}

	private void exch(int i, int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}

	@Override
	public Iterator<Key> iterator() {
		return new HeapIterator();
	}

	private class HeapIterator implements Iterator<Key> {

		private MaxPQ<Key> copy;

		public HeapIterator() {
			if (comparator == null) {
				copy = new MaxPQ<>(size());
			} else
				copy = new MaxPQ<Key>(size(), comparator);
			for (int i = 1; i < size() + 1; i++) {
				copy.insert(pq[i]);
			}
		}

		@Override
		public boolean hasNext() {
			return !copy.isEmpty();
		}

		@Override
		public Key next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return copy.delMax();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public static void main(String[] args) {
		MaxPQ<String> pq = new MaxPQ<String>(
				"A B C D E F G H I J K L M N O P Q R".split(" "));
		Random rand = new Random(System.currentTimeMillis());
		String[] ins = ("A B C D E F G H I J K L M N O P Q R"
				+ " S T U V W X Y Z "//
				+ "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -")
						.split(" ");
		int n = 200;
		while (n-- > 0) {
			String item = ins[rand.nextInt(ins.length)];
			if (!item.equals("-")) {
				pq.insert(item);
			} else if (!pq.isEmpty()) {
				for (String k : pq)
					System.out.print(k + " ");
				StdOut.println("->" + pq.delMax());
			}
		}
		StdOut.println("(" + pq.size() + " left on pq)");
		System.out.println(pq.isMaxHeap());
	}

}
