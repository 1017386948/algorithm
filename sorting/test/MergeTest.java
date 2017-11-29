package test;

import static sorting.Example.isSorted;
import static sorting.Example.less;

import java.util.Arrays;
import java.util.Random;

@SuppressWarnings("rawtypes")
public class MergeTest {
	private static Comparable[] aux;

	public static void sort(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
		sort(a, 0, N - 1);
	}

	public static void sort(Comparable[] a, int lo, int hi) {
		if (lo == hi)
			return;
		int mid = (lo + hi) >> 1;
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
	}

	private static void merge(Comparable[] a, int lo, int mid, int hi) {
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		int i = 0, j = mid + 1;
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
		System.out.println(Arrays.toString(a));
		sort(a);
		System.out.println(Arrays.toString(a));
		System.out.println(isSorted(a));
	}
}
