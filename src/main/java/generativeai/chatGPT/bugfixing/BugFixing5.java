package generativeai.chatGPT.bugfixing;

public class BugFixing5 {
	public static void main(String[] args) {
		f(0);
	}

	public static int f(int i) {
		if (i < 0) {
			throw new IllegalArgumentException("i should not be negative");
		} else if (i == 0) {
			return 0;
		} else {
			return 1;
		}

		// return 1;

	}

}
