package Modelos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import Exceptions.*;
import Extras.Pantalla;
import Extras.Teclado;

public class Jugador {

	private String nombre;
	private Teclado teclado;
	private Mazo mazo;
	private Contenedor<Carta> cementerio;
	private Contenedor<Carta> premios;
	private Contenedor<Carta> mano;
	private final int CANT_INICIAL_MANO = 7;
	private Banca banca;
	private final int CANT_BANCA_MAX = 5;
	private Pokemon pokemonActivo;

	///////////////////////////////////////////////////////////////////////////
	// Constructor
	public Jugador(String jugador, Mazo mazo) {
		super();
		nombre = jugador;
		this.mazo = mazo;
		cementerio = new Contenedor<Carta>(Mazo.MAX_CARTAS);
		premios = new Contenedor<Carta>(CANT_INICIAL_MANO);
		banca = new Banca(CANT_BANCA_MAX);
		pokemonActivo = null;
		mano = new Contenedor<Carta>(Mazo.MAX_CARTAS);

	}

	///////////////////////////////////////////////////////////////////////////
	// Getters & Setters
	public Mazo getMazo() {
		return mazo;
	}

	public String getNombre() {
		return nombre;
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

	public Banca getBanca() {
		return banca;
	}

	public Pokemon getPokemonActivo() {
		return pokemonActivo;
	}

	private void setPokemonActivo(Pokemon nuevoActivo) {
		pokemonActivo = nuevoActivo;
	}

	///////////////////////////////////////////////////////////////////////////
	// Metodos

	/**
	 * Este metodo cargara la mano con 6 cartas
	 */
	private void cargarMano() {
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
		// Recorro buscando algun pokemon y se detiene cuando lo encuentre o no haya mas
		// elementos
		while (iterador.hasNext() && !rta) {
			cartaAux = iterador.next();
			if (cartaAux instanceof Pokemon) {
				rta = true;
			}
		}
		if (rta == false) {
			// Meto las cartas que saque en el mazo nuevamente
			for (int i = 1; i <= CANT_INICIAL_MANO; i++) {
				getMazo().insertarCarta(getMano().extraerCarta(0));

			}
			getMazo().mezclar();
		}
		return rta;
	}

	/**
	 * Crea una mano valida(con al menos un pokemon)
	 */
	public void iniciarMano() {
		getMazo().mezclar();
		cargarMano();
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
	 * Roba una carta del mazo y la coloca en la mano
	 * 
	 * @throws MazoVacioException
	 */
	public void robarCarta() throws MazoVacioException {
		getMano().insertarCarta(getMazo().extraerCarta(0));
		if (getMazo().estaVacio()) {
			throw new MazoVacioException("Te quedaste sin cartas, perdiste lince");
		}
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
	 * 
	 * @throws PremiosVacioException
	 */
	public void robarPremio() throws PremiosVacioException {
		getMano().insertarCarta(getPremios().extraerCarta(0));
		// Si despues de sacar un premio quedan 0 significa que gane
		if (getPremios().cantElementos() == 0) {
			throw new PremiosVacioException(
					"Ganaste crack monstro figura elemento mastodonte campeón jefe fiera máquina genio maestro socio master champion fenómeno torero héroe golfo tanque estegosaurio estrella catedrático espécimen individuo personaje delincuente nobel halcón macho lince ministro neptuno titán artista");
		}
	}

	/**
	 * @param nuevoActivo es el pokemon que estara en la posicion activa
	 */
	public void colocarActivo(Pokemon nuevoActivo) {
		setPokemonActivo(nuevoActivo);
	}

	/**
	 * Quita las energias necesarias para retirar al pokemon activo
	 */
	private void pagarCostoRetirada() {
		Pokemon activo = getPokemonActivo();
		int cantARetirar = activo.getCostoRetirada();
		int indice = -1;
		Energia elegida = null;

		while (cantARetirar > -1) {
			elegida = elegirEnergia(activo);

			insertarEnCementerio(activo.desequiparEnergia(elegida));
			cantARetirar--;
		}
	}

	/**
	 * Quita al pokemon de la posicion activa y la deja en null
	 * 
	 * @return el pokemon que estaba activo
	 */
	private Pokemon retirarPokemonActivo() {
		Pokemon activo = null;
		activo = getPokemonActivo();
		setPokemonActivo(null);
		return activo;
	}

	/**
	 * 
	 * @param pkm la carta pokemon a agregar en la banca
	 * @throws BancaLLenaException si esta llena la banca
	 */
	public void agregarPokemonBanca(Pokemon pkm) throws BancaLLenaException {
		getBanca().agregarPokemon(pkm);
	}

	/**
	 * Comprueba que el pokemon activo pueda pagar su coste de retirada y si puedo
	 * lo retira y reemplaza con uno de la banca
	 * 
	 * @throws BancaLLenaException
	 * @throws EnergiaInsuficiente
	 */
	public void intercambiarPokemones() throws EnergiaInsuficiente {
		Pokemon activo = getPokemonActivo();
		if (activo.puedePagarCostoRetirada()) {
			pagarCostoRetirada();

			// Hago el cambio entre los pokemones
			Pokemon viejoActivo = retirarPokemonActivo();
			Pokemon nuevoActivo = elegirPokemonEnBanca();
			colocarActivo(nuevoActivo);
			getBanca().insertarCarta(viejoActivo);
		} else {
			throw new EnergiaInsuficiente("No alcanzan las energias para retirar al pokemon");
		}
	}

	public void reponerPokemonActivo() throws BancaVaciaException {
		if (getBanca().bancaVacia()) {
			throw new BancaVaciaException("Te quedaste sin pokemones, perdiste");
		} else {
			Pokemon nuevoActivo = elegirPokemonEnBanca();
			colocarActivo(nuevoActivo);
		}
	}

	/**
	 * 
	 * @return el ataque que sera usado por el pokemon atacante
	 * @throws NoPuedeAtacarException
	 */
	public Ataque elegirAtaque() throws NoPuedeAtacarException {
		Pokemon activo = getPokemonActivo();
		boolean tieneSegundoAtaque = activo.tieneSegundoAtaque();
		Ataque elegido = null;
		// Si solo tiene un ataque
		if (tieneSegundoAtaque == false) {
			elegido = activo.retornarAtaque1();
		} else {
			System.out.println("Ingrese 1 para elegir el ataque 1 o 2 para  el ataque 2");
			int eleccion = teclado.nextInt();
			while (eleccion != 1 || eleccion != 2) {
				System.out.println("Valor invalido, ingrese 1 o 2");
				eleccion = teclado.nextInt();
			}
			if (eleccion == 1) {
				elegido = activo.retornarAtaque1();

			} else {
				elegido = activo.retornarAtaque2();
			}
		}
		return elegido;
	}

	/**
	 * 
	 * @return una carta extraida de la mano
	 */
	public Carta elegirCarta() {
		int indice = -1;
		Contenedor<Carta> auxMano = getMano();
		System.out.println("Ingrese un numero entre 1 y " + auxMano.cantElementos()+" para jugar una carta");
		indice = teclado.nextInt() - 1;
		while (indice < 0 && indice > auxMano.cantElementos()) {
			System.out.println("Valor invalido\nIngrese un numero entre 1 y " + auxMano.cantElementos()+" para jugar una carta");
			indice = teclado.nextInt() - 1;
		}
		return auxMano.extraerCarta(indice);
	}

	/**
	 * 
	 * @param p pokemon objetivo de esta accion
	 * @return una energia seleccionada de las equipadas del pokemon
	 */
	public Energia elegirEnergia(Pokemon p) {
		int indice = -1;
		ArrayList<Energia> energiasEquipadas = p.getEnergiasEquipadas();
		System.out.println(energiasEquipadas);
		System.out.println("Ingrese un numero entre 1 y " + +energiasEquipadas.size()+"para elegir la energia");
		indice = teclado.nextInt() - 1;

		while (indice < 0 && indice > energiasEquipadas.size()) {
			System.out.println("Valor invalido\nIngrese un numero entre 1 y " + energiasEquipadas.size()+"para elegir la energia");
			indice = teclado.nextInt() - 1;
		}

		Energia energiaElegida = energiasEquipadas.get(indice);
		return energiaElegida;

	}

	/**
	 * Se elige y quita un pokemon de la banca
	 * 
	 * @return un pokemon
	 */
	public Pokemon elegirPokemonEnBanca() {
		Pokemon elegido = null;
		int eleccion = -1;
		Banca bancaAux = getBanca();
		int tam = bancaAux.cantElementos();
		System.out.println("Ingrese un numero entre 1 y " + tam+"para seleccionar a un pokemon de la banca");
		eleccion = teclado.nextInt() - 1;

		while (eleccion < 0 && eleccion > tam) {
			System.out.println("Valor incorrecto");
			System.out.println("Ingrese un numero entre 1 y " + tam+"para seleccionar a un pokemon de la banca");
			eleccion = teclado.nextInt() - 1;
		}

		// La variable que le paso a quitarPokemon() podria estar mejor
		elegido = bancaAux.quitarPokemon(bancaAux.extraerCarta(eleccion));

		return elegido;
	}

	/**
	 * 
	 * @return un pokemon que se encuentra en el campo, puede ser el activo o que
	 *         este presente en la banca
	 */
	public Pokemon elegirPokemonEnCampo() {
		// SACAR EL SCANNER DE ARRIBA
		ArrayList<Pokemon> listaPokemones = new ArrayList<>();
		// Agrego todos los pokemones del jugador a una misma coleccion
		listaPokemones.addAll(getBanca().getElementos());
		listaPokemones.add(getPokemonActivo());
		Pantalla.mostrarRecurso(listaPokemones);
		
		System.out.println("Ingrese un numero entre 1 y " + listaPokemones.size()+" para elegir alguno de los pokemones");
		int indice = teclado.nextInt() - 1;
		while (indice < 0 && indice > listaPokemones.size()) {
			System.out.println("Valor invalido");
			System.out.println("Ingrese un numero entre 1 y " + listaPokemones.size()+" para elegir alguno de los pokemones");
			indice = teclado.nextInt() - 1;
		}
		return listaPokemones.get(indice);
	}
}
