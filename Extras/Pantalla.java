package Extras;

import java.util.ArrayList;

import Modelos.Carta;

public class Pantalla {

	public static void mostrarPorPantalla(String s) {
		System.out.println(s);
		System.out.println("\n");
		
		System.out.println("Toque la barra espaciadore y luego Enter para continuar");
		
		Teclado.next();
	}
	public static<T extends Carta> void mostrarRecurso(ArrayList<T> c1) {
		
		for(T c :c1) {
			System.out.println(c.toString());
		}
	}
}