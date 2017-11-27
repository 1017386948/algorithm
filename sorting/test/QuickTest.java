package test;

import static sorting.Example.exch;
import static sorting.Example.isSorted;
import static sorting.Example.less;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import sorting.Sort;

public class QuickTest implements Sort {

	@Override
	public void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}

	private void sort(Comparable[] a, int lo, int hi) {
		if (lo >= hi)
			return;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	private int partition(Comparable[] a, int lo, int hi) {
		Comparable v = a[lo];
		int i = lo, j = hi + 1;
		while (true) {
			while (less(a[++i], v))
				if (i == hi)
					break;
			while (less(v, a[--j]))
				if (j == lo)
					break;
			if (i >= j)
				break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}

	public static void main(String[] args) {
		Random rand = new Random();
		List<Integer> a = new ArrayList<>(100);
		for (int i = 0; i < 100; i++)
			a.add(rand.nextInt(10000));
		Collections.shuffle(a);
		Sort sort = new QuickTest();
		Integer[] result = a.toArray(new Integer[100]);
		System.out.println(Arrays.deepToString(result));
		sort.sort(result);
		System.out.println(isSorted(result));
		System.out.println(Arrays.deepToString(result));
	}

}
