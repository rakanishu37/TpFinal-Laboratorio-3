package app;

import Modelos.Ataque;
import Modelos.Energia;
import Modelos.Jugador;
import Modelos.Mazo;
import Modelos.Pokemon;
import Modelos.Tablero;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import Extras.JsonUtiles;

public class Main {

	public static void main(String[] args) {		
		
		JSONArray mazoJ1=null;
		
		try {
			mazoJ1 = new JSONArray(JsonUtiles.leer("mazoJ1.json"));
		} catch (JSONException e) {
			System.out.println("Archivo json mal formateado");
			e.printStackTrace();
		}
		JSONArray mazoJ2=null;
		
		try {
			mazoJ2 = new JSONArray(JsonUtiles.leer("mazoJ2.json"));
		} catch (JSONException e) {
			System.out.println("Archivo json mal formateado");
			e.printStackTrace();
		}
		
		
		Jugador j1 = new Jugador( new Mazo(mazoJ1));
		Jugador j2 = new Jugador( new Mazo(mazoJ2));
		
		Tablero t= new Tablero(j1,j2);
		
		
		t.jugar();

	}
}