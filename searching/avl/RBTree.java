package avl;

import java.io.BufferedInputStream;
import java.util.Scanner;
import java.util.Stack;

public class RBTree<Key extends Comparable<Key>, Value> {
	Node root;

	private class Node {
		Key key;
		Value val;
		Node parent;
		Node left;
		Node right;
		Color color;
		int N;

		public Node(Key key, Value val) {
			// this.parent = parent;
			this.key = key;
			this.val = val;
			this.color = Color.RED;
			this.N = 1;
		}

		/*
		 * public boolean isLeft() { return this.parent != null && this ==
		 * this.parent.left; }
		 * 
		 * public boolean isRight() { return this.parent != null && this ==
		 * this.parent.right; }
		 */

	}

	private boolean isRed(Node x) {
		return x != null ? x.color == Color.RED : false;
	}

	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null)
			return new Node(key, val);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		// if (isRed(x)) {
		// if (isRed(x.left)) {
		// if (x.isRight())
		// x = leftRotate(x);
		// if (x.isLeft()) {
		// if (isRed(x.parent.right)) {
		// x.color = Color.BLACK;
		// x.parent.color = Color.RED;
		// x.parent.right.color = Color.BLACK;
		// } else {
		// x.parent.parentleftLeftRotate
		// }
		// }
		// } else if (isRed(x.right)) {
		//
		// }
		// }

		if (isRed(x.left)) {
			if (isRed(x.left.right))
				x.left = rightRotate(x.left);
			if (isRed(x.left.left)) {
				if (isRed(x.right)) {
					x.color = Color.RED;
					x.left.color = Color.BLACK;
					x.right.color = Color.BLACK;
				} else
					x = leftLeftRotate(x);
			}
		}
		if (isRed(x.right)) {
			if (isRed(x.right.left))
				x.right = leftRotate(x.right);
			if (isRed(x.right.right)) {
				if (isRed(x.left)) {
					x.color = Color.RED;
					x.left.color = Color.BLACK;
					x.right.color = Color.BLACK;
				} else
					x = rightRightRotate(x);
			}
		}

		// if (isRed(x) && isRed(x.parent)) {
		// if (x.isRight())
		// x.parent = rightRotate(x.parent);
		// }
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	private Node leftRotate(Node x) {
		Node t = x;
		x = t.left;
		t.left = x.right;
		x.right = t;
		x.N = size(t);
		t.N = size(t.left) + size(t.right) + 1;
		return x;
	}

	private Node leftLeftRotate(Node x) {
		Node t = x;
		x = t.left;
		t.left = x.right;
		x.right = t;
		x.color = t.color;
		t.color = Color.RED;
		x.N = size(t);
		t.N = size(t.left) + size(t.right) + 1;
		return x;
	}

	private Node rightRotate(Node x) {
		Node t = x;
		x = t.right;
		t.right = x.left;
		x.left = t;
		x.N = t.N;
		t.N = size(t.left) + size(t.right) + 1;
		return x;
	}

	private Node rightRightRotate(Node x) {
		Node t = x;
		x = t.right;
		t.right = t.left;
		x.left = t;
		x.N = size(t);
		t.N = size(t.left) + size(t.right) + 1;
		return x;
	}

	// private boolean isLeft(Node x) {
	// return x != root ? x == x.parent.left : false;
	// }

	public Value get(Key key) {
		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				x = x.left;
			else if (cmp > 0)
				x = x.right;
			else
				return x.val;
		}
		return null;
	}

	public int size() {
		return size(root);
	}

	public int size(Node x) {
		return x == null ? 0 : size(x.left) + size(x.right) + 1;
	}

	private int height() {
		return height(root);
	}

	private int height(Node x) {
		if (x == null)
			return 0;
		return Math.max(height(x.left), height(x.right)) + 1;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedInputStream(System.in), "UTF-8");
		RBTree<String, Integer> brTree = new RBTree<>();
		for (int i = 0; scan.hasNext(); i++) {
			brTree.put(scan.next(), i);
		}
		scan.close();
		System.out.println("size: " + brTree.size());
		System.out.println("height: " + brTree.height());
	}

}
