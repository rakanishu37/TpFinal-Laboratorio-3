package Modelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Exceptions.*;
import Extras.Teclado;

public class Tablero {
	// SACAR EL SCANNER

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

	public void setJugadorAtacante(Jugador jugadorAtacante) {
		this.jugadorAtacante = jugadorAtacante;
	}

	public void setJugadorDefenstor(Jugador defensor) {
		jugadorDefensor = defensor;
	}

	public void jugar() {
		Carta c = null;		
		j1.iniciarMano();
		j2.iniciarMano();
		jugadorAtacante = j1;
		jugadorDefensor = j2;

		while (!ganador) {

			try {
				robarCarta();
				menu();

			} catch (MazoVacioException e) {
				System.out.println(e.getMessage());
			} catch (BancaVaciaException e) {
				System.out.println(e.getMessage());
			} catch (PremiosVacioException e) {
				System.out.println(e.getMessage());
			}
			cambiarTurno();
		}
	}

	private String menuTexto() {
		StringBuilder msg = new StringBuilder();
		msg.append("1- Ver Mano");
		msg.append("2- Ver Banca");
		msg.append("3- Ver Pokemon Defensor");
		msg.append("4- Ver Banca Defensora");
		msg.append("5- Retirar Pokemon");
		msg.append("6- Atacar");
		msg.append("7- Pasar turno");
		return msg.toString();
	}

	private void menuMano() throws MazoVacioException {

		int opcion = -1;
		do {
			verMano();
			System.out.println("Ingrese 1 para jugar una carta");
			System.out.println("Ingrese 0 para volver al menu anterior");
			opcion = teclado.nextInt();
			switch (opcion) {
			case 1:
				Carta c = jugadorAtacante.elegirCarta();
				if (energiaJugada) {
					jugadorAtacante.getMano().insertarCarta(c);
					System.out.println("Ya se bajo una energia este turno");
				} else {
					try {
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

	private void menu() throws MazoVacioException, BancaVaciaException, PremiosVacioException {
		int opcion = -1;
		System.out.println(menuTexto());
		// Se detiene este while cuando decidio atacar o pasar el turno
		while (opcion != 6 && opcion != 7) {
			System.out.println();
			switch (opcion) {
			case 1:
				menuMano();
				break;

			case 2:
				verBanca();
				break;

			case 3:
				verPokemonDefensor();
				break;

			case 4:
				verBancaDefensor();
				break;
			case 5:
				try {
					jugadorAtacante.intercambiarPokemones();
				} catch (EnergiaInsuficiente e) {
					System.out.println(e.getMessage());
				}

				break;
			case 6:
				atacar();
				break;
			case 7:
				System.out.println("Pasando el turno");
				break;
			}
		}

	}

	private void cambiarTurno() {
		energiaJugada = false;
		Jugador aux = jugadorAtacante;
		jugadorAtacante = jugadorDefensor;
		jugadorDefensor = aux;
	}

	private void robarCarta() throws MazoVacioException {
		getJugadorAtacante().robarCarta();
	}

	private void verMano() {
		System.out.println(jugadorAtacante.getMano());
	}

	private void verBanca() {
		if (getJugadorAtacante().getBanca().bancaVacia()) {
			System.out.println("No hay pokemones en la banca");
		} else {
			System.out.println(jugadorAtacante.getBanca());
		}
	}

	private void verPokemonActivo() {
		System.out.println(jugadorAtacante.getPokemonActivo());
	}

	private void verBancaDefensor() {
		if (getJugadorDefensor().getBanca().bancaVacia()) {
			System.out.println("No hay pokemones en la banca del jugador defensor");
		}
		System.out.println(jugadorDefensor.getBanca());
	}

	private void verPokemonDefensor() {
		System.out.println(jugadorDefensor.getPokemonActivo());
	}

	/**
	 * Resuelve segun que tipo de carta
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

			} else {
				Trainer aux = (Trainer) c;
				resolverTrainer(aux);
				jugadorAtacante.insertarEnCementerio(aux);
			}
		}
	}

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

	private void atacar() throws BancaVaciaException, PremiosVacioException {
		Jugador atacante = getJugadorAtacante();
		Jugador defensor = getJugadorDefensor();
		resolverCombate(atacante.elegirAtaque(), defensor.getPokemonActivo());
	}

	private void resolverCombate(Ataque ataque, Pokemon defensor) throws BancaVaciaException, PremiosVacioException {
		defensor.decrementarVida(ataque);
		if (defensor.estaIncapacitado()) {
			getJugadorDefensor().reponerPokemonActivo();
			getJugadorAtacante().robarPremio();
		}

	}
}