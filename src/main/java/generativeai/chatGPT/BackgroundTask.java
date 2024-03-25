package generativeai.chatGPT;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class BackgroundTask {

	private static String resutls;

	public static void main(String[] args) {
		backGroudnTask1();
		backGroundTask2();
	}

	public static void backGroudnTask1() {
		// Create a cached thread pool
		ExecutorService executor = Executors.newCachedThreadPool();

		// Submit a task to the executor and obtain a Future object
		Future<String> future = executor.submit(BackgroundTask::backgroundTask);

		// Shutdown the executor when no longer needed
		executor.shutdown();

		// Continue executing other tasks on the main thread
		doOtherTasksOnMainThread();

		try {
			// Get the result from the background task
			String result = future.get();
			System.out.println("Result: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void backGroundTask2() {
		// Create a cached thread pool
		ExecutorService executor = Executors.newCachedThreadPool();

		// Submit a task to the executor
		executor.submit(BackgroundTask::backgroundTask);

		// Shutdown the executor when no longer needed
		executor.shutdown();

		// Continue executing other tasks on the main thread
		doOtherTasksOnMainThread();

		try {
			// Wait for all tasks to complete or a timeout occurs
			boolean terminated = executor.awaitTermination(10, TimeUnit.SECONDS);
			if (terminated) {
				// All tasks completed within the specified timeout
				// Perform any necessary post-processing
				System.out.println("Result: " + resutls);
			} else {
				// Timeout occurred before all tasks completed
				// Handle accordingly
			}
		} catch (InterruptedException e) {
			// Handle interrupted exception
		}

	}

	private static String backgroundTask() {
		System.out.println("Running backround tasks on backgourn thread: " + Thread.currentThread().getName());
		// Perform the background task of reading the file into memory
		Path filePath = Paths.get("file.txt");
		try {
			byte[] fileBytes = Files.readAllBytes(filePath.toAbsolutePath());

			// Return the result
			synchronized (BackgroundTask.class) {
				resutls = formatByteArrayToHex(fileBytes);
			}
			return resutls;

		} catch (Exception e) {
			e.printStackTrace();
			return "Error occurred while reading the file";
		}
	}

	private static void doOtherTasksOnMainThread() {
		// Perform other tasks on the main thread
		System.out.println("Running other tasks on main thread: " + Thread.currentThread().getName());
	}

	private static String formatByteArrayToHex(byte[] byteArray) {
		StringBuilder sb = new StringBuilder();
		for (byte b : byteArray) {
			sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}
}
