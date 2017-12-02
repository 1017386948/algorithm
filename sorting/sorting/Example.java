package sorting;

import com.algs4.stdlib.StdOut;

@SuppressWarnings("rawtypes")
public class Example {
	public static void sort(Comparable[] a) {

	}

	@SuppressWarnings("unchecked")
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	public static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++)
			StdOut.print(a[i] + " ");
		StdOut.println();
	}

	public static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

	public static boolean isSorted(int[] a) {
		for (int i = 1; i < a.length; i++)
			if (a[i] < a[i - 1])
				return false;
		return true;
	}

	public static String arrayToString(int[] a) {
		StringBuffer bf = new StringBuffer();
		bf.append("[");
		if (a.length != 0) {
			int i = 0;
			for (; i < a.length - 1; i++) {
				bf.append(a[i]).append(", ");
			}
			bf.append(a[i]);
		}
		bf.append("]\n");
		return bf.toString();
	}
}
