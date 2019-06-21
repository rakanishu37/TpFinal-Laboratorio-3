package Extras;

import java.util.Scanner;

public class Teclado {
	private static Scanner s = new Scanner(System.in);
	
	public static int nextInt() {
		
		int dato= s.nextInt();
		
		return dato;
	}
	
	public static void next() {
		
		s.nextLine();
		
		
		
	}
}
