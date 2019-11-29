
public class poltrojuhlonik {

	public static void main(String[] args) {
		int u = 6, n = 0;
		for (int i = 1; i <= u; i++, n = 0) {
			for (int o = 1; o <= u - i; o++) {
				System.out.print("  ");
			}
			while (n != 2 * i - 1) {
				System.out.print("* ");
				n++;
			}
			System.out.println();
		}
	}
}