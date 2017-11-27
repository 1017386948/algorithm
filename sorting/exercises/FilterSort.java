package exercises;

import static sorting.Example.exch;

import java.util.Arrays;
import java.util.Random;

public class FilterSort {
	public static int dedup(String[] a) {
		sort(a);
		System.out.println(Arrays.deepToString(a));
		int i = 0, j = 0, k = 0;
		while (++j < a.length) {
			if (!a[i].equals(a[j])) {
				System.out.print(j - i + " ");
				a[k++] = a[i];
				i = j;
			}
		}
		System.out.println(a.length - i);
		a[k] = a[i];
		return k;
	}

	private static void sort(String[] a) {
		sort(a, 0, a.length - 1);
	}

	private static void sort(String[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int lt = lo, i = lo + 1, gt = hi;
		while (i <= gt) {
			int cmp = a[i].compareTo(a[lt]);
			if (cmp < 0)
				exch(a, lt++, i++);
			else if (cmp > 0)
				exch(a, i, gt--);
			else
				i++;
		}
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
	}

	private static boolean isSorted(Comparable[] a, int k) {

		for (int i = 1; i <= k; i++)
			if (a[i].compareTo(a[i - 1]) < 0)
				return false;
		return true;
	}

	public static void main(String[] args) {
		Random rand = new Random(System.currentTimeMillis());
		String[] a = new String[20];
		for (int i = 0; i < a.length; i++)
			a[i] = (Character.toString((char) (rand.nextInt(26) + 'a')));
		System.out.println(Arrays.deepToString(a));
		int k = dedup(a);
		System.out.println(Arrays.deepToString(a));
		System.out.println(k);
		System.out.println(isSorted(a, k));
	}
}
