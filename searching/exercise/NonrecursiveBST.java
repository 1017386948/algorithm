package exercise;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.Stack;

public class NonrecursiveBST<Key extends Comparable<Key>, Value> {
	Node root;

	private class Node {
		Key key;
		Value value;
		Node left;
		Node right;

		Node(Key key, Value value) {
			this.key = key;
			this.value = value;
		}
	}

	public void put(Key key, Value value) {
		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) {
				if (x.left != null)
					x = x.left;
				else {
					x.left = new Node(key, value);
					return;
				}
			} else if (cmp > 0) {
				if (x.right != null)
					x = x.right;
				else {
					x.right = new Node(key, value);
					return;
				}
			} else {
				x.value = value;
				return;
			}
		}
		root = new Node(key, value);
	}

	public Value get(Key key) {
		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				x = x.left;
			else if (cmp > 0)
				x = x.right;
			else
				return x.value;
		}
		return null;
	}

	public Iterable<Key> keys() {
		Node x = root;
		Collection<Key> queue = new ArrayList<>();
		Stack<Node> stack = new Stack<>();
		while (x != null || !stack.isEmpty()) {
			if (x != null) {
				stack.push(x);
				x = x.left;
			} else {
				x = stack.pop();
				queue.add(x.key);
				x = x.right;
			}
		}
		return queue;
	}

	public Key min() {
		Node x = root;
		if (x == null)
			return null;
		while (x.left != null)
			x = x.left;
		return x.key;
	}

	public Key max() {
		Node x = root;
		if (x == null)
			return null;
		while (x.right != null)
			x = x.right;
		return x.key;
	}

	public static void main(String[] args) {
		NonrecursiveBST<String, Integer> nRecBST = new NonrecursiveBST<>();
		Scanner scanner = new Scanner(new BufferedInputStream(System.in), "UTF-8");
		for (int i = 0; scanner.hasNext(); i++) {
			String key = scanner.next();
			nRecBST.put(key, i);
		}
		scanner.close();
		int n = 0;
		for (String s : nRecBST.keys()) {
			++n;
			System.out.println(s + " " + nRecBST.get(s));
		}
		System.out.println(n);
	}

}
