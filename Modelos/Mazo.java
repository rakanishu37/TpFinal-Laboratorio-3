package Modelos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Random;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Interfaces.moverCarta;

public class Mazo implements moverCarta<Carta> {
	public static final int MAX_CARTAS = 40;
	private Vector<Carta> cartas;

	public Mazo(JSONArray composicionMazo) {
		super();
		cartas= crearMazo(composicionMazo);		
	}

	private void setCartas(Vector<Carta> mazo) {
		cartas = mazo;
	}

	@Override
	public void insertarCarta(Carta cartaAInsertar) {
		cartas.add(cartaAInsertar);
		// cartas.push(cartaAInsertar);
	}

	@Override
	public Carta extraerCarta(int indice) {
		// return cartas.pop();
		return cartas.remove(indice);
	}

	/**
	 * Mezcla el mazo
	 */
	public void mezclar() {
		int cartasRestantes = MAX_CARTAS - 1;
		Vector<Carta> temporal = null;
		Random random = new Random();
		while (cartasRestantes >= 0) {
			// Elijo una carta al azar entre 0 y las cartas restantes(39 porque 40 es
			// posicion invalida)
			// y decrementara hasta 0
			temporal.add(cartas.get(random.nextInt(cartasRestantes)));
			cartasRestantes--;
		}
		setCartas(temporal);
	}

	/**
	 * 
	 * @return true si el mazo ya no tiene cartas
	 */
	public boolean estaVacio() {
		return cartas.isEmpty();
	}

	/**
	 * Crea un mazo a partir del JSON indicado
	 * 
	 * @param composicionMazo
	 * @return mazo de cartas
	 */
	public Vector<Carta> crearMazo(JSONArray composicionMazo) {
		Vector<Carta> nuevoMazo = new Vector<Carta>();		
		HashMap<Integer, Carta> listaDeCartas = cargarListaDeCartas();
		
		JSONObject cartaBuscada = null;
		int idCartaBuscada=-1;
		int cantCopias=0;
		
		for(int i= 0; i < composicionMazo.length(); i++) {
			try {
				cartaBuscada = composicionMazo.getJSONObject(i);
				idCartaBuscada = cartaBuscada.getInt("id");
				cantCopias = cartaBuscada.getInt("cant");
			} catch (JSONException e) {
				e.printStackTrace();
			} 
			
			//Repito tantas veces como copias se requiera
			for(int j=1; j<=cantCopias;j++) {
				nuevoMazo.add(listaDeCartas.get(idCartaBuscada));
			}
		}
		
		return nuevoMazo;

	}

	/**
	 * Busca y retorna una carta que se encuentre en el listado de las cartas
	 * @param listaDeCartas
	 * @param id
	 * @return carta buscada
	 */
	private Carta buscarCarta(HashMap<Integer, Carta> listaDeCartas, int id) {
		Carta carta = null;
		carta = listaDeCartas.get(id);
		return carta;
	}

	/**
	 * Lee del archivo a un hashmap
	 * 
	 * @return un hashMap con todas las cartas existentes su clave es el id de la
	 *         carta
	 */
	private HashMap<Integer, Carta> cargarListaDeCartas() {

		String archivoFuente = "listaDeCartas.dat";
		ObjectInputStream ois = null;
		HashMap<Integer, Carta> inventarioDeCartas = new HashMap<>();
		try {
			ois = new ObjectInputStream(new FileInputStream(archivoFuente));
			Carta c1 = null;
			//Mientras haya objetos para leer dentro del objectInputStream
			//lo agrego al hashmap con el id de la carta y la carta
			while ((c1 = (Carta) ois.readObject()) != null) {
				inventarioDeCartas.put(c1.getId(), c1);
			}
		} catch (EOFException eof) {
			System.out.println("Fin de lectura del archivo.\n");
		} catch (IOException e) {
			System.out.println(" Anomalía en el flujo de salida " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException er) {
				er.printStackTrace();
			}
		}
		return inventarioDeCartas;
	}
}
