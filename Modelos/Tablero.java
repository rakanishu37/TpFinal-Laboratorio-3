package Modelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;



import Exceptions.*;

public class Tablero {
	// SACAR EL SCANNER

	private Scanner teclado;
	private Jugador j1;
	private Jugador j2;

	private Jugador jugadorAtacante;
	private Jugador jugadorDefensor;

	private boolean turnoJugadorAtacante;
	private boolean turnoJugadorDefensor;

	private boolean ganador;
	private Jugador jugadorGanador;

	public Tablero(Jugador jugador1, Jugador jugador2) {

		ganador = false;
		turnoJugadorAtacante = false;
		turnoJugadorDefensor = false;

		j1 = jugador1;
		j2 = jugador2;
	}

	public Jugador getJugadorAtacante() {
		return jugadorAtacante;
	}

	public void setJugadorAtacante(Jugador jugadorAtacante) {
		this.jugadorAtacante = jugadorAtacante;
	}

	public void jugar() {
		jugadorAtacante = j1;
		turnoJugadorAtacante = true;

		jugadorDefensor = j2;

		while (!ganador) {
			// El jugador atacante elige una carta de su mano

			try {
				mazoVacio(); // Si se queda sin cartas
				faseRobarCarta();

				Carta c = jugarCarta();

				resolverCarta(c);

				cambiarTurno();
			} catch (MazoVacioException e) {
				System.out.println(e.getMessage());
			}

		}
	}

	private void cambiarTurno() {
		Jugador aux = jugadorAtacante;
		jugadorAtacante = jugadorDefensor;
		jugadorDefensor = aux;
	}

	private void mazoVacio() throws MazoVacioException {
		getJugadorAtacante().getMazo().estaVacio();
	}

	private void faseRobarCarta() {
		getJugadorAtacante().robarCarta();
	}

	private Carta jugarCarta() {
		int indice = 0;
		Contenedor<Carta> aux = jugadorAtacante.getMano();
		while (indice < 0 && indice >= aux.cantElementos()) {
			// Ingreso nuevo de eleccion
		}
		return aux.extraerCarta(indice);
	}

	private void resolverCarta(Carta c) {
		if (c instanceof Energia) {
			// Aca estan todos los pokemones del jugador
			// Se elige un pokemon de esta listaPokemones y se le equipa la energia
			Pokemon objetivo = elegirPokemon();

			((Energia) c).serEquipada(objetivo);
		} else {
			if (c instanceof Pokemon) {
				Pokemon aux = (Pokemon) c;
				try {
					jugadorAtacante.agregarPokemonBanca(aux);
				} catch (BancaLLenaException e) {
					System.out.println(e.getMessage());
				}
			} else {
				Trainer aux = (Trainer) c;
				resolverTrainer(aux);
			}
		}
	}

	private void resolverTrainer(Trainer t) {
		String accion = t.getPalabraClave();		

		switch (accion) {
		case "ROBAR":
			int k = t.getNumero();

			for (int i = 1; i <= k; i++) {
				jugadorAtacante.robarCarta();
			}
			break;
		case "CURAR":
			Pokemon objetivo = elegirPokemon();

			objetivo.aumentarVida(t.getNumero());
			break;
		}

	}

	private Pokemon elegirPokemon() {
		// SACAR EL SCANNER DE ARRIBA
		ArrayList<Pokemon> listaPokemones = new ArrayList<>();
		//Agrego todos los pokemones del jugador a una misma coleccion
		Collections.addAll(listaPokemones,jugadorAtacante.getBanca());		
		listaPokemones.add(jugadorAtacante.getPokemonActivo());
		
		System.out.println("Ingrese un numero entre 1 y " + listaPokemones.size());
		int indice = teclado.nextInt();
		while (indice < 1 && indice >= listaPokemones.size()) {
			System.out.println("Ingrese un numero entre 1 y " + listaPokemones.size());
			indice = teclado.nextInt();
		}
		return listaPokemones.get(indice);
	}
}