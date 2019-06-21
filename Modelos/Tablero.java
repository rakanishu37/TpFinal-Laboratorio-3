package Modelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import Exceptions.*;
import Extras.Pantalla;
import Extras.Teclado;

public class Tablero {

	private Teclado teclado;
	private Jugador j1;
	private Jugador j2;

	private Jugador jugadorAtacante;
	private Jugador jugadorDefensor;

	private boolean energiaJugada;
	private boolean ganador;

	public Tablero(Jugador jugador1, Jugador jugador2) {
		ganador = false;

		j1 = jugador1;
		j2 = jugador2;
	}

	public Jugador getJugadorAtacante() {
		return jugadorAtacante;
	}

	public Jugador getJugadorDefensor() {
		return jugadorDefensor;
	}

	private boolean getEnergiaJugada() {
		return energiaJugada;
	}

	public void setJugadorAtacante(Jugador atacante) {
		jugadorAtacante = atacante;
	}

	public void setJugadorDefensor(Jugador defensor) {
		jugadorDefensor = defensor;
	}

	/**
	 * Se juega una partida hasta que haya un ganador por alguna razon o pierda el
	 * otro jugador
	 */
	public void jugar() {
		// Se inicia la mano de los jugadores con una mano valida y se cargan sus premios
		primeraMano();

		// Comienza jugando jugador1
		setJugadorAtacante(j1);
		setJugadorDefensor(j2);

		while (!ganador) {

			try {
				robarCarta();
				menu();
				cambiarTurno();
			} catch (MazoVacioException e) {
				System.out.println(e.getMessage());
			} catch (BancaVaciaException e) {
				System.out.println(e.getMessage());
			} catch (PremiosVacioException e) {
				System.out.println(e.getMessage());
			}
			
		}
	}

	/**
	 * 
	 * @return texto del menu
	 */
	private String menuTexto() {
		StringBuilder msg = new StringBuilder();
		msg.append("1- Menu Mano\n");
		msg.append("2- Ver Banca\n");
		msg.append("3- Ver Pokemon Activo\n");
		msg.append("4- Ver Pokemon Defensor\n");
		msg.append("5- Ver Banca Defensora\n");
		msg.append("6- Retirar Pokemon\n");
		msg.append("7- Atacar\n");
		msg.append("8- Pasar turno\n");
		return msg.toString();
	}

	/**
	 * Menu que permite interactuar con la mano
	 * 
	 * @throws MazoVacioException
	 */
	private void menuMano() throws MazoVacioException {

		int opcion = -1;
		do {
			verMano();
			System.out.println("Ingrese 1 para jugar una carta");
			System.out.println("Ingrese 0 para volver al menu anterior");
			opcion = teclado.nextInt();
			switch (opcion) {
			case 1:
				Carta c = getJugadorAtacante().elegirCarta();
				// Si ya se jugo una energia en este turno del jugador se la inserta nuevamente
				// en su mano
				if(c instanceof Energia && getEnergiaJugada())
				{
					getJugadorAtacante().getMano().insertarCarta(c);
					System.out.println("Ya se bajo una energia este turno");
				} else {
					try {
						// En el caso de que la banca este llena
						// se le permite al jugador seguir realizando acciones
						jugarCarta(c);
					} catch (BancaLLenaException e) {
						System.out.println(e.getMessage());
					}
				}
				break;
			case 0:
				System.out.println("Volviendo al menu anterior");
				break;
			}
		} while (opcion != 0);
	}

	/**
	 * Menu principal del juego que posee todas las acciones de un jugador puede
	 * realizar en su turno
	 * 
	 * @throws MazoVacioException
	 * @throws BancaVaciaException
	 * @throws PremiosVacioException
	 */
	private void menu() throws MazoVacioException, BancaVaciaException, PremiosVacioException {
		int opcion = -1;
		
		// Se detiene este while cuando decidio atacar o pasar el turno
		do {
			System.out.println(getJugadorAtacante().getNombre());
			System.out.println(menuTexto());
			opcion = teclado.nextInt();
			switch (opcion) {
			case 1:
				menuMano();
				break;

			case 2:
				verBanca();
				break;

			case 3:
				verPokemonActivo();
				break;

			case 4:
				verPokemonDefensor();
				break;
			case 5:
				verBancaDefensor();
				break;
			
			case 6:
				try {
					getJugadorAtacante().intercambiarPokemones();
				} catch (EnergiaInsuficiente e) {
					System.out.println(e.getMessage());
				}
				break;
			case 7:
				atacar();
				break;
			case 8:
				Pantalla.mostrarPorPantalla("Pasando el turno");
				break;
			}
		}
		while (opcion != 7 && opcion != 8);
	}

	/**
	 * Produce el cambio de turno en la partida
	 */
	private void cambiarTurno() {
		energiaJugada = false;
		Jugador aux = getJugadorAtacante();
		setJugadorAtacante(getJugadorDefensor());
		setJugadorDefensor(aux);
	}

	/**
	 * Indica que el jugador robe una carta
	 * 
	 * @throws MazoVacioException
	 */
	private void robarCarta() throws MazoVacioException {
		getJugadorAtacante().robarCarta();
	}

	/**
	 * Muestra la mano del jugador
	 */
	private void verMano() {
		Pantalla.mostrarRecurso(getJugadorAtacante().getMano().getElementos());
	}

	/**
	 * Muestra la banca del jugador
	 */
	private void verBanca() {
		if (getJugadorAtacante().getBanca().bancaVacia()) {
			System.out.println("No hay pokemones en la banca");
		} else {
			Pantalla.mostrarRecurso((getJugadorAtacante().getBanca().getElementos()));
		}
	}

