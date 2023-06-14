package n3e1ejercicioPerson;

import java.io.*;
import java.util.*;	
import java.util.stream.Collectors;

public class App {
	
	final static String filePath = "names.csv";	//leer el csv file con los nombres

	static List<Person> personList = new ArrayList<Person>();	//= ListFromCSVFile() añadiria aqui para que se compile la lista del csv a la lista aqui;
	
	public static void main(String[] args) {
		
		
		/*///añadiendo a la lista directamente desde el main para probar los metodos. 
		personList.add(new Person("Andreu", "Ballestero Llenas", "34835767J"));
		personList.add(new Person("Miquel", "Bayona Source", "48743957B"));
		personList.add(new Person("Guillem", "Capdevila Riu", "33957834J"));
		personList.add(new Person("Albert", "Carbonell Ferrer", "77364986R"));
		personList.add(new Person("Ferran", "Casas Coderch", "23047848P"));
		personList.add(new Person("Maria", "Casellas Fuste", "47102244S"));
		personList.add(new Person("Genius", "Ciutat Vendrell", "39718459N"));*/
		
		start();
		
	}
	
	static int inputNumber(String message) {				//request a number input
		Scanner input = new Scanner(System.in);
		System.out.println(message);
		return input.nextInt();
	}
	
	static String inputString(String message) {				//request a string input
		Scanner input = new Scanner(System.in);
		System.out.println(message);
		return input.nextLine();
	}
	
	
	//metodo para enseñar opciones
	private static int menu() {
		int initialMenu = inputNumber(
				"1.- Enter person.\r\n"
				+ "2.- Show people sorted by name (A-Z).\r\n"
				+ "3.- Show people sorted by name (Z-A).\r\n"
				+ "4.- Show people sorted by last name (A-Z).\r\n"
				+ "5.- Show people sorted by last name (Z-A).\r\n"
				+ "6.- Show people sorted by ID (1-9).\r\n"
				+ "7.- Show the people sorted by ID (9-1).\r\n"
				+ "0.- Exit.");
		return initialMenu;
	}
  
	//metodo para empezar el app
	public static void start() {
		boolean exitApp = false;
		do {
			int choice = menu();
			
			switch(choice) {
				case 1:		//enter a person
					addPerson(inputString("What´s the first name of the person?"), 
							inputString("What´s the surname of the person?"), 
							inputString("What´s the identity card number of the person?"));
					break;
				case 2:		// Show people sorted by name (A-Z).\r\n
					sortNameFirst();					
					break;
				case 3:		//Show people sorted by name (Z-A).\r\n"
					sortReverseNameFirst();
					break;
				case 4:		//Show people sorted by last name (A-Z).\r\n"
					sortNameLast();
					break;
				case 5:		//Show people sorted by last name (Z-A).\r\n"
					sortReverseNameLast();
					break;
				case 6:		//Show people sorted by ID (1-9).\r\n"
					sortID();
					break;	
				case 7:		//Show the people sorted by ID (9-1).\r\n"
					sortReverseID();
					break;
				case 0:		//exit program
					exitApp = true;
					break;
				default:
					System.out.println("That´s not one of the options, please try again.");
					break;
			}
		} while (!exitApp);
		System.out.println("Thank you for using the registry\\u2122.");
	}
	
	
	//metodo para cojer un archivo csv y convertirlo en una lista array
	public static List<Person> ListFromCSVFile() {
		List<Person> personList = new ArrayList<Person>();
        BufferedReader br = null;
 
        try {
        	
            // create file object
            File file = new File(filePath);
            // create BufferedReader object from the File
            br = new BufferedReader(new FileReader(file));
            String line = null;
            // read file line by line
            while ((line = br.readLine()) != null) {
                // split the line by " "
                String[] parts = line.split(",");
                // the csv file should have everything separated by commas.
                String nameFirst = parts[0].trim();
                String nameLast = parts[1].trim();
                String identityCard = parts[2].trim();
                // 
                if (!nameFirst.equals("") && !nameLast.equals("") && !identityCard.equals("")) {
                	personList.add(new Person(nameFirst, nameLast, identityCard));
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
  
            // Always close the BufferedReader
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception e) {
                }
            }
        }
        return personList;
    }
	
	private static int searchPersonName(String nameFirst, String nameLast) {		////buscar el nombre de un cliente
		int counter = 0;
		int index = -1;
		if (personList.size() != 0) {//me daba error si estaba vacio la lista, esto sirve como control si esta vacio
			while (!(personList.get(counter).getNameFirst().equalsIgnoreCase(nameFirst) && personList.get(counter).getNameLast().equalsIgnoreCase(nameLast)) && counter < personList.size()) {
				counter++;
			} if (personList.get(counter).getNameFirst().equalsIgnoreCase(nameFirst) && personList.get(counter).getNameLast().equalsIgnoreCase(nameLast)) {
				index = counter;
			}
		}
		return index;
	}
	
	private static int searchPersonID(String id) {
		int counter = 0;
		int index = -1;
		if (personList.size() != 0) {//me daba error si estaba vacio la lista, esto sirve como control si esta vacio
			while (personList.get(counter).getIdentityCard() != id && counter < personList.size()) {
				counter++;
			} if (personList.get(counter).getIdentityCard() == id) {
				index = counter;
			}
		}
		return index;
	}
	
