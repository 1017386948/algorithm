package avl;

import java.awt.Color;

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
		public Node(Node parent, Key key, Value val) {
			this.parent = parent;
			this.key = key;
			this.val = val;
			this.color = Color.RED;
			this.N = 1;
		}

	}
	private boolean isRed(Node x) {
		return x != null ? x.color == Color.RED : false;
	}

	public void put(Key key, Value val) {
		root = put(root, key, val);
	}
	private Node put(Node x, Key key, Value val) {
		if (x == null)
			return new Node(x.parent, key, val);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		if (isRed(x.left)) {
			if (isRed(x.left.right)) {
				x.left = rightRotate(x.left);
			}
			if (isRed(x.left.left)) {
				if (isRed(x.right)) {
					
				}
			}
		}
		return x;
	}

	private Node rightRotate(Node x) {
		Node t = x;
		x = t.right;
		t.right = x.left;
		x.left = t;
		return x;
	}
	private Node left
	private boolean isLeft(Node x) {
		return x != root ? x == x.parent.left : false;
	}

	public int size() {
		return size(root);
	}

	public int size(Node x) {
		return x == null ? 0 : size(x.left) + size(x.right) + 1;
	}
}
