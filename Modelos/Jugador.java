package Modelos;

import java.util.Iterator;

import Exceptions.BancaLLenaException;
import Exceptions.BancaVaciaException;

public class Jugador {
	private Mazo mazo;
	private Contenedor<Carta> cementerio;
	private Contenedor<Carta> premios;
	private Contenedor<Carta> mano;
	private final int CANT_INICIAL_MANO = 7;
	private Pokemon[] banca;
	private final int CANT_BANCA_MAX = 5;
	private Pokemon pokemonActivo;

	///////////////////////////////////////////////////////////////////////////
	//Constructor
	public Jugador(Mazo mazo) {
		super();
		this.mazo = mazo;
		cementerio = new Contenedor<Carta>(Mazo.MAX_CARTAS);
		premios = new Contenedor<Carta>(CANT_INICIAL_MANO);
		banca = new Pokemon[CANT_BANCA_MAX];
		pokemonActivo = null;
		mano = new Contenedor<Carta>(Mazo.MAX_CARTAS);

	}

	///////////////////////////////////////////////////////////////////////////
	//Getters & Setters
	public Mazo getMazo() {
		return mazo;
	}

	public Contenedor<Carta> getCementerio() {
		return cementerio;
	}

	public Contenedor<Carta> getPremios() {
		return premios;
	}

	public Contenedor<Carta> getMano() {
		return mano;
	}

	public Pokemon[] getBanca() {
		return banca;
	}

	public Pokemon getPokemonActivo() {
		return pokemonActivo;
	}

	private void setPokemonActivo(Pokemon nuevoActivo) {
		pokemonActivo = nuevoActivo;
	}

	///////////////////////////////////////////////////////////////////////////
	//Metodos
	
	/**
	 * Este metodo cargara la mano con 6 cartas
	 */
	public void cargarMano() {
		Mazo mazoAux = getMazo();
		Contenedor<Carta> manoAux = getMano();
		for (int i = 1; i <= CANT_INICIAL_MANO; i++) {
			// ponemos 0 en extraerCarta para sacar la primera del mazo
			manoAux.insertarCarta(mazoAux.extraerCarta(0));
		}
	}

	/**
	 * 
	 * @param mano del jugador
	 * @return retorna verdadero cuando la mano contenga por lo menos un pokemon
	 */
	private boolean manoValida(Contenedor<Carta> mano) {
		boolean rta = false;
		Carta cartaAux = null;
		Iterator<Carta> iterador = getMano().iterator();

		while (iterador.hasNext() && !rta) {
			cartaAux = iterador.next();
			if (cartaAux instanceof Pokemon) {
				rta = true;
			}
		}
		return rta;
	}

	/**
	 * Crea una mano valida(con al menos un pokemon)
	 */
	public void iniciarMano() {

		while (manoValida(getMano()) == false) {
			cargarMano();
		}
	}

	/**
	 * Mezcla el mazo del jugador
	 */
	public void mezclarMazo() {
		getMazo().mezclar();
	}

	/**
	 * 
	 * @return true si ya no quedan cartas en el mazo
	 */
	public boolean mazoVacio() {
		return getMazo().estaVacio();
	}

	/**
	 * Roba una carta del mazo y la coloca en la mano
	 */
	public void robarCarta() {
		getMano().insertarCarta(getMazo().extraerCarta(0));
	}

	/**
	 * Inserta en el cementario la carta que llega por parametro
	 * 
	 * @param aCementerio
	 */
	public void insertarEnCementerio(Carta aCementerio) {
		getCementerio().insertarCarta(aCementerio);
	}

	/**
	 * Carga los premios con 6 cartas provenientes del mazo
	 */
	public void cargarPremios() {
		Mazo mazoAux = getMazo();
		Contenedor<Carta> premiosAux = getPremios();
		for (int i = 1; i <= 6; i++) {
			// ponemos 0 en extraerCarta para sacar la primera del mazo
			premiosAux.insertarCarta(mazoAux.extraerCarta(0));
		}
	}

	/**
	 * Saca un premio de la pila y lo coloca en la mano
	 */
	public void robarPremio() {
		getMano().insertarCarta(getPremios().extraerCarta(0));
	}

