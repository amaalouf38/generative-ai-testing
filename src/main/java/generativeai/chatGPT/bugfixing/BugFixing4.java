package generativeai.chatGPT.bugfixing;

public class BugFixing4 {

	public static void main(String[] args) {

		String result = "";
		for (int i = 0; i < 1000000; i++)
			result += String.format("%06d\n", i);
		System.out.println(result);

	}

}
