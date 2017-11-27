package test;

import static sorting.Example.exch;
import static sorting.Example.isSorted;
import static sorting.Example.less;
import static sorting.Example.show;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import sorting.Sort;

public class ShellTest implements Sort {

	@Override
	public void sort(Comparable[] a) {
		int N = a.length;
		int h = 1;
		while (h < N / 3)
			h = 3 * h + 1;
		while (h >= 1) {
			for (int i = 0; i < N - h; i += h) {

				for (int j = i + h; j > h - 1; j -= h) {
					if (less(a[j], a[j - h]))
						exch(a, j, j - h);
				}
			}
			h /= 3;
		}
	}

	public static void main(String[] args) {
		List<Integer> a = new ArrayList<>(100);
		for (int i = 0; i < 100; i++)
			a.add(i);
		Collections.shuffle(a);
		Sort sort = new ShellTest();
		Integer[] result = a.toArray(new Integer[100]);
		System.out.println(Arrays.deepToString(result));
		sort.sort(result);
		System.out.println(isSorted(result));
		System.out.println(sort.getClass().getSimpleName());
		show(result);
	}

}