	/**
	 * Muestra el pokemon activo
	 */
	private void verPokemonActivo() {
		Pantalla.mostrarPorPantalla((getJugadorAtacante().getPokemonActivo().toString()));
	}

	/**
	 * Muestra el pokemon del jugador defensor
	 */
	private void verBancaDefensor() {
		if (getJugadorDefensor().getBanca().bancaVacia()) {
			System.out.println("No hay pokemones en la banca del jugador defensor");
		}
	Pantalla.mostrarRecurso(getJugadorDefensor().getBanca().getElementos());
	}

	/**
	 * Muestra la banca del jugador defensor
	 */
	private void verPokemonDefensor() {
		Pantalla.mostrarPorPantalla((getJugadorDefensor().getPokemonActivo().toString()));
	}

	/**
	 * Realiza una accion distinta que carta se le pase por parametro: *Pokemon sera
	 * colocado en la banca *Energia sera equipada en un pokemon seleccionado
	 * *Trainer producira un efecto segun sus palabras claves
	 * 
	 * @param c
	 * @throws MazoVacioException
	 * @throws BancaLLenaException
	 */
	private void jugarCarta(Carta c) throws MazoVacioException, BancaLLenaException {
		if (c instanceof Energia) {
			// Se elige un pokemon que esta en el campo y se le equipa la energia
			Pokemon objetivo = jugadorAtacante.elegirPokemonEnCampo();

			((Energia) c).serEquipada(objetivo);
			energiaJugada = true;
		} else {
			if (c instanceof Pokemon) {
				Pokemon aux = (Pokemon) c;
				jugadorAtacante.agregarPokemonBanca(aux);
				System.out.println("POkemon fue agregado");

			} else {
				Trainer aux = (Trainer) c;
				resolverTrainer(aux);
				jugadorAtacante.insertarEnCementerio(aux);
			}
		}
	}

	/**
	 * Se encarga de efectuar lo que indica la carta de tipo Trainer
	 * 
	 * @param t
	 * @throws MazoVacioException
	 */
	private void resolverTrainer(Trainer t) throws MazoVacioException {
		String accion = t.getPalabraClave();

		switch (accion) {
		case "ROBAR":
			int k = t.getNumero();

			for (int i = 1; i <= k; i++) {
				jugadorAtacante.robarCarta();
			}
			break;
		case "CURAR":
			Pokemon objetivo = jugadorAtacante.elegirPokemonEnCampo();

			objetivo.aumentarVida(t.getNumero());
			break;
		}
	}

	/**
	 * Se consulta si desea atacar y resuelva el combate
	 * @throws BancaVaciaException
	 * @throws PremiosVacioException
	 */
	private void atacar() throws BancaVaciaException, PremiosVacioException {
		Jugador atacante = getJugadorAtacante();
		Jugador defensor = getJugadorDefensor();

		try {
				resolverCombate(atacante.elegirAtaque(), defensor.getPokemonActivo());
			} catch (NoPuedeAtacarException e) {
				System.out.println(e.getMessage());
			}
	}

	/**
	 * Se encarga de manejar el daño de combate, robar premios y el jugador defensor reponga su pokemon activo
	 * @param ataque que infligira daño
	 * @param defensor pokemon que recibe el ataque
	 * @throws BancaVaciaException si el jugador defensor se quedo sin pokemones en campo y perdio
	 * @throws PremiosVacioException si el jugador atacante gano al quedarse sin premios
	 */
	private void resolverCombate(Ataque ataque, Pokemon defensor) throws BancaVaciaException, PremiosVacioException {
		defensor.decrementarVida(ataque);
		if (defensor.estaIncapacitado()) {
			getJugadorDefensor().reponerPokemonActivo();
			getJugadorAtacante().robarPremio();
		}
	}
	/**
	 * Hace que ambos jugadores inicien con una mano y carguen los premios
	 */
	private void primeraMano() 
	{
		j1.iniciarMano();
		j1.cargarPremios();
		elegirPrimerPokemon(j1);
		
		j2.iniciarMano();
		j2.cargarPremios();
		elegirPrimerPokemon(j2);
	}
	
	private void elegirPrimerPokemon(Jugador j){
		Pantalla.mostrarPorPantalla(j.getNombre()+" va elegir su primer pokemon");
		Iterator<Carta> mano = j.getMano().iterator();
		ArrayList<Pokemon> pokemonsDisponibles = new ArrayList<>();
		
		while(mano.hasNext()) {
			Carta c= mano.next();
			if(c instanceof Pokemon) {
				pokemonsDisponibles.add((Pokemon)c);
			}
		}
		
		Pantalla.mostrarRecurso(pokemonsDisponibles);
		
		System.out.println("Ingrese un valor entre 1 y "+ pokemonsDisponibles.size()+" para elegir a su primer pokemon");
		int opcion = teclado.nextInt()-1;
		
		while(opcion<0 && opcion >pokemonsDisponibles.size()) {
			System.out.println("Valor incorrecto\nIngrese un valor entre 1 y "+ pokemonsDisponibles.size()+" para elegir a su primer pokemon");
			opcion = teclado.nextInt()-1;
		}
		Pokemon activo = pokemonsDisponibles.remove(opcion);
		
		int indice = j.getMano().getElementos().indexOf(activo);
		j.getMano().extraerCarta(indice);
		
		j.colocarActivo(activo);
	}
}