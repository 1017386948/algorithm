package exercise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.algs4.stdlib.StdIn;

import searching.BST;

public class PerfectBalance<Key extends Comparable<Key>, Value> {
	private Node root;

	private class Node {
		Key key;
		Value value;
		Node left;
		Node right;

		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
		}
	}

	public PerfectBalance(Key[] keys, Value[] values) {
		assert keys.length == values.length;
		// Arrays.sort(keys);
		put(keys, values, 0, keys.length - 1);
	}

	private void put(Key[] keys, Value[] values, int lo, int hi) {
		if (lo > hi)
			return;
		int mid = lo + ((hi - lo) >> 1);
		put(keys[mid], values[mid]);
		put(keys, values, lo, mid - 1);
		put(keys, values, mid + 1, hi);
	}

	public void put(Key key, Value value) {
		root = put(root, key, value);
	}

	private Node put(Node x, Key key, Value value) {
		if (x == null)
			return new Node(key, value);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, value);
		else if (cmp > 0)
			x.right = put(x.right, key, value);
		else
			x.value = value;
		return x;
	}

	public Value get(Key key) {
		return get(root, key).value;
	}

	private Node get(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		return x;
	}

	public double avgCompares() {
		return avgCompares(root);
	}

	public double avgCompares(Node x) {
		if (root == null)
			throw new RuntimeException("Symbol table underflow");
		return (double) totalCompares(x) / size(x);
	}

	private int totalCompares(Node x) {
		if (x == null)
			return 0;
		return totalCompares(x.left) + totalCompares(x.right) + size(x);
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		return size(x.left) + size(x.right) + 1;
	}

	public Iterable<Key> keys() {
		Collection<Key> queue = new ArrayList<>();
		keys(root, queue);
		return queue;
	}

	private void keys(Node x, Collection<Key> queue) {
		if (x == null)
			return;
		keys(x.left, queue);
		queue.add(x.key);
		keys(x.right, queue);
	}

	public int height() {
		return height(root);
	}

	private int height(Node x) {
		if (x == null)
			return 0;
		return Math.max(height(x.left), height(x.right)) + 1;

	}

	public static void main(String[] args) {
		List<String> keys = new ArrayList<>(10679);
		List<Integer> values = new ArrayList<>(10679);
		int i = 0;
		for (; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			keys.add(key);
			values.add(i);
		}
		PerfectBalance<String, Integer> pb = new PerfectBalance<>(keys.toArray(new String[i]),
				values.toArray(new Integer[i]));
		for (String s : pb.keys())
			System.out.println(s + " " + pb.get(s));
		System.out.println(pb.avgCompares());
		System.out.println(BST.optCompares(pb.size()));
	}

}
