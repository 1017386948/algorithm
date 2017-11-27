package exercises;

import java.util.Arrays;

public class KendallTau {
	public static int distance(int[] a, int[] b) {
		if (a.length != a.length)
			throw new IllegalArgumentException("Wrong Argument");
		int n = a.length;
		int[] ainv = new int[n];
		for (int i = 0; i < n; i++)
			ainv[a[i]] = i;
		int[] bnew = new int[n];
		for (int i = 0; i < n; i++) {
			bnew[i] = ainv[b[i]];
		}
		return Inversions.count(bnew);
	}

	public static void main(String[] args) {
		int[] a = { 0, 3, 1, 6, 2, 5, 4 };
		int[] b = { 1, 0, 3, 6, 4, 2, 5 };
		int distance = distance(a, b);
		System.out.println(distance);
		System.out.println(Inversions.brute(a, b) == distance);
	}
}

class Inversions {
	private Inversions() {
	}

	public static int count(int[] a) {
		int n = a.length;
		int[] aux = Arrays.copyOf(a, n);
		return count(aux, new int[n], 0, n - 1);
	}

	private static int count(int[] b, int[] aux, int lo, int hi) {
		int inversions = 0;
		if (hi <= lo)
			return 0;
		int mid = lo + (hi - lo) / 2;
		inversions += count(b, aux, lo, mid);
		inversions += count(b, aux, mid + 1, hi);
		inversions += merge(b, aux, lo, mid, hi);
		return inversions;
	}

	private static int merge(int[] a, int[] aux, int lo, int mid, int hi) {
		int inversions = 0;
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (a[i] > a[j]) {
				a[k] = aux[j++];
				inversions += mid - i + 1;
			} else
				a[k] = aux[i++];

		}
		return inversions;
	}

	public static int brute(int[] a, int[] b) {
		int n = a.length;
		int[] ainv = new int[n];
		for (int i = 0; i < n; i++)
			ainv[a[i]] = i;
		int[] bnew = new int[n];
		for (int i = 0; i < n; i++) {
			bnew[i] = ainv[b[i]];
		}

		int inversions = 0;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++) {
				if (bnew[i] > bnew[j])
					inversions++;
			}
		return inversions;
	}

}
