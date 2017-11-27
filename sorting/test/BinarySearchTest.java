package test;

import java.util.Arrays;
import java.util.Random;

public class BinarySearchTest {
	public static int binarySearch(int[] a, int key) {
		return binarySearch0(a, 0, a.length - 1, key);
	}
	private static int binarySearch0(int[] a, int begin, int end, int key) {
		while (begin <= end) {
			int mid = (begin + end) >> 1;
			if (a[mid] < key) {
				begin = mid + 1;
			} else if (a[mid] > key) {
				end = mid - 1;
			} else {
				return mid;
			}
		}
		return -(begin + 1);
	}
	public static void main(String[] args) {
		Random rand = new Random();
		int[] a = new int[10];
		for (int i = 0; i < a.length; i++)
			a[i] = rand.nextInt(20);
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		int key = rand.nextInt(20);
		int position = binarySearch(a, key);
		System.out.print(key + " is in the position of " + position);
		System.out.println("\n" + a[position >= 0 ? position : -1 - position]);
	}
}
