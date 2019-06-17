package Modelos;

import java.io.Serializable;
import java.util.ArrayList;

public class Ataque implements Serializable {
	private String nombre;
	private String tipo;
	private ArrayList<Energia> costo;
	private int damage;// Mulltiplos de 10

	public Ataque(String nombre, String tipo, ArrayList<Energia> costo, int damage) {
		super();
		setNombre(nombre);
		setTipo(tipo);
		setCosto(costo);
		setDamage(damage);
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public ArrayList<Energia> getCosto() {
		return costo;
	}

	public int getDamage() {
		return damage;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private void setTipo(String tipo) {
		this.tipo = tipo;
	}

	private void setCosto(ArrayList<Energia> costo) {
		this.costo = costo;
	}

	private void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * 
	 * @param energiaEquipada la energia equipada del pokemon
	 * @return true si tiene la energia para usar el ataque
	 */
	public boolean puedeAtacar(ArrayList<Energia> energiaEquipada) {
		return comprobarEnergia(energiaEquipada);
	}

	/**
	 * 
	 * @param energiaEquipada del pokemon
	 * @return true si tiene las energias suficientes para pagar el costo
	 */
	private boolean comprobarEnergia(ArrayList<Energia> energiaEquipada) {
		boolean bandera = true;
		boolean banderaIncolora = false;
		if (energiaEquipada.size() < costo.size()) {
			bandera = false;
		}
		if (bandera) {
			int i = 0;

			ArrayList<Energia> temporal = energiaEquipada;// se copia las energias equipadas al temporal
			while (i < costo.size() && bandera == true) {

				if (costo.get(i).equals("Incoloro")) {
					banderaIncolora = true;
				}

				if (banderaIncolora == false) {
					if (temporal.contains(costo.get(i))) {
						temporal.remove(costo.get(i));
					}
					i++;
				} else {
					bandera = false;
				}
			}
		}
		if (banderaIncolora) {
			return true;
		} else {
			return bandera;
		}
	}
	
	@Override
	public String toString() {
		String msg="nombre=" + nombre + "\ntipo=" + tipo + "\nEnergias necesarias:";
		for(int i= 0; i< costo.size(); i++) {
			msg+= costo.get(i).getTipo()+ " ";
		}
		msg+="\ndamage=" + damage ;
		return msg;
	}
}