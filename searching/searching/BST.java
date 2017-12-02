package searching;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;

	private class Node {
		private Key key;
		private Value value;
		private Node left, right;
		private int N;

		public Node(Key key, Value value, int N) {
			this.key = key;
			this.value = value;
			this.N = N;
		}
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.value;
	}

	public void put(Key key, Value value) {
		root = put(root, key, value);
	}

	private Node put(Node x, Key key, Value value) {
		if (x == null)
			return new Node(key, value, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, value);
		else if (cmp > 0)
			x.right = put(x.right, key, value);
		else
			x.value = value;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public Key min() {
		if (isEmpty())
			return null;
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null)
			return x;
		else
			return min(x.left);
	}

	public Key max() {
		return max(root).key;
	}

	private Node max(Node x) {
		if (x.right == null)
			return x;
		else
			return max(x.right);
	}

	public Key floor(Key key) {
		return floor(root, key);
	}

	private Key floor(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x.key;
		else if (cmp < 0)
			return floor(x.left, key);
		else {
			Key rKey = floor(x.right, key);
			return rKey == null ? x.key : rKey;
		}
	}

	public Key ceiling(Key key) {
		return ceiling(root, key);
	}

	private Key ceiling(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x.key;
		else if (cmp > 0)
			return ceiling(x.right, key);
		else {
			Key lKey = ceiling(x.left, key);
			return lKey == null ? x.key : lKey;
		}
	}

	public int size() {
		return size(root);
	}

	public int size(Node x) {
		return x == null ? 0 : x.N;
		// if (x == null)
		// return 0;
		// return size(x.left) + size(x.right) + 1;
	}

	public int size(Key lo, Key hi) {
		if (lo.compareTo(hi) > 0)
			return 0;
		if (contains(hi))
			return rank(hi) - rank(lo) + 1;
		else
			return rank(hi) - rank(lo);
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Key select(int k) {
		return select(root, k);
	}

	private Key select(Node x, int k) {
		if (x == null)
			return null;
		int t = size(x.left);
		if (k < t)
			return select(x.left, k);
		else if (k > t)
			return select(x.right, k - t - 1);
		else
			return x.key;
	}

	public int rank(Key key) {
		return rank(root, key);
	}

	private int rank(Node x, Key key) {
		if (x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return rank(x.left, key);
		else if (cmp > 0)
			return rank(x.right, key) + 1 + size(x.left);
		return size(x.left);
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	public void deleteMin() {
		if (isEmpty())
			throw new RuntimeException("Symbol table underflow");
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void deleteMax() {
		if (isEmpty())
			throw new RuntimeException("Symbol table underflow");
		deleteMax(root);
	}

	private Node deleteMax(Node x) {
		if ((x.right == null))
			return x.left;
		x.right = deleteMin(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	public Node delete(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = delete(x.left, key);
		else if (cmp > 0)
			x.right = delete(x.right, key);
		else {
			if (x.left == null)
				return x.right;
			if (x.right == null)
				return x.left;
			Node t = x;
			x = min(x.right);
			// 会对t.right进行重新计数
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		// 会对x进行重新计数
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public Iterable<Key> keys() {
		Collection<Key> keys = new ArrayList<>(size());
		keys(root, keys);
		return keys;
	}

	private void keys(Node x, Collection<Key> queue) {
		if (x == null)
			return;
		keys(x.left, queue);
		queue.add(x.key);
		keys(x.right, queue);
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		Collection<Key> queue = new ArrayList<>(size());
		keys(root, lo, hi, queue);
		return queue;
	}

	private void keys(Node x, Key lo, Key hi, Collection<Key> queue) {
		if (x == null)
			return;
		if (lo.compareTo(x.key) < 0)
			keys(x.left, lo, hi, queue);
		if (lo.compareTo(x.key) <= 0 && hi.compareTo(x.key) >= 0)
			queue.add(x.key);
		if (hi.compareTo(x.key) > 0)
			keys(x.right, lo, hi, queue);
	}

	public int height() {
		return height(root);
	}

	private int height(Node x) {
		if (x == null)
			return 0;
		return Math.max(height(x.left), height(x.right)) + 1;
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

	public boolean isBinaryTree() {
		return isBinaryTree(root);
	}

	private boolean isBinaryTree(Node x) {
		return confirmSize(x) == size(x);
	}

	private int confirmSize(Node x) {
		if (x == null)
			return 0;
		return confirmSize(x.left) + confirmSize(x.right) + 1;
	}

	public static double optCompares(int N) {
		assert (N > 0);
		int h = 0, n = 0;
		while ((n = (n + 1 << 1) - 1) <= N) {
			h++;
		}
		int total = 0;
		for (int i = 1; i <= h; i++) {
			total += i * (1 << i - 1);
		}
		total += (N - (1 << h) + 1) * (h + 1);
		return (double) total / N;
	}

	public static void main(String[] args) throws IOException {
		BST<String, Integer> bst = new BST<>();
		Scanner scanner = new Scanner(new BufferedInputStream(System.in), "UTF-8");
		for (int i = 0; scanner.hasNext(); i++) {
			String key = scanner.next();
			bst.put(key, i);
		}
		scanner.close();

		System.out.println(bst.size());
		// System.out.println(bst.min());
		// System.out.println(bst.max());
		// System.out.println(
		// bst.floor("youthman") + "\n" + bst.ceiling("youthman"));
		// System.out.println(bst.select(10678));
		// int k = bst.rank("key");
		// System.out.println(k);
		// System.out.println(bst.select(k));
		// bst.deleteMax();
		// bst.deleteMin();
		// System.out.println(bst.select(0));
		// bst.delete("key");
		// System.out.println(bst.size());
		// System.out.println(bst.select(k));
		// int i = 0;
		// for (String key : bst.keys("noo", "yesb")) {
		// i++;
		// }
		// System.out.println(i);
		System.out.println(bst.height());
		System.out.println(bst.avgCompares());
		System.out.println(BST.optCompares(10679));
		// System.out.println(bst.ceiling("ba"));
		// System.out.println(bst.size("a", "babble"));
		System.out.println(bst.isBinaryTree());
		System.out.println(bst.get("he"));
	}
}
