package Modelos;

import java.util.HashMap;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

import Interfaces.moverCarta;

public class Mazo implements moverCarta<Carta> {
	public static final int MAX_CARTAS = 40;	
	private Vector<Carta> cartas;

	public Mazo() {
		super();
		cartas= crearMazo();//
		/*(recibe la composicion del mazo en forma de json) 
		 tiene adentro el archivo fuente con todas las cartas
		 * 
		 */
	}
	
	private void setCartas(Vector<Carta> mazo) {
		cartas= mazo;
	}
	
	@Override
	public void insertarCarta(Carta cartaAInsertar) {
		cartas.add(cartaAInsertar);
		// cartas.push(cartaAInsertar);
	}

	@Override
	public Carta extraerCarta(int indice) {		
		// return cartas.pop();
		return cartas.remove(indice);
	}
	
	/**
	 * Mezcla el mazo
	 */
	public void mezclar() {
		int cartasRestantes = MAX_CARTAS-1;		
		Vector<Carta> temporal = null;
		Random random = new Random();
		while(cartasRestantes>= 0) {
			//Elijo una carta al azar entre 0 y las cartas restantes(39 porque 40 es posicion invalida)
			//y decrementara hasta 0
			temporal.add( cartas.get(random.nextInt(cartasRestantes)) );
			cartasRestantes--;
		}
		setCartas(temporal);
	}

	/**
	 * 
	 * @return true si el mazo ya no tiene cartas
	 */
	public boolean estaVacio() {
		return cartas.isEmpty();
	}
	
	/*
	 * tenemos un archivo binario de objetos de tipo Carta
	 */
	public Vector<Carta> crearMazo() {
		Vector<Carta> nuevoMazo = new Vector<Carta>();

		HashMap<Integer, Carta> listaDeCartas = cargarListaDeCartas();
		// getValue(carta.getId)
		return nuevoMazo;

	}

	private HashMap<Integer, Carta> cargarListaDeCartas() {
		
	}

	

	
}
