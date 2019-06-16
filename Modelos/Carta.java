package Modelos;

import java.io.Serializable;

public abstract class Carta implements Serializable
{
	//ultimoId va a contener el id disponbile
	private static int idDisponible = 0;
	private int id;
	private String nombre;
	
	public Carta(String nombre) 
	{
		super();
		setId(idDisponible);
		idDisponible++;
		setNombre(nombre);
	}
	
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	
	
	
	
}
