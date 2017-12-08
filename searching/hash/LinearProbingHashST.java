package hash;

import java.io.BufferedInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LinearProbingHashST<Key, Value> {
	private int N;
	private int M;
	private Key[] keys;
	private Value[] values;

	public LinearProbingHashST() {
		this(16);
	}

	@SuppressWarnings("unchecked")
	public LinearProbingHashST(int M) {
		this.M = tableSizeFor(M);
		keys = (Key[]) new Object[M];
		values = (Value[]) new Object[M];
	}

	private int hash(Key key) {
		int h;
		return (h = key.hashCode() & 0x7FFFFFFF) ^ (h >>> 16);
	}

	public void put(Key key, Value value) {
		int h = hash(key);
		if (N > M >> 1)
			resize(M << 1);
		int i;
		for (i = h & M - 1; keys[i] != null; i = (i + 1) & (M - 1)) {
			if (keys[i].equals(key)) {
				values[i] = value;
				return;
			}
		}
		keys[i] = key;
		values[i] = value;
		N++;
	}

	public Value get(Key key) {
		int h = hash(key) & M - 1;
		for (int i = h; keys[i] != null; i++)
			if (keys[i].equals(key))
				return values[i];
		return null;
	}

	public void delete(Key key) {
		int h = hash(key) & M - 1;
		for (int i = h; keys[i] != null; i = i + 1 & M - 1) {
			if (keys[i].equals(key)) {
				keys[i] = null;
				values[i] = null;
				N--;
			} else {
				Key keyToRedo = keys[i];
				Value valueToRedo = values[i];
				keys[i] = null;
				values[i] = null;
				N--;
				put(keyToRedo, valueToRedo);
			}
		}
		if (N < M >> 3)
			resize(M >> 1);
	}

	public int size() {
		return N;
	}

	public Iterable<Key> keys() {
		Collection<Key> queue = new ArrayList<>();
		for (Key k : keys)
			if (k != null)
				queue.add(k);
		return queue;
	}

	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		Key[] ks = (Key[]) new Object[capacity];
		Value[] vs = (Value[]) new Object[capacity];
		for (int i = 0; i < M; i++) {
			Key key = keys[i];
			if (key == null)
				continue;
			int h = hash(key) & capacity - 1;
			int j = h;
			for (; ks[j] != null; j = j + 1 & capacity - 1)
				;
			ks[j] = keys[i];
			vs[j] = values[i];
		}
		M = capacity;
		keys = ks;
		values = vs;
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
		LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
		Scanner scan = new Scanner(new BufferedInputStream(System.in), "UTF-8");
		Map<Integer, String> map = new HashMap<>();
		for (int i = 0; scan.hasNext(); i++) {
			st.put(scan.next(), i);
		}
		scan.close();
		int i = 0;
		for (String key : st.keys()) {
			map.put(i++, key);
		}
		System.out.println(st.size());
		for (int k = 0; k < 1000; k++) {
			st.delete(map.get(k));
			map.remove(k);
		}
		System.out.println(st.size());
		/*
		 * 判断是否存在取不到的情况
		 */
		for (String value : map.values()) {
			if (st.get(value) == null)
				System.err.println("false");
		}
		System.out.println(true);

		try {
			Field field = st.getClass().getDeclaredField("keys");
			field.setAccessible(true);
			Object[] keys = (Object[]) field.get(st);
			int n = 0;
			for (Object o : keys) {
				if (o != null)
					n++;
			}
			System.out.println(n);

			Field field2 = HashMap.class.getDeclaredField("table");
			field2.setAccessible(true);
			Object[] table = (Object[]) field2.get(map);
			System.err.println(table.length);
			System.out.println(tableSizeFor(table.length));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
