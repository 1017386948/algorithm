package avl;

public class TwoThreeTree<Key, Value> {
	Node root;
	private class Node {
		Key key;
		Value value;
		Node left;
		Node mid;
		Node right;
		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
		}
	}
	public void put(Key key, Value value) {
	}
}
