package test;

import sorting.Sort;
import static sorting.Example.*;

import com.algs4.stdlib.StdIn;

import sorting.Selection;

public class InsertionTest implements Sort {

	@Override
	public void sort(Comparable[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			int j = i + 1;
			while (j > 0 && less(a[j], a[j - 1])) {
				exch(a, j, j - 1);
				j--;
			}
		}
	}

	public static void main(String[] args) {
		String[] a = StdIn.readStrings();
		Sort sort = new InsertionTest();
		sort.sort(a);
		System.out.println(isSorted(a));
		System.out.println(sort.getClass().getSimpleName());
		show(a);
	}

}
