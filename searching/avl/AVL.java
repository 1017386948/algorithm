package avl;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import searching.BST;

public class AVL<Key extends Comparable<Key>, Value> {
	Node root;

	private class Node {
		Key key;
		Value value;
		Node left;
		Node right;
		int height;
		int N;

		Node(Key key, Value value) {
			this.key = key;
			this.value = value;
			height = 1;
			N = 1;
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
			return new Node(key, value);
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(x.left, key, value);
			if (height(x.left) - height(x.right) == 2) {
				if (key.compareTo(x.left.key) < 0)
					x = leftLeftRotate(x);
				else
					x = leftRightRotate(x);
			}
		} else if (cmp > 0) {
			x.right = put(x.right, key, value);
			if (height(x.right) - height(x.left) == 2) {
				if (key.compareTo(x.right.key) > 0)
					x = rightRightRotate(x);
				else
					x = rightLeftRotate(x);
			}
		} else
			x.value = value;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
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
			else if (x.right == null)
				return x.left;
			else {
				Node t = x;
				x = Min(x.right);
				x.left = t.left;
				x.right = deleteMin(t.right);
			}
		}
		if (height(x.left) - height(x.right) > 1) {
			if (key.compareTo(x.left.key) < 0)
				x = leftLeftRotate(x);
			else
				x = leftRightRotate(x);
		} else if (height(x.left) - height(x.right) < -1) {
			if (key.compareTo(x.right.key) > 0)
				x = rightRightRotate(x);
			else
				x = rightLeftRotate(x);
		}
		x.N = size(x.left) + size(x.right) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		return x;
	}

	public void deleteMin() {
		if (root == null)
			throw new RuntimeException("Symbol table underflow");
		deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		if (height(x.left) - height(x.right) < -1) {
			if (size(x.right.right) > size(x.right.left))
				x = rightRightRotate(x);
			else
				x = rightLeftRotate(x);
		}
		x.N = size(x.left) + size(x.right) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		return x;
	}

	public Key Min() {
		return Min(root).key;
	}

	private Node Min(Node x) {
		if (x == null)
			return null;
		if (x.left != null)
			return Min(x.left);
		else
			return x;
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.N;
	}

	private void correctHeight(Node x) {
		x.height = Math.max(height(x.left), height(x.right)) + 1;
	}

	private Node leftLeftRotate(Node x) {
		Node t = x.left;
		t.N = x.N;
		x.left = t.right;
		correctHeight(x);
		t.right = x;
		correctHeight(t);
		x.N = size(x.left) + size(x.right) + 1;
		return t;
	}

	private Node leftRightRotate(Node x) {
		x.left = rightRightRotate(x.left);
		return leftLeftRotate(x);
	}

	private Node rightRightRotate(Node x) {
		Node t = x.right;
		t.N = x.N;
		x.right = t.left;
		correctHeight(x);
		t.left = x;
		correctHeight(t);
		x.N = size(x.left) + size(x.right) + 1;
		return t;
	}

	private Node rightLeftRotate(Node x) {
		x.right = leftLeftRotate(x.right);
		return rightRightRotate(x);
	}

	public int height() {
		return height(root);
	}

	private int height(Node x) {
		return x == null ? 0 : x.height;
	}

	public Iterable<Key> keys() {
		List<Key> queue = new ArrayList<>(size());
		keys(root, queue);
		return queue;
	}

	private void keys(Node x, List<Key> queue) {
		if (x != null) {
			keys(x.left, queue);
			queue.add(x.key);
			keys(x.right, queue);
		}
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

	public static void main(String[] args) {
		AVL<String, Integer> avl = new AVL<>();
		Scanner scanner = new Scanner(new BufferedInputStream(System.in), "UTF-8");
		for (int i = 0; scanner.hasNext(); i++) {
			String key = scanner.next();
			avl.put(key, i);
		}
		scanner.close();
		for (String key : avl.keys())
			System.out.println(key + " " + avl.get(key));
		System.out.println(avl.avgCompares());
		System.out.println(BST.optCompares(10679));
		System.out.println(avl.size());
		System.out.println(avl.height());
		avl.delete("he");
		System.out.println(avl.size());
	}
}
