package generativeai.chatGPT.bugfixing;

public class BugFixing6 {
	public static void main(String[] args) {
		f(0);
	}

	public static int f(int i) {
		if (i < 0) {
			return 0;
		} else {
			return f(i--);
		}
	}

	/*
	 * public static int f(int i) {
	 * if (i < 0) {
	 * return 0;
	 * } else {
	 * return f(i - 1);
	 * }
	 * }
	 */
}
