package n3e1ejercicioPerson;

import java.util.Comparator;

//clase comparator para sortear la lista por apellido

public class SortNameLast implements Comparator<Person>{
	
	@Override
	public int compare(Person person1, Person person2) {
		return person1.getNameLast().compareTo(person2.getNameLast());
	}

}
