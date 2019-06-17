
package Modelos;

import java.util.ArrayList;

public class Pokemon extends Carta {

	private int vidaMaxima;
	private int vidaActual;
	private String tipo;
	private int costoRetirada;
	private ArrayList<Energia> energiasEquipadas;
	private String debilidad;
	private String resistencia;
	private Ataque ataque1;
	private Ataque ataque2;

	public Pokemon(String nombre, int vidaMaxima, String tipo, int retirada, String debilidad,String resistencia,
			Ataque ataque1, Ataque ataque2) {
		super(nombre);
		setVidaMaxima(vidaMaxima);
		setVidaActual(vidaMaxima);
		setTipo(tipo);
		setCostoRetirada(retirada);
		energiasEquipadas = new ArrayList<Energia>();
		setDebilidad(debilidad);
		setResistencia(resistencia);
		setAtaque1(ataque1);
		setAtaque2(ataque2);		
	}

	public int getVidaMaxima() {
		return vidaMaxima;
	}

	private void setVidaMaxima(int maxvida) {
		this.vidaMaxima = maxvida;
	}

	public int getVidaActual() {
		return vidaActual;
	}

	private void setVidaActual(int Actual) {
		vidaActual = Actual;
	}

	public String getTipo() {
		return tipo;
	}

	private void setTipo(String Tipo) {
		tipo = Tipo;
	}

	public int getCostoRetirada() {
		return costoRetirada;
	}

	private void setCostoRetirada(int retirada) {
		costoRetirada = retirada;
	}

	public ArrayList<Energia> getEnergiasEquipadas() {
		return energiasEquipadas;
	}
	
	public String getDebilidad() {
		return debilidad;
	}

	private void setDebilidad(String debil) {
		debilidad = debil;
	}

	public String getResistencia() {
		return resistencia;
	}

	private void setResistencia(String resist) {
		resistencia = resist;
	}

	public Ataque getAtaque1() {
		return ataque1;
	}

	private void setAtaque1(Ataque ataque) {
		ataque1 = ataque;
	}

	public Ataque getAtaque2() {
		return ataque2;
	}

	private void setAtaque2(Ataque ataque) {
		this.ataque2 = ataque;
	}

	/**
	 * Agrega la carta energia que llegar por parametro al pokemon
	 * @param energiaNueva a equipar
	 */
	public void equiparEnergia(Energia energiaNueva) {
		getEnergiasEquipadas().add(energiaNueva);
	}

	/**
	 * 
	 * @param e energia a quitar
	 * @return la energia quitada
	 */
	public Energia desequiparEnergia(Energia e) {
		Energia quitada = e;
		getEnergiasEquipadas().remove(e);
		return quitada;
	}
	
	/**
	 * Baja la vida del pokemon en el daño del ataque, considerando resistencia
	 * o debilidad
	 * @param ataque del pokemon rival
	 */
	public void decrementarVida(Ataque ataque) {
		int vidaPerdida=0;
		String tipoDelAtaque = ataque.getTipo();
		int dmg= ataque.getDamage();
		if (tipoDelAtaque.equals(getDebilidad()))
			vidaPerdida= dmg*2;
		else {
			if (tipoDelAtaque.equals(getResistencia())) {
				if(dmg-30 > 0) {
					vidaPerdida= dmg-30;
				}				
			}
		}
		int nuevaVida= getVidaActual() - vidaPerdida;
		if(nuevaVida < 0) {
			nuevaVida=0;
		}
		setVidaActual(nuevaVida);
	}
	
	/**
	 * 
	 * @param vidaRegenerada cantidad a curar
	 */
	public void aumentarVida(int vidaRegenerada) {
		int actual = getVidaActual();
		int maxima = getVidaMaxima();
		if(actual+vidaRegenerada > maxima) {
			setVidaActual(maxima);
		}
		else {
			int nuevaVida= actual+vidaRegenerada;
			setVidaActual(nuevaVida);
		}
	}
	@Override
	public String toString() {
		String msg= "Pokemon\n"+ super.toString()
				+ "vidaMaxima= " + vidaMaxima 
				+ "\nvidaActual= " + vidaActual 
				+ "\ntipo= " + tipo
				+ "\ncostoRetirada= " + costoRetirada 
				+ "\nenergiasEquipadas= " + energiasEquipadas.toString() 
				+ "\ndebilidad= " + debilidad
				+ "\nresistencia= " + resistencia 
				+"\n------ataque1------\n" + getAtaque1().toString() 
				+"\n-------------------\n";
		if(ataque2!=(null)) {
			msg+= "\n------ataque2------\n" + ataque2.toString()
				 +"\n-------------------\n";
		}		
		return msg;
	}
}
