package shapes;

public class TestShape {

	public static void main(String[] args) {

		Square s1 = new Square(25, 25, 50);
//		s1.draw();
		Square s2 = new Square(15, 55, 50, Color.GREEN);
//		s2.draw();
		Rectangle t1 = new Rectangle(15, 25, 12, 52);
//		t1.draw();
		Circle c1 = new Circle(10, 20, 14);
//		c1.draw();
	

	Shape[] shapes = {s1,s2,t1,c1};
	
	for(Shape shape:shapes)
		shape.draw();
	}
}