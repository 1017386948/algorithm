package fundamentals;

/*
 * ����ֲ���С�ĸ��arr����Ϊ1ʱ��arr[0]�Ǿֲ���С��arr�ĳ���ΪN(N>1)ʱ��
 * ���arr[0]<arr[1]����ôarr[0]�Ǿֲ���С�����arr[N-1]<arr[N-2]��
 * ��ôarr[N-1]�Ǿֲ���С�����0<i<N-1������arr[i]<arr[i-1]����arr[i]<arr[i+1]��
 * ��ôarr[i]�Ǿֲ���С��
 * ������������arr����֪arr�������������ڵ���������ȣ�дһ��������
 * ֻ�践��arr������һ���ֲ���С���ֵ�λ�ü��ɡ�
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
