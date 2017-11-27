package exercises;

import java.util.Arrays;

public class FairElection {
	private static char[] regulation = "RWQOJMVAHBSGZXNTCIEKUPDYFL".toCharArray();
	private static int[] order;
	static {
		int n = regulation.length;
		order = new int[n];
		for (int i = 0; i < n; i++) {
			int index = regulation[i] - 'A';
			order[index] = i;
		}
	}

	private static int getOrder(char c) {
		int index = Character.toUpperCase(c) - 'A';
		assert (index >= 0 && index < 26);
		return order[index];
	}

	private static class Participant implements Comparable<Participant> {
		String[] name;

		public Participant(String name) {
			this.name = name.split(" ");
		}

		@Override
		public int compareTo(Participant o) {
			int i = name.length;
			int j = o.name.length;
			int k = Math.min(i, j);
			int cmp = 0;
			for (int l = 0; l < k; l++) {
				int ii = name[l].length();
				int jj = o.name[l].length();
				int kk = Math.min(ii, jj);
				for (int ll = 0; ll < kk; ll++) {
					cmp = getOrder(name[l].charAt(ll)) - getOrder(o.name[l].charAt(ll));
					if (cmp != 0)
						return cmp;
				}
				if (ii - jj != 0)
					return ii - jj;
			}
			return i - j;
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			for (String segment : name)
				sb.append(segment).append(" ");
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		}

	}

	public static void main(String[] args) {
		String[] names = { //
				"Rickard Stark", "Lyanna Stark", "Jon Snow", //
				"Master Luwin", "Catelyn Tully", "Ser Rodrik Cassel", //
				"Queen Cersei", "Tyrion Imp", "Ser Jaime", //
				"Robert Baratheon", "Maester Aemon" };
		int n = names.length;
		Participant[] participants = new Participant[n];
		for (int i = 0; i < n; i++)
			participants[i] = new Participant(names[i]);
		Arrays.sort(participants);
		System.out.println(Arrays.deepToString(participants));

	}
}
