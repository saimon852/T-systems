
public class trojuholnik {

	public static void main(String[] args) {

		for (int i = 1; i <= 5; i++) {
			System.out.println(i);
			printTriangle(i);
			System.out.println(i + "square");
			printSquare(i);
			System.out.println(i + "triangle2");
			printTriangle2(i);
			System.out.println(i + "triangle3");
			printTriangle3(i);
			System.out.println(i + "triangle4");
			printTriangle4(i);

		}
	}

	static void printSquare(int n) {
		for (int i = 1; i <= n; i++) {
			printStars(n);
			System.out.println("*");
		}

	}

	static void printTriangle(int n) {
		for (int i = 1; i <= n; i++) {
			printStars(i);
			System.out.println("*");
		}
	}

	static void printTriangle2(int n) {
		for (int i = n; i >= 1; i--) {
			printStars(i);
			System.out.print("\n");

		}

	}

	static void printTriangle3(int n) {
		for (int i = n; i >= 0; i--) {
			printSpaces(n);
			printStars(n - 1);
			System.out.println();
		}
	}

	static void printTriangle4(int n) {
		for (int i = n; i >= 0; i--) {
			printSpaces(i);
			printStars(n - i);
			printStars(n - i);
			System.out.println();

		}

	}

	static void printStars(int n) {
		printStrings(n, "*");
	}

	static void printSpaces(int n) {
		printStrings(n, " ");
	}

	static void printStrings(int n, String s) {
		for (int i = 1; i <= n; i++) {
			System.out.print(s);
		}

	}
}