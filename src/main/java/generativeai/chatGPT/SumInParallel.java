package generativeai.chatGPT;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SumInParallel {

	static final int total_size = 1000000000;
	static int[] nums = new int[total_size];
	static int sum = 0;

	static {
		initializeNums();
	}

	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
		sum = sum1tototalSize();
		long duration = System.currentTimeMillis() - currentTimeMillis;
		System.out.println("Sum: " + sum);
		System.out.println("Duartion parallel: " + duration);

		currentTimeMillis = System.currentTimeMillis();
		sum = sum0tototalSize();
		duration = System.currentTimeMillis() - currentTimeMillis;
		System.out.println("Sum: " + sum);
		System.out.println("Duartion serial : " + duration);

	}

	public static void initializeNums() {
		for (int i = 0; i < total_size; i++)
			nums[i] = Math.abs(new Random().nextInt());
	}

	public static int sum0tototalSize() {
		sum = 0;
		for (int i = 0; i < total_size; i++)
			sum += nums[i];
		return sum;
	}
	/*
	 * public static int sum1tototalSize() {
	 * sum = 0;
	 * for (int i = 0; i < total_size; i++)
	 * sum += nums[i];
	 * return sum;
	 * }
	 */

	public static int sum1tototalSize() {
		final int numThreads = Runtime.getRuntime().availableProcessors();
		final int chunkSize = (int) Math.ceil((double) total_size / numThreads);
		int[] sumPerThread = new int[numThreads];

		class SumTask implements Runnable {
			int task;

			public SumTask(int task) {
				this.task = task;
			}

			@Override
			public void run() {
				for (int i = (task - 1) * chunkSize; i < Math.min(task * chunkSize, total_size); i++) {
					sumPerThread[task - 1] += nums[i];
				}
			}
		}

		ExecutorService executor = Executors.newFixedThreadPool(numThreads);

		for (int i = 1; i <= numThreads; i++) {
			executor.execute(new SumTask(i));
		}

		executor.shutdown();

		try {
			executor.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int sum = 0;
		for (int i = 0; i < numThreads; i++) {
			sum += sumPerThread[i];
		}

		return sum;
	}

}
