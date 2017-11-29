package test;

import static sorting.Example.exch;
import static sorting.Example.isSorted;
import static sorting.Example.less;
import static sorting.Example.show;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sorting.Sort;

public class InsertionTest implements Sort {

	@Override
	public void sort(@SuppressWarnings("rawtypes") Comparable[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			int j = i + 1;
			while (j > 0 && less(a[j], a[j - 1])) {
				exch(a, j, j - 1);
				j--;
			}
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedInputStream(System.in), "UTF-8");
		List<String> target = new ArrayList<>();
		while (scan.hasNext())
			target.add(scan.next());
		String[] a = new String[target.size()];
		scan.close();
		target.toArray(a);
		Sort sort = new InsertionTest();
		sort.sort(a);
		System.out.println(isSorted(a));
		System.out.println(sort.getClass().getSimpleName());
		show(a);
	}

}
