
public class object {
	private String firstName;
	
	private String lastName;
	
	private int age;

public object( String firstName, String lastName, int age) {
	this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
}
public void introduce() {
	System.out.println("Hallo! i am " + firstName+" "+lastName);
}
}
