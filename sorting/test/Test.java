package test;

public class Test {
	public static void main(String[] args) {
		// line(0, 0, 20, 30);
		System.out.println(tableSizeFor(Integer.MIN_VALUE + 1));
	}

	static double sqrt(double c) {
		if (c < 0)
			return Double.NaN;
		double t = c;
		double err = 1e-15;
		while (Math.abs(t - c / t) > err)
			t = (t + c / t) / 2.0;// ţ�ٵ�����
		return t;
	}

	private static final int tableSizeFor(int c) {
		int n = c - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return n;
	}

}
