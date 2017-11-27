package fundamentals;

import com.algs4.stdlib.StdIn;
import com.algs4.stdlib.StdOut;

public class QuickFindUF {
	private int count;
	private final int[] id;

	public QuickFindUF(int N) {
		this.count = N;
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
	}

	public void union(int p, int q) {
		assert (p < id.length && p > 0);
		assert (q < id.length && q > 0);
		int idP = find(p), idQ = find(q);
		if (idP == idQ)
			return;
		if (idP < idQ) {
			for (int i = idP; i < id.length; i++)
				if (id[i] == idQ)
					id[i] = idP;
		} else if (idP > idQ) {
			for (int i = idQ; i < id.length; i++)
				if (id[i] == idP)
					id[i] = idQ;
		}
		count--;

	}

	public int find(int p) {
		return id[p];
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
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
		QuickFindUF uf = new QuickFindUF(N);
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
