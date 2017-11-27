package sorting;

import static sorting.Example.*;

import com.algs4.stdlib.StdIn;

public class Insertion implements Sort {

	@Override
	public void sort(Comparable[] a) {
		for (int i = 1; i < a.length; i++)
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
				exch(a, j, j - 1);
	}

	public static void main(String[] args) {
		String[] a = StdIn.readStrings();
		Sort sort = new Insertion();
		sort.sort(a);
		System.out.println(isSorted(a));
		show(a);
	}
}
