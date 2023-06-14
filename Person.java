package n3e1ejercicioPerson;

import java.util.Objects;

public class Person {

	private String nameFirst;
	private String nameLast;
	private String identityCard;
	
	public Person(String nameFirst, String nameLast, String identityCard) {
		this.nameFirst = nameFirst;
		this.nameLast = nameLast;
		this.identityCard = identityCard;
	}
	
	public String getNameFirst(){
		return nameFirst;
	}
	
	public String getNameLast(){
		return nameLast;	
	}
	
	public String getIdentityCard(){
		return identityCard;
	}
	
	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}
	
	public void setNameLast(String nameLast) {
		this.nameLast = nameLast;
	}
	
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(identityCard, nameFirst, nameLast);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Person other = (Person) obj;
		return Objects.equals(identityCard, other.identityCard) && Objects.equals(nameFirst, other.nameFirst)
				&& Objects.equals(nameLast, other.nameLast);
	}
	
	public int compare(Person p1, Person p2) {
		return p1.getNameFirst().compareTo(p2.getNameFirst());
	}

	public String toString() {
		return nameFirst + " " + nameLast + " " + identityCard + "\r";
	}
	
	/*
	 * 
	 * 
	 * 
	 * public class NameComparator implements Comparator<Person>
{
    public int compare(Person o1, Person o2)
    {
       return o1.getName().compareTo(o2.getName());
   }
}

public class AgeComparator implements Comparator<Person>
{
    public int compare(Person o1, Person o2)
    {
        return o1.getAge().compareTo(o2.getAge());
    }
}

public class CountryComparator implements Comparator<Person>
{
    public int compare(Person o1, Person o2)
    {
        return o1.getCountry().compareTo(o2.getCountry());
    }
}
	 * 
	 * 
	 * Collections.sort(personList, new NameComparator());
Collections.sort(personList, new AgeComparator());
Collections.sort(personList, new CountryComparator());
	 * */
	
}