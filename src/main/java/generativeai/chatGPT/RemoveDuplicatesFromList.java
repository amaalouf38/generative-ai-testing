package generativeai.chatGPT;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;
import java.util.List;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesFromList {

	public static void main(String[] args) {
		// Sample List with duplicate Persons
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("John", 25));
		personList.add(new Person("Alice", 30));
		personList.add(new Person("John", 25, 1)); // Duplicate
		personList.add(new Person("Bob", 40));
		personList.add(new Person("Alice", 30, 1)); // Duplicate

		// Print the unique Persons
		System.out.println(removeDuplicates(personList));
		System.out.println(removeDuplicates2(personList));
		System.out.println(removeDuplicates3(personList));

	}

	public static List<Person> removeDuplicates2(List<Person> personList) {
		Map<Person, Boolean> uniqueMap = new HashMap<>();

		for (Person person : personList) {
			uniqueMap.put(person, true);
		}

		return new ArrayList<>(uniqueMap.keySet());
	}

	public static List<Person> removeDuplicates3(List<Person> personList) {
		Set<Person> uniqueMap = new HashSet<>();

		for (Person person : personList) {
			uniqueMap.add(person);
		}

		return new ArrayList<>(uniqueMap);
	}

	public static List<Person> removeDuplicates(List<Person> personList) {
		List<Person> uniquePersons = new ArrayList<>();

		for (int i = 0; i < personList.size(); i++) {
			Person currentPerson = personList.get(i);
			boolean isDuplicate = false;

			for (int j = 0; j < i; j++) {
				Person comparisonPerson = personList.get(j);

				if (currentPerson.equals(comparisonPerson)) {
					isDuplicate = true;
					break;
				}
			}

			if (!isDuplicate) {
				uniquePersons.add(currentPerson);
			}
		}

		return uniquePersons;
	}
}

class Person {
	private String name;
	private int age;
	private int order;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Person(String name, int age, int order) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getOrder() {
		return order;
	}

	@Override
	public String toString() {
		return String.format("name : %s age: %d order: %d ", name, age, order);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Person person = (Person) obj;
		return age == person.age && name.equals(person.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode() + age;
	}
}
