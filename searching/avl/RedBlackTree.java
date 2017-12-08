package avl;

import java.io.BufferedInputStream;
import java.util.Scanner;

import searching.BST;

enum Color {
	RED, BLACK;
}

public class RedBlackTree<Key extends Comparable<Key>, Value> {
	Node root;

	private class Node {
		Key key;
		Value value;
		Color color;
		int N;
		Node left, right;

		private Node(Key key, Value value, Color color) {
			this.key = key;
			this.value = value;
			this.color = color;
			this.N = 1;
		}
	}

	private boolean isRed(Node x) {
		if (x == null)
			return false;
		return x.color == Color.RED;
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		return x == null ? 0 : x.N;
	}

	public int height() {
		return height(root);
	}

	private int height(Node x) {
		return x == null ? 0 : Math.max(height(x.left), height(x.right)) + 1;
	}

	public int minHeight() {
		return minHeight(root);
	}

	private int minHeight(Node x) {
		return x == null ? 0 : Math.min(minHeight(x.left), minHeight(x.right)) + 1;
	}

	public int totalCmp() {
		return totalCmp(root);
	}

	private int totalCmp(Node x) {
		if (x == null)
			return 0;
		return size(x) + totalCmp(x.left) + totalCmp(x.right);
	}

	public void put(Key key, Value value) {
		root = put(root, key, value);
		root.color = Color.BLACK;
	}

	public Node put(Node x, Key key, Value value) {
		if (x == null)
			return new Node(key, value, Color.RED);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, value);
		else if (cmp > 0)
			x.right = put(x.right, key, value);
		else
			x.value = value;
		if (isRed(x.left) && isRed(x.left.left))
			x = rightRotate(x);
		if (!isRed(x.left) && isRed(x.right))
			x = leftRotate(x);
		if (isRed(x.left) && isRed(x.right))
			flipColor(x);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public Value get(Key key) {
		if (root == null)
			return null;
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
		else
			return x;
	}

	private Node leftRotate(Node x) {
		Node t = x;
		x = t.right;
		t.right = x.left;
		x.left = t;
		x.color = t.color;
		x.left.color = Color.RED;
		x.N = t.N;
		t.N = size(t.left) + size(t.right) + 1;
		return x;
	}

	private Node rightRotate(Node x) {
		assert x != null && x.color == Color.BLACK;
		Node t = x;
		x = t.left;
		t.left = x.right;
		x.right = t;
		x.color = x.right.color;
		x.right.color = Color.RED;
		x.N = t.N;
		t.N = size(t.left) + size(t.right) + 1;
		return x;
	}

	private void flipColor(Node x) {
		assert x != null && x.color == Color.BLACK;
		assert x.left != null && x.left.color == Color.RED;
		assert x.right != null && x.right.color == Color.RED;
		x.color = Color.RED;
		x.left.color = x.right.color = Color.BLACK;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedInputStream(System.in), "UTF-8");
		RedBlackTree<String, Integer> brTree = new RedBlackTree<>();
		for (int i = 0; scan.hasNext(); i++) {
			brTree.put(scan.next(), i);
		}
		scan.close();
		System.out.println("size: " + brTree.size());
		System.out.println("height: " + brTree.height());
		System.out.println("minimum height: " + brTree.minHeight());
		System.out.println("average height: " + (double) brTree.totalCmp() / brTree.size());
		System.out.println("ideal height: " + BST.optCompares(brTree.size()));
	}
}
