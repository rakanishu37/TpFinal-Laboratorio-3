package Extras;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Modelos.Carta;
import Modelos.Energia;
import Modelos.Trainer;
import Modelos.Pokemon;
import Modelos.Ataque;

/**
 * Esta clase manipula y hace las acciones necesarias para crear los archivos fuentes
 * necesarios para el funcionamiento del programa
 *
 */
public class ManagerBiblotecaCartas {
	/**
	 * Uso interno a esta clase
	 * 
	 * @return un listado con todas las cartas
	 */
	private final static ArrayList<Carta> jsonAColeccion() {
		/// Del Json con a una coleccion
		JSONObject biblotecaDeCartas = null;
		ArrayList<Carta> listaDeCartas = new ArrayList<>();
		try {
			biblotecaDeCartas = new JSONObject(JsonUtiles.leer("BiblotecaDeCartas.json"));
		} catch (JSONException e1) {
			System.out.println("No se encuentra el archivo fuente de las cartas");
			e1.printStackTrace();
		}

		// Energias
		try {
			JSONArray listaDeEnergias = biblotecaDeCartas.getJSONArray("Energias");

			for (int i = 0; i < listaDeEnergias.length(); i++) {
				JSONObject elemento = listaDeEnergias.getJSONObject(i);

				String id = elemento.getString("id");
				String nombre = elemento.getString("nombre");
				String tipo = elemento.getString("tipo");

				Carta c = new Energia(id,nombre, tipo);
				listaDeCartas.add(c);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// Trainers
		try {
			JSONArray listaDeTrainers = biblotecaDeCartas.getJSONArray("Trainers");

			for (int i = 0; i < listaDeTrainers.length(); i++) {
				JSONObject elemento = listaDeTrainers.getJSONObject(i);

				String id = elemento.getString("id");
				
				String nombre = elemento.getString("nombre");

				String clave = elemento.getString("palabraClave");

				int num = elemento.getInt("numeros");

				String descripcion = elemento.getString("descripcion");

				Carta c = new Trainer(id, nombre, clave, num, descripcion);
				listaDeCartas.add(c);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// Pokemons
		try {
			JSONArray listaDePokemons = biblotecaDeCartas.getJSONArray("Pokemons");

			for (int i = 0; i < listaDePokemons.length(); i++) {
				JSONObject elemento = listaDePokemons.getJSONObject(i);
				// Atributos del Pokemon
				String id = elemento.getString("id");
				String nombre = elemento.getString("nombre");
				int vidaMaxima = elemento.getInt("vidaMaxima");
				String tipo = elemento.getString("tipo");
				int costoRetirada = elemento.getInt("costoRetirada");
				String debilidad = elemento.getString("debilidad");
				String resistencia = elemento.getString("resistencia");

				// Atributos del ataque 1
				JSONObject ataque = elemento.getJSONObject("ataque1");
				String nombreAtaque = ataque.getString("nombreAtaque");
				int damage = ataque.getInt("damage");

				ArrayList<Energia> costoAtk = new ArrayList<Energia>();
				JSONArray costo = ataque.getJSONArray("costo");

				for (int k = 0; k < costo.length(); k++) {
					costoAtk.add(new Energia(costo.getString(k)));
				}

				Ataque atk1 = new Ataque(nombreAtaque, tipo, costoAtk, damage);

				// Atributos del ataque 2
				Ataque atk2 = null;
				if (!elemento.isNull("ataque2")) {
					ataque = elemento.getJSONObject("ataque2");
					nombreAtaque = ataque.getString("nombreAtaque");
					damage = ataque.getInt("damage");
					costoAtk = new ArrayList<Energia>();
					costo = ataque.getJSONArray("costo");
					for (int k = 0; k < costo.length(); k++) {
						costoAtk.add(new Energia(costo.getString(k)));
					}
					atk2 = new Ataque(nombreAtaque, tipo, costoAtk, damage);
				}

				Carta c = new Pokemon(id,nombre, vidaMaxima, tipo, costoRetirada, debilidad, resistencia, atk1, atk2);
				listaDeCartas.add(c);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listaDeCartas;
	}

	/**
	 * Uso interno a esta clase
	 * @param listaDeCartas la cual se convertira en el archivo fuente de las cartas
	 */
	private static void coleccionAArchivo(ArrayList<Carta> listaDeCartas) {
		// crear el archivo en base a la coleccion
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("listaDeCartas.dat"));
			for (Carta c : listaDeCartas) {
				oos.writeObject(c);
			}
		} catch (IOException e) {
			System.out.println(" Anomalía en el flujo de salida " + e.getMessage());
		} finally {
			try {
				oos.close();
			} catch (IOException er) {
				er.printStackTrace();
			} finally {
				System.out.println("Se guardo el archivo fuente");
			}
		}
	}

	/**
	 * Crea el archivo fuente con las cartas a partir del json BiblotecaDeCartas
	 */
	public static void crearArchivoFuente(){
		ArrayList<Carta> listadoTemp = jsonAColeccion();
		coleccionAArchivo(listadoTemp);
	}
	
	/*
	public static String listarCartas(HashMap<String,Carta> listaDeCartas) { 
		//Para recorrer el mapa
			StringBuilder msg = new StringBuilder();
			Iterator i = listaDeCartas.entrySet().iterator();
			while(i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				msg.append("Id: "+me.getKey()+"\nCarta: "+me.getValue());
			}
			return msg.toString();			
	}*/
}