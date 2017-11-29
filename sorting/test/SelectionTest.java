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

@SuppressWarnings("rawtypes")
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
		Scanner scan = new Scanner(new BufferedInputStream(System.in), "UTF-8");
		List<String> target = new ArrayList<>();
		while (scan.hasNext())
			target.add(scan.next());
		String[] a = new String[target.size()];
		scan.close();
		Sort sort = new SelectionTest();
		sort.sort(a);
		System.out.println(isSorted(a));
		System.out.println(sort.getClass().getSimpleName());
		show(a);
	}

}
