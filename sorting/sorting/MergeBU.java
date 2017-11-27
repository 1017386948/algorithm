package sorting;

import static sorting.Example.isSorted;
import static sorting.Example.less;

import java.util.Arrays;
import java.util.Random;

public class MergeBU {
	private static Comparable[] aux;

	public static void sort(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
		for (int sz = 1; sz < N; sz *= 2)
			for (int lo = 0; lo < N - sz; lo += 2 * sz)
				merge(a, lo, lo + sz - 1, Math.min((lo + 2 * sz - 1), N - 1));
	}

	private static void merge(Comparable[] a, int lo, int mid, int hi) {
		for (int i = lo; i <= hi; i++)
			aux[i] = a[i];
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[i], aux[j]))
				a[k] = aux[i++];
			else
				a[k] = aux[j++];
		}
	}

	public static void main(String[] args) {
		Random rand = new Random();
		Integer[] a = new Integer[300];
		for (int i = 0; i < a.length; i++)
			a[i] = rand.nextInt(1000);
		sort(a);
		System.out.println(Arrays.toString(a));
		System.out.println(isSorted(a));
	}

}
