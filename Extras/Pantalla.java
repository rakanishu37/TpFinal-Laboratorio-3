package Extras;

import java.util.ArrayList;

import Modelos.Carta;

public class Pantalla {

	public static void mostrarPorPantalla(String s) {
		System.out.println(s);
		
		
		System.out.println("Presione una tecla y luego enter");
		
		Teclado.next();
	}
	public static<T extends Carta> void mostrarRecurso(ArrayList<T> c1) {
		
		for(T c :c1) {
			mostrarPorPantalla((c.toString()));
		}
	}
}