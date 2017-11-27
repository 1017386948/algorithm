package fundamentals;

/*
 * 定义局部最小的概念。arr长度为1时，arr[0]是局部最小。arr的长度为N(N>1)时，
 * 如果arr[0]<arr[1]，那么arr[0]是局部最小；如果arr[N-1]<arr[N-2]，
 * 那么arr[N-1]是局部最小；如果0<i<N-1，既有arr[i]<arr[i-1]又有arr[i]<arr[i+1]，
 * 那么arr[i]是局部最小。
 * 给定无序数组arr，已知arr中任意两个相邻的数都不相等，写一个函数，
 * 只需返回arr中任意一个局部最小出现的位置即可。
 */
public class LocalMinimum {
	public static int localMin(int[] a) {
		return localMin0(a, 0, a.length - 1);
	}

	private static int localMin0(int[] a, int fromIndex, int toIndex) {
		if (toIndex < fromIndex)
			return -1;
		else if (fromIndex == toIndex)
			return 0;
		else if (a[fromIndex] < a[fromIndex + 1])
			return fromIndex;
		else if (a[toIndex] < a[toIndex - 1])
			return toIndex;
		else if (a[a.length - 1] < a[a.length - 2])
			return a.length - 1;
		int mid;
		int left = fromIndex + 1;
		int right = toIndex - 1;
		while (left < right) {
			mid = (left + right) >>> 1;
			if (a[mid] > a[mid - 1])
				right = mid - 1;
			else if (a[mid] > a[mid + 1])
				left = mid + 1;
			else
				return mid;
		}
		return left;
	}

	public static void main(String[] args) {
		int[][] a = { { 0, 1, 2, 3, 4, 5 }, //
				{ 9, 7, 5, 1, 0, 2, 5, 11 }, //
				{ 25, 0, 15, 20, 44 } };
		for (int[] b : a)
			System.out.println(localMin(b));
	}

}
