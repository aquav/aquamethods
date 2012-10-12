package org.aquamethods.fashbook.experiments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SortingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ArrayList list = new ArrayList();

		list.add(new Person("Ananad",1));
		list.add(new Person("Vishal",5));
		list.add(new Person("Neha",3));
		list.add(new Person("Chirag",6));
		list.add(new Person("xox",2));
		
		
		//list.add(new Student("B",2));
		//list.add(new Student("D",4));

		// To sort an Object by its property, you have to make the Object
		// implement the Comparable interface and override the compareTo()
		// method
		Collections.sort(list);

		for (Object o : list) {

			if (o instanceof Person) {
				System.out.println(((Person) o).getFirstName() + " :: " +((Person) o).getPersonId() );
			}
			if (o instanceof Student) {
				System.out.println(((Student) o).getFirstName());
			}
		}

		Collections.sort(list, Person.PersonNameComparator);
		System.out.println("\nsorted using compaartor -- ");
		for (Object o : list) {

			if (o instanceof Person) {
				System.out.println(((Person) o).getFirstName() + " :: " +((Person) o).getPersonId() );
			}
			if (o instanceof Student) {
				System.out.println(((Student) o).getFirstName());
			}
		}
		
	}

}
