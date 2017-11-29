package sorting;

import static sorting.Example.exch;
import static sorting.Example.isSorted;
import static sorting.Example.less;

import java.util.Arrays;
import java.util.Random;
@SuppressWarnings("rawtypes")
public class Selection implements Sort {

	
	@Override
	public void sort(Comparable[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < a.length; j++) {
				if (less(a[j], a[min]))
					min = j;
			}
			exch(a, i, min);
		}
	}

	public static void sort2(Comparable[] a, int lo) {
		if (lo >= a.length)
			return;
		int min = lo;
		for (int i = lo + 1; i < a.length; i++) {
			if (less(a[i], a[min])) {
				min = i;
			}
		}
		exch(a, lo, min);
		sort2(a, ++lo);
	}

	public static void main(String[] args) {
		// String[] a = StdIn.readStrings();
		// Sort sort = new Selection();
		// sort.sort(a);
		// System.out.println(isSorted(a));
		// System.out.println(sort.getClass().getSimpleName());
		// show(a);

		Random rand = new Random(System.currentTimeMillis());
		Integer[] a = new Integer[10];
		for (int i = 0; i < a.length; i++)
			a[i] = rand.nextInt(100);
		sort2(a, 0);
		System.out.println(Arrays.deepToString(a));
		System.out.println(isSorted(a));
	}

}
