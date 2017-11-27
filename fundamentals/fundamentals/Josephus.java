package fundamentals;

public class Josephus {

	private Node first;

	private Josephus(int n, int m) {
		if (n < 0 || m < 0 || n <= m)
			throw new IllegalArgumentException();
		first = new Node(0, null);
		Node pre = first;
		for (int i = 1; i < n; i++) {
			pre.next = new Node(i, null);
			pre = pre.next;
		}
		pre.next = first;
	}

	private static class Node {
		int no;
		Node next;

		Node(int no, Node next) {
			this.no = no;
			this.next = next;
		}
	}

	public static int survivor(int n, int m) {
		Josephus josephus = new Josephus(n, m);
		Node first = josephus.first;
		while (first != first.next) {
			int m0 = m;
			int m1 = m - 1;
			Node pre = first;
			while (--m0 > 0) {
				first = first.next;
			}
			if (m1 == 0) {
				Node tail = pre;
				while (pre.next != tail)
					pre = pre.next;
			} else
				while (--m1 > 0)
					pre = pre.next;
			System.out.print(first.no + " ");
			first = first.next;
			pre.next = first;
		}
		return first.no;
	}

	public static void main(String[] args) {
		System.out.println(Josephus.survivor(41, 3));
	}

}
