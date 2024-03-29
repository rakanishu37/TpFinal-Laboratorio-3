package Extras;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonUtiles {
	public static void grabar(JSONArray array,String nombreArchivo) {
		try {
			FileWriter file = new FileWriter(nombreArchivo);
			file.write(array.toString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void grabar(JSONObject obj,String nombreArchivo) {
		try {
			FileWriter file = new FileWriter(nombreArchivo);
			file.write(obj.toString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String leer(String nombreArchivo) 
	{
		String contenido = "";
		try 
		{
			contenido = new String(Files.readAllBytes(Paths.get(nombreArchivo)));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return contenido;
	}
}
