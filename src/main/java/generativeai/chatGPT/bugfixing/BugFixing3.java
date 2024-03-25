package generativeai.chatGPT.bugfixing;

import java.util.List;
import java.util.stream.Stream;

public class BugFixing3 {
	public static void main(String[] args) {
		System.out.print(generateList(5, "echos"));
	}

	public static List<String> generateList(int limit, String literal) {
		return Stream.generate(() -> literal).limit(limit).toList();
	}
}
