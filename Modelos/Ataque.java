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
	 * @param energiaEquipada la energia equipadas por el pokemon
	 * @return true si tiene las energias suficientes para pagar el costo
	 */
	public boolean comprobarEnergia(ArrayList<Energia> energiaEquipada) {
		boolean bandera = true;
		boolean banderaIncolora = false;
		ArrayList<Energia> auxCosto = getCosto();
		// Variable auxiliar
		Energia auxEnergia = null;
		// Verifico si la cantidad de elementos de energiaEquipada es menor que la
		// cantidad de elementos del costo
		if (energiaEquipada.size() < auxCosto.size()) {
			bandera = false;
		} 
		else {
			if (bandera) {
				int i = 0;
				// se copia las energias equipadas al temporal
				ArrayList<Energia> temporal = energiaEquipada;
				//Recorro la coleccion costo preguntando si el elemento i esta en las energia equipadas

				//En caso de que no este corta el ciclo y significa que no tiene las energias
				//para ejecutar este ataque
				while (i < auxCosto.size() && bandera == true) {
					
					auxEnergia = auxCosto.get(i);
					if (auxEnergia.getTipo().equals("Incolora")) {
						banderaIncolora = true;
					}

					if (banderaIncolora == false) {
						if (temporal.contains(auxEnergia)) {
							temporal.remove(auxEnergia);
						}
						i++;
					} 
					else {
						bandera = false;
					}
				}
			}
		}
		if (banderaIncolora) {
			return true;
		} 
		else {
			return bandera;
		}
	}

	@Override
	public String toString() {
		String msg = "Nombre: " + getNombre() + "\nTipo: " + getTipo() + "\nEnergias necesarias: \n";
		for (int i = 0; i < costo.size(); i++) {
			msg += costo.get(i).getTipo() + " ";
		}
		msg += "\ndamage=" + getDamage();
		return msg;
	}
	}