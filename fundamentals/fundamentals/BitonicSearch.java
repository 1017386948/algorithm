package fundamentals;

public class BitonicSearch {
	public static int bitonicSearch(int[] a, int key) {
		int max = LocalMaximum(a);
		int index = binarySearchAsc(a, 0, max, key);
		return index < 0 ? binarySearchDesc(a, max, a.length, key) : index;
	}

	private static int LocalMaximum(int[] a) {
		if (a.length == 1)
			return 0;
		if (a[0] > a[1])
			return 0;
		if (a[a.length - 1] > a[a.length - 2])
			return a.length - 1;
		int mid;
		int left = 1;
		int right = a.length - 2;
		while (left < right) {
			mid = (left + right) >>> 1;
			if (a[mid] < a[mid - 1])
				right = mid - 1;
			else if (a[mid] < a[mid + 1])
				left = mid + 1;
			return mid;
		}
		return left;

	}

	private static int binarySearchAsc(int[] a, int fromIndex, int toIndex,
			int key) {
		int left = fromIndex, right = toIndex - 1;
		int mid;
		while (left <= right) {
			mid = (left + right) >>> 1;
			int midVal = a[mid];
			if (midVal < key)
				left = mid + 1;
			else if (midVal > key)
				right = mid - 1;
			else
				return mid;
		}
		return -(left + 1);
	}
	private static int binarySearchDesc(int[] a, int fromIndex, int toIndex,
			int key) {
		int left = fromIndex, right = toIndex - 1;
		int mid;
		while (left <= right) {
			mid = (left + right) >>> 1;
			int midVal = a[mid];
			if (midVal > key)
				left = mid + 1;
			else if (midVal < key)
				right = mid - 1;
			else
				return mid;
		}
		return -(left + 1);
	}

	public static void main(String[] args) {
		int[] a = {-31, -12, 0, 4, 20, 11, 8, -22, -100};
		System.out.println(bitonicSearch(a, -22));
	}

}