	/**
	 * 
	 * @return retorna la primera posicion libre de la banca o 5
	 */
	private int buscarPosicionVaciaEnBanca() {
		int i = 0;
		int posicionValida = 0;
		boolean estaLleno = true;
		Pokemon[] bancaAux = getBanca();

		while (estaLleno && i < CANT_BANCA_MAX) {
			// Si el equals devuelve true significa que ese hueco de la banca esta libre
			if (bancaAux[i].equals(null)) {
				posicionValida = i;
				estaLleno = false;
			} else {
				i++;
			}
		}
		return posicionValida;
	}

	/**
	 * 
	 * @param pkm la carta pokemon a agregar en la banca
	 * @throws BancaLLenaException si esta llena la banca
	 */
	public void agregarPokemonBanca(Pokemon pkm) throws BancaLLenaException {
		Pokemon[] bancaAux = getBanca();
		int posicionValida = buscarPosicionVaciaEnBanca();
		if (posicionValida == CANT_BANCA_MAX)
			throw new BancaLLenaException("La banca esta llena y no podes bajar más");
		else {
			bancaAux[posicionValida] = pkm;
		}
	}

	/**
	 * 
	 * @return true si la banca esta vacia, false en caso contrario
	 */
	public boolean bancaVacia() {
		boolean rta = true;
		Pokemon[] bancaAux = getBanca();
		int i = 0;

		while (rta && i < CANT_BANCA_MAX) {
			// Si el equals devuelve false significa que hay una carta pokemon en esa
			// posicion de la banca
			// y con el operador ! ese valor es verdadero y entro en el if
			if (!bancaAux[i].equals(null)) {
				rta = false;
			} else {
				i++;
			}
		}
		return rta;
	}

	/**
	 * 
	 * @param posicion es el lugar (un valor entero) donde se encuentra en mi mano
	 *                 la carta pokemon a bajar en la banca
	 * @throws BancaLLenaException si la banca fuera a estar llena
	 */
	public void bajarPokemonABanca(int posicion) throws BancaLLenaException {
		Pokemon pkm = (Pokemon) getMano().extraerCarta(posicion);
		agregarPokemonBanca(pkm);
	}

	/**
	 * 
	 * @return el pokemon que estaba activo dejando la posicion vacia
	 */
	private Pokemon retirarPokemonActivo() {
		Pokemon activo = null;
		activo = getPokemonActivo();
		setPokemonActivo(null);
		return activo;
	}

	/**
	 * 
	 * @param nuevoActivo es el pokemon que estara en la posicion activa
	 */
	public void colocarActivo(Pokemon nuevoActivo) {
		setPokemonActivo(nuevoActivo);
	}

	/**
	 * 
	 * @param posicionPkmBanca el pokemon que pasara a estar activo
	 * @throws BancaLLenaException
	 */
	public void intercambiarPokemones(int posicionPkmBanca) throws BancaLLenaException {
		Pokemon viejoActivo = retirarPokemonActivo();
		Pokemon[] banca = getBanca();
		Pokemon nuevoActivo = banca[posicionPkmBanca];
		colocarActivo(nuevoActivo);
		agregarPokemonBanca(viejoActivo);
	}

	
	/*
	 * Logica de seleccion de ataque 
	 */
	public int elegirAtaque() {
		/*
		 * Primer boceto
		 */
		java.util.Scanner teclado= new java.util.Scanner(System.in);
		int eleccion = 0;
		System.out.println("Ingrese 1 para elegir el ataque 1");		
		
		if(getPokemonActivo().getAtaque2().equals(null)) {
			System.out.println("Ingrese 2 para elegir el ataque 2");
		}
		
		eleccion = teclado.nextInt();
		
		if(getPokemonActivo().getAtaque2().equals(null)) {
			while(eleccion!=1) {
				System.out.println("Ingresa 1");
				eleccion = teclado.nextInt();
			}
		}
		else {
			while(eleccion!= 1 || eleccion!= 2) {
				System.out.println("Valor invalido, ingrese 1 o 2");
				eleccion = teclado.nextInt();
			}
		}
		
		return eleccion;			
	}
	
	public Ataque ataqueElegido(int eleccion) {
		Ataque elegido=null;
		if(eleccion ==1) {
			elegido =getPokemonActivo().getAtaque1();
		}
		else {
			elegido =getPokemonActivo().getAtaque2();
		}
		return elegido;
	}
}
