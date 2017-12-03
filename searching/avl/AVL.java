package avl;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
		adjust(x);
		return x;
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = delete(x.left, key);
			if (height(x.right) - height(x.left) == 2) {
				if (height(x.right.right) > height(x.right.left))
					x = rightRightRotate(x);
				else
					x = rightLeftRotate(x);
			}
		} else if (cmp > 0) {
			x.right = delete(x.right, key);
			if (height(x.left) - height(x.right) == 2) {
				if (height(x.left.left) > height(x.left.right))
					x = leftLeftRotate(x);
				else
					x = leftRightRotate(x);
			}
		} else {
			if (x.left == null)
				return x.right;
			else if (x.right == null)
				return x.left;
			else {
				Node t = x;
				if (height(x.right) > height(x.left)) {
					x = Min(x.right);
					/***********************************************************/
					/* this place must no write as: */
					/*** x.left = t.left; */
					/*** x.right = deleteMin(t.right); */
					/* if not, you will delete the minimum of x's left-child */
					/* instead of the minimum of the x's right-child */
					/***********************************************************/
					x.right = deleteMin(t.right);
					x.left = t.left;
				} else {
					x = Max(x.left);
					x.left = deleteMax(t.left);
					x.right = t.right;
				}
			}

		}
		adjust(x);
		return x;
	}

	public void deleteMin() {
		if (root == null)
			throw new RuntimeException("Symbol table underflow");
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		if (height(x.right) - height(x.left) == 2) {
			if (height(x.right.right) > height(x.right.left))
				x = rightRightRotate(x);
			else
				x = rightLeftRotate(x);
		}
		adjust(x);
		return x;
	}

	public void deleteMax() {
		if (root == null)
			throw new RuntimeException("Symbol table underflow");
		root = deleteMax(root);
	}

	private Node deleteMax(Node x) {
		if (x.right == null)
			return x.left;
		x.right = deleteMax(x.right);
		if (height(x.left) - height(x.right) == 2) {
			if (height(x.left.left) > height(x.left.right))
				x = leftLeftRotate(x);
			else
				x = leftRightRotate(x);
		}
		adjust(x);
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

	public Key Max() {
		return Max(root).key;
	}

	private Node Max(Node x) {
		if (x == null)
			return null;
		if (x.right != null)
			return Max(x.right);
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

	private void adjust(Node x) {
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		x.N = size(x.left) + size(x.right) + 1;
	}

	private Node leftLeftRotate(Node x) {
		Node t = x;
		x = t.left;
		t.left = x.right;
		x.right = t;
		adjust(t);
		adjust(x);
		return x;
	}

	private Node leftRightRotate(Node x) {
		x.left = rightRightRotate(x.left);
		return leftLeftRotate(x);
	}

	private Node rightRightRotate(Node x) {
		Node t = x;
		x = t.right;
		t.right = x.left;
		x.left = t;
		adjust(t);
		adjust(x);
		return x;
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

	public boolean isAVL() {
		return isAVL(root);
	}

	private boolean isAVL(Node x) {
		if (x == null)
			return true;
		if (height(x.left) - height(x.right) > 1
				|| height(x.left) - height(x.right) < -1)
			return false;
		return isAVL(x.left) && isAVL(x.right);

	}

	private int totalCompares(Node x) {
		if (x == null)
			return 0;
		return totalCompares(x.left) + totalCompares(x.right) + size(x);
	}

	public Node get(int i) {
		if (i > size(root))
			throw new IllegalArgumentException(i + " is out of size");
		return get(root, i);
	}

	public Node get(Node x, int i) {
		int t = size(x.left);
		if (i <= t)
			return get(x.left, i);
		else if (i > t + 1)
			return get(x.right, i - t - 1);
		else
			return x;
	}

	public static void main(String[] args) {
		AVL<String, Integer> avl = new AVL<>();
		Scanner scanner = new Scanner(new BufferedInputStream(System.in),
				"UTF-8");
		for (int i = 0; scanner.hasNext(); i++) {
			String key = scanner.next();
			avl.put(key, i);
		}
		scanner.close();
		// for (String key : avl.keys())
		// System.out.println(key + " " + avl.get(key));
		// System.out.println(avl.avgCompares());
		// System.out.println(BST.optCompares(10679));

		System.out.println(avl.size());
		System.out.println(avl.height());
		System.out.println(avl.isAVL());
		Random rand = new Random(System.currentTimeMillis());
		// for (int i = 0; i < 100;) {
		// int k = rand.nextInt(avl.size()) + 1;
		// if (avl.height(avl.get(k)) != 1) {
		// continue;
		// }
		// System.out.println(avl.get(k).key);
		// avl.delete(avl.get(k).key);
		// i++;
		// avl.deleteMax();
		// avl.deleteMin();
		// }
		for (int i = 0; i < 10000; i++) {
			int k = rand.nextInt(avl.size()) + 1;
			avl.delete(avl.get(k).key);
		}

		System.err.println(avl.isAVL());
		System.err.println(avl.size());
		System.err.println(avl.height());
	}
}
