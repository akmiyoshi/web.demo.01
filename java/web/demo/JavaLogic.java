package web.demo;

import java.util.List;
import java.util.ArrayList;

public class JavaLogic {
	public static List<JavaPerson> getPersons()
	{
		List<JavaPerson> result = new ArrayList<JavaPerson>();
		result.add(new JavaPerson("Tanaka(田中)", 11));
		result.add(new JavaPerson("Suzuki(鈴木)", 22));
		return result;
	}
}
