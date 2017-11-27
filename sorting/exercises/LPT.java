package exercises;

import static exercises.FindKMax.intArrayString;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class LPT {
	public static void lpt(Task[] tasks, int M) {
		assert (M > 0);
		int[] cores = new int[M];
		Arrays.sort(tasks, Comparator.reverseOrder());
		MinPQ pq = new MinPQ(cores);
		for (Task t : tasks) {
			pq.push(t);
		}
		System.out.println(intArrayString(cores));
	}

	public static void main(String[] args) {
		int N = 10;
		int k = 4;
		Random rand = new Random(System.currentTimeMillis());
		Task[] tasks = new Task[N];
		for (int i = 0; i < N; i++)
			tasks[i] = new Task("task" + i, rand.nextInt(1000) + 1);
		System.out.println(Arrays.toString(tasks));
		lpt(tasks, k);
	}

}

class MinPQ {
	private int[] pq;

	public MinPQ(int[] cores) {
		this.pq = cores;
	}

	public void push(Task t) {
		pq[0] += t.getDuring();
		sink();
	}

	private void sink() {
		int i = 0, M = pq.length - 1;
		while (i < (M + 1) >> 1) {
			int j = 2 * i + 1;
			if (j + 1 <= M && pq[j + 1] < pq[j])
				j++;
			if (pq[i] <= pq[j])
				break;
			int temp = pq[i];
			pq[i] = pq[j];
			pq[j] = temp;
			i = j;
		}
	}

	@Override
	public String toString() {
		return intArrayString(pq);
	}
}
