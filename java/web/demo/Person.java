package web.demo;

public class Person {
	public String name;
	public int age;
	public Person(String a_name, int a_age)
	{
		this.name = a_name;
		this.age = a_age;
	}
	public String getName()
	{
		return this.name;
	}
	public int getAge()
	{
		return this.age;
	}
}
