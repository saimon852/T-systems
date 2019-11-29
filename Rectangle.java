package shapes;

public class Rectangle extends Shape {

		private int size;
		private int size1;
		

		public Rectangle(int size, int x, int y, Color color, int size1) {
			super(x,y,color);
			this.size = size;
			this.size1 = size1;
		}

		public Rectangle(int size, int size1, int x, int y) {
			this(x, y, size, Color.BLACK, size1);

		}


		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

	
		
		public int getSize1() {
			return size1;
		}

		public void setSize1(int size1) {
			this.size1 = size1;
		}

		public void draw() {
			System.out.println("Rectangle x=" + getX() + ", y=" + getY() + ", size =" + size + ", size1 =" + size + "color" + getColor());

	}

}
