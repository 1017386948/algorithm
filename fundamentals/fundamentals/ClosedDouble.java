package fundamentals;

import java.util.Arrays;

import com.algs4.stdlib.In;

public class ClosedDouble {
	public static int index(double[] a) {
		Arrays.sort(a);
		int index = 0;
		if (a.length < 2)
			throw new IllegalArgumentException("array length must bigger than 2");
		double diff = Math.abs(a[1] - a[0]);
		for (int i = 0; i < a.length - 1; i++) {
			double d = Math.abs(a[i + 1] - a[i]);
			if (d < diff) {
				diff = d;
				index = i;
				System.out.println(d);
			}
		}
		return index;
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		String fileName = System.getProperty("user.dir")
				+ "\\resource\\AlgorithmsSedgewick-master\\1-Fundamentals\\1-4-AnalysisOfAlgorithms\\doubles.txt";
		double[] d = In.readDoubles(fileName);
		System.out.println(index(d));
	}
}
