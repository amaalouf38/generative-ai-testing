package generativeai.chatGPT;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

public class ParallelAdd {

	static final int total_size = 1000000000;
	static long sum;
	static final int numThreads = Runtime.getRuntime().availableProcessors(); // Get the number of available processors
	static long[] sumPersThread = new long[numThreads];
	static final int chunkSize = (int) Math.ceil((double) total_size / numThreads);
	// static final ExecutorService executor =
	// Executors.newFixedThreadPool(numThreads); // Create a thread pool

	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
		sum1tototalSize();
		long duration = System.currentTimeMillis() - currentTimeMillis;
		System.out.println("Duartion serial Loop: " + duration);

		currentTimeMillis = System.currentTimeMillis();
		sumUsingParallelStreams();
		duration = System.currentTimeMillis() - currentTimeMillis;
		System.out.println("Duartion parallel streams: " + duration);

		currentTimeMillis = System.currentTimeMillis();
		sumUsingStreams();
		duration = System.currentTimeMillis() - currentTimeMillis;
		System.out.println("Duartion serial streams: " + duration);

		currentTimeMillis = System.currentTimeMillis();
		threadPoolExample();
		duration = System.currentTimeMillis() - currentTimeMillis;
		System.out.println("Duartion parallel threads: " + duration);

		currentTimeMillis = System.currentTimeMillis();
		chatGPTthreadPoolExample();
		duration = System.currentTimeMillis() - currentTimeMillis;
		System.out.println("Duartion parallel threads: " + duration);
	}

	public static void sum1tototalSize() {
		for (int i = 1; i <= total_size; i++)
			sum += i;
		System.out.println("Sum: " + sum);

		/*
		 * sum = 0;
		 * for (int i = 1; i <= numThreads; i++) {
		 * for (int j = (i - 1) * chunkSize; j < Math.min(i * chunkSize, total_size);
		 * j++) {
		 * // System.out.printf("%d ",j);
		 * sum += j;
		 * }
		 * // System.out.print("\n\n");
		 * }
		 * sum += total_size;
		 * 
		 * System.out.println("Sum: " + sum);
		 */

	}

	public static void sumUsingParallelStreams() {
		long sum = LongStream.rangeClosed(1, total_size).parallel() // Convert the stream to parallel stream
				.sum();

		System.out.println("Sum: " + sum);
	}

	public static void sumUsingStreams() {
		long sum = LongStream.rangeClosed(1, total_size).sum();

		System.out.println("Sum: " + sum);
	}

	public static void chatGPTthreadPoolExample() {
		// I asked ChatGPT to create a parallel execution plan for the task of adding
		// 10000 numbers together this is the code it came up with
		// code is not thread safe, causes data race on the shared variable sum and does
		// not produce the correct answer
		// does not compile at first because the lambda tries to change the effectively
		// local final variable sum
		// the execution plan does not make sense, it only makes sense to divide the
		// task evenly on the existing processors
		// the code is fixed in the next function

		ExecutorService executor = Executors.newFixedThreadPool(numThreads); //
		// Create a thread pool

		// int sum = 0;
		sum = 0;
		for (int i = 1; i <= 1000; i++) {
			final int num = i;
			executor.execute(() -> {
				// Add the number to the sum
				sum += num;
			});
		}

		executor.shutdown(); // Shutdown the executor

		try {
			executor.awaitTermination(10, TimeUnit.SECONDS); // Wait for tasks to complete
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Sum: " + sum);
	}

	public static void threadPoolExample() {

		class myTask implements Runnable {
			int task;

			public myTask(int task) {
				this.task = task;
			}

			@Override
			public void run() {
				for (int j = (task - 1) * chunkSize; j < Math.min(task * chunkSize, total_size); j++) {
					sumPersThread[task - 1] += j;
				}

			}
		}

		ExecutorService executor = Executors.newFixedThreadPool(numThreads); //
		// Create a thread pool

		for (int i = 1; i <= numThreads; i++)
			executor.execute(new myTask(i));

		executor.shutdown(); // Shutdown the executor

		try {
			executor.awaitTermination(10, TimeUnit.SECONDS); // Wait for tasks to complete
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		sum = 0;
		for (int i = 0; i < numThreads; i++)
			sum += sumPersThread[i];
		sum += total_size;

		System.out.println("Sum: " + sum);
	}

}
