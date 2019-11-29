
public class Test {

	public static void main(String[] args) {
//		Dom h1 = new Dom("zlty ", "doggy ", 5);
//		h1.describe();
//		Dom h2 = new Dom("zeleny ", "budy ", 4);
//		h2.describe();

		Person p1 = new Person("Pilan", "Knazko mam", 35, " a mam auto", "Fiat", " a mam dom");
		p1.introduce();

		Person p2 = new Person("Marcel", "Porzvaz mam", 28, " a mam auto", "" ," a nemam dom");
		p2.introduce();
		
		Person p3 = new Person("Samson", "Hrivny mam", 35, " a mam auto", "Citroen", " a nemam dom");
		p3.introduce();

		Person p4 = new Person("Lukas", "Kudrna mam", 28, " a mam auto", "Ferarri " ," a mam dom");
		p4.introduce();
		
		Person p5 = new Person("Lubo", "Kotlar mam", 35, "a nemam auto", "", "a mam dom");
		p5.introduce();
		


		Person[] people = { p1, p2, p3, p4, p5 };

	for (int i = 0; i < people.length; i++) {
		if ( people[i].maOrNema())
		System.out.println(people[i].name());
	}
	
	for (int i = 0; i < people.length; i++) {
if (people[i].autoOrNema())
	System.out.println(people[i].name());

	}
	}
		
		public static void SortPersons(Person[] people) {
			for (int i = 0; i < people.length; i++)
				for (int j = 1 ; j < people.length - 1 ; j++) 
				 if (people[j].getAge() > people[j+1].getAge()) {
					 Person temp = people [j];
					 people[j] = people[j+1];
							 people[j+1] = temp;
	

//		car c1 = new car("Cintroen", 46647);
//		c1.auto();
//		car c2 = new car("Tico", 46427);
//		c2.auto();
				 }
	}
		
}