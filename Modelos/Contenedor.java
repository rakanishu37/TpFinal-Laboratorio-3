package Modelos;

import java.util.ArrayList;
import java.util.Iterator;

import Interfaces.moverCarta;

public class Contenedor<T> implements moverCarta<T>,Iterable<T>{

	private final int CANT_MAXIMA;
	private ArrayList<T>conjuntoDeElementos;
	
	
	public Contenedor(int cANT_MAXIMA) {
		super();
		CANT_MAXIMA = cANT_MAXIMA;
		conjuntoDeElementos=new ArrayList<T>();
	}

	/**
	 * @return en forma de arrayList los elementos del contenedor
	 */
	public ArrayList<T> getElementos(){
		return conjuntoDeElementos;
	}
	
	@Override
	public void insertarCarta(T carta) {
		conjuntoDeElementos.add(carta);
	}

	@Override
	public T extraerCarta(int indice) {	
		return conjuntoDeElementos.remove(indice);
	}

	@Override
	public Iterator<T> iterator() {		
		return conjuntoDeElementos.iterator();
	}

	/**
	 * 
	 * @return la cantidad de elementos en el contenedor
	 */
	public int cantElementos() {
		return conjuntoDeElementos.size();
	}
}