	//añade nueva persona a la lista
	private static void addPerson(String nameFirst, String nameLast, String id) {
		int index = searchPersonName(nameFirst, nameLast);
		if (index == -1) {
			personList.add(new Person(nameFirst, nameLast, id));
		} else {
			System.out.println("This person is already in the registry.\r");
		}
	}
	
	//imprime lista por primer nombre
	private static void sortNameFirst() {
		System.out.println("The list or people sorted by first name A-Z are: ");
		System.out.println("___Name___ _____Surnames____ ___NIF___");
		if (personList.size() != 0) {
			Collections.sort(personList, new SortNameFirst());	
			for (ListIterator<Person> it = personList.listIterator(); it.hasNext(); ) {
				//personList.forEach(System.out::println);
				System.out.println(it.next());
			}
		} else {
			System.out.print("There are no people within the list.\r");
		}
	}	
	
	//imprime lista en inversa de nombres
	private static void sortReverseNameFirst() {
		System.out.println("The list or people sorted by first name in reverse Z-A are: ");
		System.out.println("___Name___ _____Surnames____ ___NIF___");
		if (personList.size() != 0) {
			Collections.sort(personList, new SortNameFirst());
			for (ListIterator<Person> it = personList.listIterator(personList.size()); it.hasPrevious(); ) {
				//sortedList.forEach(System.out::println);
				System.out.println(it.previous());
			}
		} else {
			System.out.print("There are no people within the list.\r");
		}
	}
	
	//imprime lista de apellidos
	private static void sortNameLast() {
		System.out.println("The list or people sorted by last name A-Z are: ");
		System.out.println("___Name___ _____Surnames____ ___NIF___");
		if (personList.size() != 0) {
			Collections.sort(personList, new SortNameLast());
			for (ListIterator<Person> it = personList.listIterator(); it.hasNext(); ) {
				//sortedList.forEach(System.out::println);
				System.out.println(it.next());
			}
			/*for (ListIterator<Person> it = personList.listIterator(); it.hasNext(); ) {
				System.out.println(it.next());
			}*/
		} else {
			System.out.print("There are no people within the list.\r");
		}
	}
	
	//imprime lista en inversa de apellidos
	private static void sortReverseNameLast() {
		System.out.println("The list or people sorted by last name in reverse z-A are: ");
		System.out.println("___Name___ _____Surnames____ ___NIF___");
		if (personList.size() != 0) {
			Collections.sort(personList, new SortNameLast());
			for (ListIterator<Person> it = personList.listIterator(personList.size()); it.hasPrevious(); ) {
				//sortedList.forEach(System.out::println);
				System.out.println(it.previous());
			}
			/*for (ListIterator<Person> it = personList.listIterator(); it.hasNext(); ) {
				System.out.println(it.next());
			}*/
		} else {
			System.out.print("There are no people within the list.\r");
		}
	}
	
	//imprime lista por dni
	private static void sortID() {
		System.out.println("The list or people sorted by their ID are: ");
		System.out.println("___Name___ _____Surnames____ ___NIF___");
		if (personList.size() != 0) {
			Collections.sort(personList, new SortID());
			for (ListIterator<Person> it = personList.listIterator(); it.hasNext(); ) {
				//sortedList.forEach(System.out::println);
				System.out.println(it.next());
			}
			/*for (ListIterator<Person> it = personList.listIterator(); it.hasNext(); ) {
				System.out.println(it.next());
			}*/
		} else {
			System.out.print("There are no people within the list.\r");
		}
	}
	
	//imprime lista en inversa de dni
	private static void sortReverseID() {
		System.out.println("The list or people sorted by their ID in reverse are: ");
		System.out.println("___Name___ _____Surnames____ ___NIF___");
		if (personList.size() != 0) {
			Collections.sort(personList, new SortID());
			for (ListIterator<Person> it = personList.listIterator(personList.size()); it.hasPrevious(); ) {
				//sortedList.forEach(System.out::println);
				System.out.println(it.previous());
			}
			/*for (ListIterator<Person> it = personList.listIterator(); it.hasNext(); ) {
				System.out.println(it.next());
			}*/
		} else {
			System.out.print("There are no people within the list.\r");
		}
	}

}


/*
 * Create an application capable of reading a CSV file. This file has 3 fields: 
 * first name, last name and ID, for each record. It is about sorting the people 
 * read from the file, by their first name, last name or ID. You can use whichever
 *  list you think is most appropriate.

The Person class has 3 attributes:

first name

surname

identity card


The main class has the following menu:

1.- Enter person.

2.- Show people sorted by name (A-Z).

3.- Show people sorted by name (Z-A).

4.- Show people sorted by last name (A-Z).

5.- Show people sorted by last name (Z-A).

6.- Show people sorted by ID (1-9).

7.- Show the people sorted by ID (9-1).

0.- Exit.


The program should list the people like the following example:

___Name___ ____Surnames___ __NIF__

Andreu 		Ballestero Llenas 34835767J

Miquel		 Bayona Source 48743957B

Guillem 	Capdevila Riu 33957834J

Albert 		Carbonell Ferrer 77364986R

Ferran 		Casas Coderch 23047848P

Maria 		Casellas Fuste 47102244S

Genius 		Ciutat Vendrell 39718459N
 * 
 * 
 * 
 */

