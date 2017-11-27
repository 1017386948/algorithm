package fundamentals;

import java.util.Arrays;

import com.algs4.stdlib.StdIn;
import com.algs4.stdlib.StdOut;

public class PathHalvingWeightQuickUnion {
	private final int[] id;
	private final int[] sz;
	private int count;
	public PathHalvingWeightQuickUnion(int N) {
		this.count = N;
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 0;
		}
	}
	public void union(int p, int q) {
		int IDp = find(p);
		int IDq = find(q);
		if (IDp == IDq)
			return;
		if (sz[IDp] < sz[IDq]) {
			id[IDp] = IDq;
		} else if (sz[IDp] > sz[IDq]) {
			id[IDq] = IDp;
		} else {
			id[IDq] = IDp;
			sz[IDp]++;
		}
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
		PathHalvingWeightQuickUnion uf = new PathHalvingWeightQuickUnion(N);
		int i = 2000000;
		while (i-- > 0) {
			int q = StdIn.readInt();
			int p = StdIn.readInt();
			uf.union(p, q);
//			StdOut.println(p + " " + q + " " + uf.count);
		}
		System.out.println(System.currentTimeMillis() - start);
		StdOut.print(uf.count + "components");
	}
}
