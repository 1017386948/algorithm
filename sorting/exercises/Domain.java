package exercises;

import java.util.Arrays;

public class Domain implements Comparable<Domain> {
	private String url;

	public Domain(String url) {
		this.url = url;
	}

	@Override
	public int compareTo(Domain o) {
		String[] ths = url.split("\\.");
		String[] other = o.url.split("\\.");
		int i = ths.length;
		int j = other.length;
		int k = Math.min(i, j);
		for (int l = 0; l < k; l++) {
			int cmp = ths[i - l - 1].compareTo(other[j - l - 1]);
			if (cmp != 0)
				return cmp;
		}
		return i - j;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		String[] ths = url.split("\\.");
		for (int i = ths.length - 1; i >= 0; i--)
			sb.append(ths[i]).append(".");
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public static void main(String[] args) {
		Domain[] urls = { new Domain("m.baidu.com"), //
				new Domain("hehe.baidu.com"), //
				new Domain("you.m.baidu.com"), //
				new Domain("sina.com"), //
				new Domain("baidu.com"), //
				new Domain("m.sina.com") };
		Arrays.sort(urls);
		System.out.println(Arrays.deepToString(urls));
	}
}