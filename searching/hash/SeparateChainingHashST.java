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
		this(997);
	}
	@SuppressWarnings("unchecked")
	public SeparateChainingHashST(int M) {
		this.M = M;
		table = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
	}
	private int hash(Key key) {
		int h;
		return key == null ? 0 : ((h = key.hashCode()) & (h >>> 16)) % M;
	}
	public Value get(Key key) {
		return table[hash(key)].get(key);
	}
	public void put(Key key, Value value) {
		int h = hash(key);
		if (table[h] == null)
			table[h] = new SequentialSearchST<Key, Value>();
		N = table[h].put(key, value) ? N + 1 : N;
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

	public static void main(String[] args) {
		SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
		Scanner scan = new Scanner(new BufferedInputStream(System.in), "UTF-8");
		for (int i = 0; scan.hasNext(); i++) {
			st.put(scan.next(), i);
		}
		scan.close();
		System.out.println(st.size());
	}
}
