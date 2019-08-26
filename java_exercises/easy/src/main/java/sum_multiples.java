package src.main.java;
import java.io.Console;

class sum_multiples {
	public static void main(String args[]) {
		Console cli = System.console();
		if (cli == null) {
			System.err.println("No console.");
			System.exit(1);
		}
		int u_number = Integer.parseInt(cli.readLine("Give me the number at which to stop: "));
		System.out.println("Sum of multiples of 3 and 5 found in " + u_number + " = " + sum(u_number));
	}
	public static int sum(int n) {
		int final_sum = 0;
		for (int i = 1; i <= n; i++) {
			if ((i % 3 == 0) || (i % 5 == 0)) {
				System.out.println("Found one: " + i);
				System.out.print(final_sum + " + " + i + " = ");
				final_sum = final_sum + i;
				System.out.println(final_sum);
			}
		}
		return final_sum;
	}
}