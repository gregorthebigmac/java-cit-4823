package src.main.java;
import java.io.Console;

class sum_two_nums {
	public static void main(String args[]) {
		Console cli = System.console();
		if ( cli == null ) {
			System.err.println("No console.");
			System.exit(1);
		}
		Integer a, b;
		System.out.println("Give me two integers to add.");
		a = Integer.parseInt(cli.readLine("Integer 1: "));
		b = Integer.parseInt(cli.readLine("Integer 2: "));
		System.out.println(a + " + " + b + " = " + sum(a, b));
	}
	public static Integer sum(Integer i, Integer j) {
		return i+j;
	}
}
