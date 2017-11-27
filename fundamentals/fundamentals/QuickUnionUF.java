package fundamentals;

import com.algs4.stdlib.StdIn;
import com.algs4.stdlib.StdOut;

public class QuickUnionUF {
	private int[] id;
	private int count;

	public QuickUnionUF(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
		this.count = N;
	}

	public void union(int p, int q) {
		assert (p < id.length && p > 0);
		assert (q < id.length && q > 0);
		int IDp = find(p);
		int IDq = find(q);
		if (IDp == IDq)
			return;
		id[IDp] = IDq;
		count--;
	}

	public int find(int p) {
		while (p != id[p])
			p = id[p];
		return p;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	public int count() {
		return count;
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		QuickUnionUF uf = new QuickUnionUF(N);
		long start = System.currentTimeMillis();
		while (!StdIn.isEmpty()) {
			int q = StdIn.readInt();
			int p = StdIn.readInt();
			uf.union(p, q);
			StdOut.println(p + " " + q + " " + uf.count);
		}
		System.out.println(System.currentTimeMillis() - start);
		StdOut.print(uf.count + "components");
	}
}
