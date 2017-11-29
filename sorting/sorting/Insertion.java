package sorting;

import static sorting.Example.exch;
import static sorting.Example.isSorted;
import static sorting.Example.less;
import static sorting.Example.show;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Insertion implements Sort {

	@SuppressWarnings("rawtypes")
	@Override
	public void sort(Comparable[] a) {
		for (int i = 1; i < a.length; i++)
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
				exch(a, j, j - 1);
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedInputStream(System.in), "UTF-8");
		List<String> target = new ArrayList<>();
		while (scan.hasNext())
			target.add(scan.next());
		Sort sort = new Insertion();
		String[] a = new String[target.size()];
		target.toArray(a);
		sort.sort(a);
		System.out.println(isSorted(a));
		show(a);
		scan.close();
	}
}
