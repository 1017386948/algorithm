package fundamentals;

import com.algs4.stdlib.StdIn;
import com.algs4.stdlib.StdOut;

public class PathHalvinhQuickUnion {
	private int count;
	private final int[] id;

	public PathHalvinhQuickUnion(int N) {
		this.count = N;
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
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
		int temp = p;
		while (temp != id[temp])
			temp = id[temp];
		while (p != id[p]) {
			int pp = p;
			p = id[p];
			id[pp] = temp;
		}
		return p;
	}

	public int count() {
		return count;
	}
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		// String file = System.getProperty("user.dir")
		// +
		// "algorithm\\resource\\AlgorithmsSedgewick-master\\1-Fundamentals\\1-5-UnionFind\\tinyUF.txt";
		int N = StdIn.readInt();
		PathHalvinhQuickUnion uf = new PathHalvinhQuickUnion(N);
		int i = 2000000;
		while (i-- > 0) {
			int q = StdIn.readInt();
			int p = StdIn.readInt();
			uf.union(p, q);
			StdOut.println(p + " " + q + " " + uf.count);
		}
		System.out.println(System.currentTimeMillis() - start);
		System.out.println("PathCompressQuickUnion");
		StdOut.print(uf.count + "components");
	}
}
