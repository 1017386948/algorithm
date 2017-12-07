package avl;

enum Color {
	RED, BLACK;
}

public class RedBlackTree<Key extends Comparable<Key>, Value> {
	Node root;

	private class Node {
		Key key;
		Value value;
		int N;
		Color color;
		Node left;
		Node right;
		public Node(Key key, Value value, int n, Color color) {
			this.key = key;
			this.value = value;
			N = n;
			this.color = color;
		}
	}

	private boolean isRed(Node x) {
		if (x == null)
			return false;
		return x.color == Color.RED;
	}

	private int size(Node x) {
		return x == null ? 0 : x.N;
	}

	public void put(Key key, Value value) {
		root = put(root, key, value);
	}

	public Node put(Node x, Key key, Value value) {
		if (x == null)
			return new Node(key, value, 1, Color.RED);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, value);
		else if (cmp > 0)
			x.right = put(x.right, key, value);
		else
			x.value = value;
		return x;
	}
}
