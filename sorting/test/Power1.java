package test;

public class Power1 {
	public static int power1(int m, int n) {
		int t = m;
		int result = 1;
		for (int i = 1; i <= n; i <<= 1) {
			if ((n & i) > 0)
				result *= t;
			t *= t;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(power1(2, 20));
	}
}
