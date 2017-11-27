package fundamentals;

import java.util.Arrays;

import com.algs4.stdlib.In;

public class BinarySearch {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		args[0] = System.getProperty("user.dir") + args[0];
		int[] a = In.readInts(args[0]);
		Arrays.sort(a);
		System.out.println(a[393]);
		System.out.println(binarySearch(a, -208341));
	}

	public static int binarySearch(int[] a, int key) {
		return binarySearch0(a, 0, a.length, key);
	}

	private static int binarySearch0(int[] a, int fromIndex, int toIndex, final int key) {
		int low = fromIndex;
		int high = toIndex - 1;
		while (low <= high) {
			int mid = (low + high) >>> 1;
			int midVal = a[mid];
			if (midVal < key)
				low = mid + 1;
			else if (midVal > key)
				high = mid - 1;
			else
				return mid;
		}
		return -(low + 1);
	}
}
