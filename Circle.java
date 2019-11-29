package shapes;

public class Circle  extends Shape {

		private int size;
		

		public Circle(int size, int x, int y, Color color) {
			super(x,y,color);
			this.size = size;
	
		}

		public Circle(int size, int x, int y) {
			this(x, y, size, Color.BLACK);

		}


		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

	
	
		

		public void draw() {
			System.out.println("Circle x=" + getX() + ", y=" + getY() + ", size =" + size + " color" + getColor());

	}

}


