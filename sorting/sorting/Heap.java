package sorting;

import static sorting.Example.isSorted;

import java.util.Arrays;
import java.util.Random;

public class Heap {

	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		int N = a.length - 1;
		for (int i = (N - 1) / 2; i >= 0; i--) {
			sink(a, i, N);
		}
		while (N > 0) {
			exch(a, 0, N);
			N--;
			sink(a, 0, N);
		}
	}

	private static void sink(Comparable[] a, int k, int N) {
		while (2 * k + 1 <= N) {
			int j = 2 * k + 1;
			if (j + 1 <= N && less(a, j, j + 1))
				j++;
			if (less(a, j, k))
				break;
			exch(a, k, j);
			k = j;
		}
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private static boolean less(Comparable[] a, int i, int j) {
		return a[i].compareTo(a[j]) < 0;
	}

	public static void main(String[] args) {
		Random rand = new Random(System.currentTimeMillis());
		int N = rand.nextInt(20);
		Integer[] a = new Integer[N];
		for (int i = 0; i < N; i++)
			a[i] = rand.nextInt(1000);
		System.out.println(Arrays.toString(a));
		sort(a);
		System.out.println(Arrays.toString(a));
		System.out.println(isSorted(a));
	}

}
