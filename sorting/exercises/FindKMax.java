package exercises;

import java.util.Arrays;
import java.util.Random;

public class FindKMax {
	public static int findMaxK(int[] a, int k) {
		int n = a.length;
		assert (k > 0 && k <= n);
		return findMaxK(a, n - k, 0, n - 1);
	}

	private static int findMaxK(int[] a, int k, int lo, int hi) {
		int pos;
		while ((pos = partition(a, lo, hi)) != k) {
			if (pos < k)
				lo = pos + 1;
			else
				hi = pos - 1;
		}
		return a[pos];
	}

	private static int partition(int[] a, int lo, int hi) {
		if (lo >= hi)
			return lo;
		int i = lo, j = hi + 1;
		int v = a[lo];
		while (true) {
			while (a[++i] < v)
				if (i == hi)
					break;
			while (a[--j] > v)
				if (j == lo)
					break;
			if (i >= j)
				break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}

	private static void exch(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static String intArrayString(int[] a) {
		StringBuffer sb = new StringBuffer("[");
		int i = 0;
		for (; i < a.length - 1; i++) {
			sb.append(a[i] + ", ");
		}
		sb.append(a[i] + "]");
		return sb.toString();
	}

	public static void main(String[] args) {
		Random rand = new Random(System.currentTimeMillis());
		int N = rand.nextInt(20) + 1;
		int K = rand.nextInt(N) + 1;
		int[] a = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = rand.nextInt(100);
		}
		System.out.println(intArrayString(a));
		int[] aux = Arrays.copyOf(a, N);
		Arrays.sort(aux);
		System.out.println(intArrayString(aux));
		int maxK = findMaxK(a, K);
		System.out.println("the max " + K + " is " + maxK);
	}
}
