package Modelos;

import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;

import Interfaces.moverCarta;

public class Mazo implements moverCarta<Carta> {
	public static final int MAX_CARTAS = 40;
	// private int cartasRestante;
	private Vector<Carta> cartas;

	public Mazo() {
		super();
		// setCartasRestante(MAX_CARTAS);

		/*
		 * cartas= crearMazo(recibe la composicion del mazo en forma de json) crearMazo
		 * tiene adentro el archivo fuente con todas las cartas
		 * 
		 */
	}

	/*
	 * public int getCartasRestante() { return cartasRestante; } public void
	 * setCartasRestante(int cartasRestante) { this.cartasRestante = cartasRestante;
	 * }
	 */
	public void mezclar() {
		// Aca va la logica de como mezlcar
	}

	@Override
	public void insertarCarta(Carta cartaAInsertar) {
		cartas.add(cartaAInsertar);
		//setCartasRestante(getCartasRestante()+1);
		//cartas.push(cartaAInsertar);
	}

	

	/*
	 * tenemos un archivo binario de objetos de tipo Carta 
	 */
	public Stack<Carta> crearMazo(){
		Stack<Carta> nuevoMazo= new Stack<Carta>();
		
		HashMap<Integer,Carta> listaDeCartas = cargarListaDeCartas(); 
		//getValue(carta.getId)
		return nuevoMazo;
		
	}
	
	
	public HashMap<Integer,Carta> cargarListaDeCartas(){
	
	
	}
	public boolean estaVacio()
	{
		return cartas.isEmpty();
	}

	@Override
	public Carta extraerCarta(int indice) {
		// setCartasRestante(cartas.size()-1);
		 return cartas.remove(indice);
		//return cartas.pop();

	}
	}