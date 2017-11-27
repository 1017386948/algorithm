package exercises;

import java.util.Arrays;

public class Version implements Comparable<Version> {

	private int[] version;

	public Version(String ver) {
		String[] before = ver.split("\\.");
		int N = before.length;
		version = new int[N];
		for (int i = 0; i < N; i++)
			version[i] = Integer.parseInt(before[i]);
	}

	@Override
	public int compareTo(Version v) {
		int n = Math.min(this.version.length, v.version.length);
		for (int i = 0; i < n; i++) {
			if (this.version[i] != v.version[i])
				return this.version[i] - v.version[i];
		}
		return this.version.length - v.version.length;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int segment : version) {
			sb.append(segment + ".");
		}
		return sb.toString().substring(0, sb.length() - 1);
	}

	public static void main(String[] args) {
		Version[] versions = new Version[]{//
				new Version("115.1.1"), //
				new Version("115.10.1"), //
				new Version("115.10.2"), //
				new Version("114.6.1"), //
				new Version("117.0.1"),//
		};
		Arrays.sort(versions);
		System.out.println(Arrays.deepToString(versions));
	}

}
