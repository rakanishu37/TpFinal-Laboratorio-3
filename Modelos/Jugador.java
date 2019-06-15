package Modelos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Jugador {
	private Mazo mazo;
	private Contenedor<Carta> cementerio;
	private Contenedor<Carta> premios;
	private Contenedor<Carta> mano;
	private Pokemon[]banca;
	private Pokemon pokemonActivo;

	public Jugador(Mazo mazo) {
		super();
		this.mazo = mazo;
		cementerio=new Contenedor<Carta>(Mazo.MAX_CARTAS);
		premios=new Contenedor<Carta>(6);
		banca= new Pokemon[5];
		pokemonActivo=null;
		mano=new Contenedor<Carta>(Mazo.MAX_CARTAS);

	}
	public Contenedor<Carta>getCementerio() {
		return cementerio;
	}
	public Mazo getMazo() {
		return mazo;
	}

	public Contenedor<Carta> getPremios() {
		return premios;
	}

	public Contenedor<Carta> getMano() {
		return mano;
	}
/**
 * Este metodo cargara la mano con 6 cartas
 */
	public void cargarMano() {
		Mazo mazoAux = getMazo();
		Contenedor<Carta> manoAux = getMano();
		for (int i = 1; i <= 6; i++) {
			//ponemos 0 en extraerCarta para sacar la primera del mazo
			manoAux.insertarCarta(mazoAux.extraerCarta(0));
		}
	}
	/**
	 * 
	 * @param mano del jugador
	 * @return retorna verdadero cuando la mano contenga por lo menos un pokemon
	 */
	private boolean manoValida(Contenedor<Carta>mano)
	{
		boolean rta=false;
		Carta cartaAux=null;
		Iterator iterador=getMano().iterator();
		while(iterador.hasNext() && !rta)
		{
			cartaAux=(Carta)iterador.next();
			if(cartaAux instanceof Pokemon)
			{
			
				rta=true;
			}
		}
		return rta;
	}
	/**
	 * Crea una mano valida(con al menos un pokemon)
	 */
	public void iniciarMano()
	{
		
		while(manoValida(getMano())==false)
		{
			cargarMano();
		}
	}
	
	public void mezclarMazo() {
		getMazo().mezclar();
	}
	
	public boolean mazoVacio()
	{
		return getMazo().estaVacio();
	}
	
	public void robarCarta() 
	{
		getMano().insertarCarta(getMazo().extraerCarta(0));
	}
	public void insertarEnCementerio(Carta aCementerio)
	{
		getCementerio().insertarCarta(aCementerio);
	}
	
	public void cargarPremios() {
		Mazo mazoAux = getMazo();
		Contenedor<Carta> premiosAux = getPremios();
		for (int i = 1; i <= 6; i++) {
			//ponemos 0 en extraerCarta para sacar la primera del mazo
			premiosAux.insertarCarta(mazoAux.extraerCarta(0));
		}
	}
	public void robarPremio() 
	{
		getMano().insertarCarta(getPremios().extraerCarta(0));
	}
	/* 	
		
		
		
		
		
		agregarPokemonBanca
		bancaVacia
		bajarPokemon
		retirarPokemonActivo
		atacar
	*/
}
