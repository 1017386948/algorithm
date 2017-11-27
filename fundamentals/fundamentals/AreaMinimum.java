package fundamentals;

public class AreaMinimum {
	public static void areaMinimum(int[][] a) {
		int x = LocalMinimum.localMin(a[0]);
		int[] b = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			b[i] = a[i][x];
		}
		int y = LocalMinimum.localMin(b);
		System.out.println("(" + x + " , " + y + ")");
	}
	public static void main(String[] args) {
		int[][] a = {//
				{9, 2, -6, 2,}, //
				{0, -2, -7, 0}, //
				{-4, 1, -4, 1}, //
				{-1, 8, 0, -2}};
		areaMinimum(a);
	}
}
