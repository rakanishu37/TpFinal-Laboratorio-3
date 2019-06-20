package Modelos;

import java.util.ArrayList;

import Exceptions.BancaLLenaException;
import Exceptions.BancaVaciaException;

public class Banca extends Contenedor<Pokemon> {

	public Banca(int cANT_MAXIMA) {
		super(cANT_MAXIMA);
		// TODO Auto-generated constructor stub
	}

	public void agregarPokemon(Pokemon p) throws BancaLLenaException {

		if (cantElementos() == 5) {
			throw new BancaLLenaException("La banca esta llena y no podes bajar más");
		} else {
			insertarCarta(p);
		}
	}

	public Pokemon quitarPokemon(Pokemon p) {
		ArrayList<Pokemon> aux = getElementos();

		Pokemon pokemonAMover = extraerCarta(aux.indexOf(p));

		return pokemonAMover;
	}

	public boolean bancaVacia() {
		boolean rta = false;
		if (cantElementos() == 0) {
			rta = true;
		}
		return rta;
	}
/*
	@Override
	public String toString() {
		StringBuilder msg = new StringBuilder();
		ArrayList<Pokemon> bancaAux = getElementos();
		for(Pokemon p: bancaAux) {
			msg.append("Nombre: "+p.getNombre());
			msg.append("Vida Maxima: "+p.getNombre());			
			msg.append("Vida Restante: "+p.getVidaActual());
			
			msg.append("Energias Equipadas: "+p.getNombre());
			
			msg.append("Ataque"+)
			
		}
		return msg.toString();
	}
*/
}
