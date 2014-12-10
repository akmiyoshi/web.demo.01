package web.demo;

import java.util.List;
import java.util.ArrayList;

public class Logic {
	public static List<Person> getPersons()
	{
		List<Person> result = new ArrayList<Person>();
		result.add(new Person("Tanaka(田中)", 11));
		result.add(new Person("Suzuki(鈴木)", 22));
		return result;
	}
}
