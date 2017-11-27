package exercises;

import java.util.Arrays;
import java.util.Random;

public class SPT {
	public static void main(String[] args) {
		Random rand = new Random(System.currentTimeMillis());
		int N = 10;
		Task[] tasks = new Task[N];
		for (int i = 0; i < N; i++)
			tasks[i] = new Task("task" + i, rand.nextInt(1000));
		Arrays.sort(tasks);
		System.out.println(Arrays.deepToString(tasks));
	}
}

class Task implements Comparable<Task> {
	private String name;
	private long during;

	public Task() {
		this.name = "default_name";
		this.during = 0;
	}

	public Task(String name, long during) {
		this.name = name;
		this.during = during;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDuring() {
		return during;
	}

	public void setDuring(long during) {
		this.during = during;
	}

	@Override
	public String toString() {
		return name + ": " + during;
	}

	@Override
	public int compareTo(Task o) {
		return (int) (during - o.during);
	}
}
