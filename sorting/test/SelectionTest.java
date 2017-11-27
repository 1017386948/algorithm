package test;

import sorting.Sort;
import static sorting.Example.*;

import com.algs4.stdlib.StdIn;

import sorting.Selection;

public class SelectionTest implements Sort {

	@Override
	public void sort(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			int min = i;
			for (int j = i + 1; j < a.length; j++) {
				if (less(a[j], a[min])) {
					min = j;
				}
			}
			exch(a, i, min);
		}
	}

	public static void main(String[] args) {
		String[] a = StdIn.readStrings();
		Sort sort = new SelectionTest();
		sort.sort(a);
		System.out.println(isSorted(a));
		System.out.println(sort.getClass().getSimpleName());
		show(a);
	}

}
