package hash;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import searching.SequentialSearchST;

public class SeparateChainingHashST<Key, Value> {
	private int N;
	private int M;
	private SequentialSearchST<Key, Value>[] table;
	public SeparateChainingHashST() {
		this(16);
	}

	@SuppressWarnings("unchecked")
	public SeparateChainingHashST(int M) {
		this.M = tableSizeFor(M);
		table = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
	}
	private int hash(Key key) {
		int h;
		return (h = key.hashCode() & 0x7FFFFFFF) ^ (h >>> 16);
	}
	public Value get(Key key) {
		int h = hash(key) & M - 1;
		return table[h].get(key);
	}
	public void put(Key key, Value value) {
		int h = hash(key) & M - 1;
		if (table[h] == null)
			table[h] = new SequentialSearchST<Key, Value>();
		if (!table[h].contains(key))
			N++;
		table[h].put(key, value);
		if (N > table.length << 3)
			resize(M << 1);
	}
	public void delete(Key key) {
		int h = hash(key) & M - 1;
		if (table[h] == null || !table[h].contains(key))
			return;
		table[h].delete(key);
		N--;
		if (N < M << 2)
			resize(M >> 1);
	}
	public Iterable<Key> keys() {
		Collection<Key> queue = new ArrayList<>();
		for (SequentialSearchST<Key, Value> st : table)
			if (st != null) {
				for (Key key : st.keys())
					queue.add(key);
			}
		return queue;
	}
	public int size() {
		return N;
	}
	private void resize(int capacity) {
		capacity = tableSizeFor(capacity);
		SeparateChainingHashST<Key, Value> newST = //
				new SeparateChainingHashST<>(capacity);
		for (SequentialSearchST<Key, Value> st : table) {
			if (st != null) {
				for (Key key : st.keys())
					newST.put(key, this.get(key));
			}
		}
		this.M = newST.M;
		this.N = newST.N;
		this.table = newST.table;
	}

	private static int tableSizeFor(int cap) {
		int n = cap - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return n + 1;
	}

	public static void main(String[] args) {
		SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
		Scanner scan = new Scanner(new BufferedInputStream(System.in), "UTF-8");
		for (int i = 0; scan.hasNext(); i++) {
			st.put(scan.next(), i);
		}
		scan.close();
		System.out.println(st.size());
		for (SequentialSearchST<String, Integer> s : st.table)
			System.err.println(s == null ? 0 : s.size());
	}
}
