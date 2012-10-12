package org.aquamethods.fashbook.experiments;

import java.util.Comparator;

public class Person implements Comparable {
	private String firstName;
	private int personId;

	public Person(String firstName, int id) {
		super();
		this.firstName = firstName;
		this.personId = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int compareTo(Object o) {
		Person p = (Person) o;
		return this.getPersonId() - p.getPersonId();
	}

	public static Comparator<Person> PersonNameComparator = new Comparator<Person>() {

		public int compare(Person p1, Person p2) {

			String pName1 = p1.getFirstName().toUpperCase();
			String pName2 = p2.getFirstName().toUpperCase();

			// ascending order
			return pName1.compareTo(pName2);

			// descending order
			// return fruitName2.compareTo(fruitName1);
		}

	};

}
