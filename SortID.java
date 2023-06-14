package n3e1ejercicioPerson;

import java.util.Comparator;

//clase comparator para sortear la lista por dni

public class SortID implements Comparator<Person>{
	
	@Override
	public int compare(Person person1, Person person2) {
		return person1.getIdentityCard().compareTo(person2.getIdentityCard());
	}

}
