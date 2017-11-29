package sorting;

import static sorting.Example.exch;
import static sorting.Example.isSorted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
@SuppressWarnings("rawtypes")
public class Quick3way {
	public static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}

	@SuppressWarnings("unchecked")
	private static void sort(Comparable[] a, int lo, int hi) {
		if (lo >= hi)
			return;
		int lt = lo, i = lo + 1, gt = hi;
		Comparable v = a[lo];
		while (i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp < 0)
				exch(a, i++, lt++);
			else if (cmp > 0)
				exch(a, i, gt--);
			else
				i++;
		}
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
	}

	public static void main(String[] args) {
		Random rand = new Random();
		List<Integer> a = new ArrayList<>(100);
		for (int i = 0; i < 100; i++)
			a.add(rand.nextInt(10000));
		Collections.shuffle(a);
		Integer[] result = a.toArray(new Integer[100]);
		System.out.println(Arrays.deepToString(result));
		sort(result);
		System.out.println(isSorted(result));
		System.out.println(Arrays.deepToString(result));
	}
}
