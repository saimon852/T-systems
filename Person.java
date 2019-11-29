
public class Person {

	private int age;
	private String firstName;
	private String lastName;
	public String owns;
	private String znackaauta;
	private String dom;

	public Person(String firstName, String lastName, int age, String owns, String znackaauta, String dom) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.owns = owns;
		this.znackaauta = znackaauta;
		this.dom = dom;
	}

	public void introduce() {
		System.out.println(" Volam sa " + firstName + " " + lastName + " " + age + owns + " " + znackaauta + " " + dom);
		
		
	}

	public boolean maOrNema() {
		if (dom != " a mam dom")
			return true;
		else
			return false;
	}

	public String name() {
		return (firstName);
	}

	public boolean autoOrNema() {
		if (owns != " a mam auto")
			return true;
		else
			return false;
	}

	public String auto() {
		return (firstName);
	}

	public int getAge() {
		return age;
	}
}

