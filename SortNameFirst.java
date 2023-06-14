package n3e1ejercicioPerson;

import java.util.Comparator;

//clase comparator para sortear la lista por primer nombre

public class SortNameFirst implements Comparator<Person> {

	@Override
	public int compare(Person person1, Person person2) {
		return person1.getNameFirst().compareTo(person2.getNameFirst());
	}

}
