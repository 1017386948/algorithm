package searching;

import java.util.ArrayList;
import java.util.Collection;

import com.algs4.stdlib.StdIn;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
	private Key[] keys;
	private Value[] values;
	private int N;

	@SuppressWarnings("unchecked")
	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		values = (Value[]) new Object[capacity];
	}

	public int size() {
		return N;
	}

	public Value get(Key key) {
		if (isEmpty())
			return null;
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0)
			return values[i];
		else
			return null;
	}

	public int rank(Key key) {
		return rank(key, 0, size() - 1);
	}

	public void put(Key key, Value value) {
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) {
			values[i] = value;
			return;
		}
		if (keys.length == N)
			resize(2 * keys.length);
		for (int j = N; j > i; j--) {
			keys[j] = keys[j - 1];
			values[j] = values[j - 1];
		}
		keys[i] = key;
		values[i] = value;
		N++;
	}

	public Key floor(Key key) {
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0)
			return keys[i - 1];
		if (i == 0)
			return null;
		else
			return keys[i - 1];
	}

	public Key ceiling(Key key) {
		int i = rank(key);
		return i == N ? null : keys[i];
	}

	public void delete(Key key) {
		if (isEmpty())
			return;
		int i = rank(key);
		if (i == N || keys[i].compareTo(key) != 0)
			return;
		for (int j = i; j < N - 1; j++) {
			keys[j] = keys[j + 1];
			values[j] = values[j + 1];
		}
		N--;
		keys[N] = null;
		values[N] = null;
		if (N > 0 && N == keys.length >>> 2)
			resize(keys.length >>> 1);
		assert check();
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public Key select(int k) {
		if (k < 0 || k >= N)
			return null;
		return keys[k];
	}

	public void deleteMin() {
		if (N == 0)
			throw new RuntimeException("Symbol table underflow error");
		delete(min());
	}

	public void deleteMax() {
		if (N == 0)
			throw new RuntimeException("Symbol table underflow error");
		delete(max());
	}

	public Key min() {
		if (isEmpty())
			return null;
		return keys[0];
	}

	public Key max() {
		if (isEmpty())
			return null;
		return keys[N - 1];
	}

	public Iterable<Key> keys() {
		return keys(min(), max());
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		Collection<Key> queue = new ArrayList<>();
		if (lo == null && hi == null)
			return queue;
		if (lo == null)
			throw new RuntimeException("lo is null in keys()");
		if (hi == null)
			throw new RuntimeException("hi is null in keys()");
		if (lo.compareTo(hi) > 0)
			return queue;
		for (int i = rank(lo); i < rank(hi); i++)
			queue.add(keys[i]);
		if (contains(hi))
			queue.add(keys[rank(hi)]);
		return queue;
	}

	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		assert capacity >= N;
		Key[] tempk = (Key[]) new Comparable[capacity];
		Value[] tempv = (Value[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			tempk[i] = keys[i];
			tempv[i] = values[i];
		}
		keys = tempk;
		values = tempv;
	}

	private int rank(Key key, int lo, int hi) {
		if (lo > hi)
			return lo;
		int mid = lo + (hi - lo) / 2;
		int cmp = key.compareTo(keys[mid]);
		if (cmp < 0)
			return rank(key, lo, mid - 1);
		else if (cmp > 0)
			return rank(key, mid + 1, hi);
		else
			return mid;
	}

	private boolean check() {
		return isSorted() && rankCheck();
	}

	// are the items in the array in ascending order?
	private boolean isSorted() {
		for (int i = 1; i < size(); i++)
			if (keys[i].compareTo(keys[i - 1]) < 0)
				return false;
		return true;
	}

	// check that rank(select(i)) = i
	private boolean rankCheck() {
		for (int i = 0; i < size(); i++)
			if (i != rank(select(i)))
				return false;
		for (int i = 0; i < size(); i++)
			if (keys[i].compareTo(select(rank(keys[i]))) != 0)
				return false;
		return true;
	}

	public static void main(String[] args) {
		BinarySearchST<String, Integer> bs = new BinarySearchST<>(16);
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			bs.put(key, i);
		}
		// bs.delete("zealous");
		// for (String s : bs.keys())
		// StdOut.println(s + " " + bs.get(s));
		System.out.println(bs.size());
		// System.out.println(bs.floor("youthman"));
		// System.out.println(bs.select(10678));
		// System.out.println(bs.rank("algorithm"));
		int i = 0;
		for (String key : bs.keys("noo", "yesb")) {
			i++;
			System.out.println(key);
		}
		System.out.println(i);
	}

}
