package Modelos;

import java.io.Serializable;

public abstract class Carta implements Serializable {
	private String id;
	private String nombre;

	/**
	 * Este constructor es para crear variables del tipo Energia pero que no afecte
	 * al indice de las cartas
	 * 
	 */
	public Carta(Carta cartaACopiar)
	{
		super();
		setId(cartaACopiar.getId());
		setNombre(cartaACopiar.getNombre());
	}
	public Carta() {
		super();
		setId("");
		setNombre("");
	}

	public Carta(String id, String nombre) {
		super();
		setId(id);
		setNombre(nombre);
	}

	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "id: " + getId() + "\nNombre: " + getNombre() + "\n";
	}
}
