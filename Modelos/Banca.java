package Modelos;

import java.util.ArrayList;
import java.util.Iterator;

import Exceptions.BancaLLenaException;
import Exceptions.BancaVaciaException;

public class Banca extends Contenedor<Pokemon> {

	public Banca(int cANT_MAXIMA) {
		super(cANT_MAXIMA);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Agrega un pokemon a la banca, verificando que no haya ya 5, en ese caso lanza la excepcion
	 * @param p pokemon a agregar
	 * @throws BancaLLenaException
	 */
	public void agregarPokemon(Pokemon p) throws BancaLLenaException {

		if (super.cantElementos() == 5) {
			throw new BancaLLenaException("La banca esta llena y no podes bajar más");
		} else {
			super.insertarCarta(p);
		}
	}

	/**
	 * Quita un pokemon de la banca
	 * @param p pokemon a quitar
	 * @return el pokemon contado
	 */
	public Pokemon quitarPokemon(Pokemon p) {
		ArrayList<Pokemon> aux = getElementos();

		Pokemon pokemonAMover = super.extraerCarta(aux.indexOf(p));

		return pokemonAMover;
	}

	/**
	 * 
	 * @return true si la banca esta vacia
	 */
	public boolean bancaVacia() {
		boolean rta = false;
		if (super.cantElementos() == 0) {
			rta = true;
		}
		return rta;
	}

	@Override
	public String toString() {
		StringBuilder msg = new StringBuilder();
		ArrayList<Pokemon> bancaAux = getElementos();
		for(Pokemon p: bancaAux) {
			msg.append("Nombre: "+p.getNombre()+"\n");
			msg.append("Vida Maxima: "+p.getVidaMaxima()+"\n");			
			msg.append("Vida Restante: "+p.getVidaActual());
			
			msg.append("Energias Equipadas: "+p.getEnergiasEquipadas()+"\n");
			
			msg.append("Ataque 1: "+p.getAtaque1()+"\n");
			
			msg.append("Ataque 2: "+p.getAtaque2()+"\n");
			
			msg.append("Resistencia: "+ p.getResistencia()+"\n");
			msg.append("Debilidad: "+ p.getDebilidad()+"\n");
			msg.append("Costo de Retirada: "+ p.getCostoRetirada()+"energias"+"\n");
		}
		return msg.toString();
	}

}
