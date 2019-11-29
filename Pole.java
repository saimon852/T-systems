
public class Pole {

	public static void main(String[] args) {

		int[] numbers = { 1, 2, 3, 8, 5, 6 };
		System.out.println("Minimum Value is" + getMin(numbers));
		System.out.println("Maximum Value is" + getMax(numbers));
		System.out.println("Sumation Value is" + getSum(numbers));
		System.out.println("Average Value is" + getAvg(numbers));
		Sort(numbers);
		for (int i =0; i< numbers.length;i++) 
		System.out.println(numbers[i]);
	}

	public static int getMax(int[] numbers) {

		int maxValue = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] > maxValue) {
				maxValue = numbers[i];
			}
		}
		return maxValue;
	}

	public static int getMin(int[] numbers) {
		int minValue = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] < minValue) {
				minValue = numbers[i];
			}
		}
		return minValue;
	}

	public static int getSum(int[] numbers) {
		int Sum = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			Sum += numbers[i];
		}
		return Sum;
	}

	public static double getAvg(int[] numbers) {
		int Avg = 0;
		for (int i = 0; i < numbers.length; i++) {
			Avg = Avg + numbers[i];

		}
		return Avg / (double) numbers.length;
	}

	public static void Sort(int[] numbers) {
		for (int i = 1; i < numbers.length; i++)
			for (int j = 0; j < numbers.length - 1; j++) 
			 if (numbers[j] > numbers[j+1]) {
				 int temp = numbers [j];
				 numbers[j] = numbers[j+1];
						 numbers[j+1] = temp;

			}
	}
}
