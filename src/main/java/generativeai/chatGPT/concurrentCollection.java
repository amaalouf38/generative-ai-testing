package generativeai.chatGPT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class concurrentCollection {

	private static final long sleeptime = 10;
	private static final long numthreads = 3;
	private static final List<String> syncElements = new Vector<>();
	private static final List<String> concurrentlist = new CopyOnWriteArrayList<>();
	private static final List<String> list = new ArrayList<>();
	// private static final List<String> synchronizedList =
	// Collections.synchronizedList(list);
	private static final List<String> synchronizedList = list;

	static {
		concurrentlist.add("Element 1");
		concurrentlist.add("Element 2");
		syncElements.add("Element 1");
		syncElements.add("Element 2");
		synchronizedList.add("Element 1");
		synchronizedList.add("Element 2");
	}

	public static void main(String[] args) {

		long currentTimeMillis = System.currentTimeMillis();
		concurrentlistList();
		long duration = System.currentTimeMillis() - currentTimeMillis;
		System.out.println("Duartion concurrentlistList: " + duration);

		currentTimeMillis = System.currentTimeMillis();
		synchronizedListList();
		duration = System.currentTimeMillis() - currentTimeMillis;
		System.out.println("Duartion synchronizedListList: " + duration);

	}

	public static void concurrentlistList() {
		Runnable r1 = () -> {
			for (String s : concurrentlist) {
				try {
					Thread.currentThread().sleep(sleeptime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.printf("first pass %s %s \n", s, Thread.currentThread().getName());
			}

			concurrentlist.add("Element 3");// + Thread.currentThread().getName());

			for (String s : concurrentlist) {
				try {
					Thread.currentThread().sleep(sleeptime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.printf("second pass %s %s \n", s, Thread.currentThread().getName());
			}

		};

		List<Thread> threadList = new ArrayList<>();
		for (int i = 0; i < numthreads; i++) {
			Thread t = new Thread(r1);
			t.start();
			threadList.add(t);
		}

		for (Thread t : threadList)
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

	}

	public static void synchronizedListList() {
		Runnable r0 = () -> {
			synchronized (synchronizedList) {
				for (String s : synchronizedList) {
					try {
						Thread.currentThread().sleep(sleeptime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.printf("first pass %s %s \n", s, Thread.currentThread().getName());
				}

				synchronizedList.add("Element 3");// + Thread.currentThread().getName());

				for (String s : synchronizedList) {
					try {
						Thread.currentThread().sleep(sleeptime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.printf("second pass %s %s \n", s, Thread.currentThread().getName());
				}
			}

		};

		List<Thread> threadList = new ArrayList<>();
		for (int i = 0; i < numthreads; i++) {
			Thread t = new Thread(r0);
			t.start();
			threadList.add(t);
		}

		for (Thread t : threadList)
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}
