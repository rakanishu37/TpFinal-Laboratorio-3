
package Modelos;

import java.util.ArrayList;

public class Pokemon extends Carta
{
	
	private int vidaMaxima;
	private int vidaActual;
	private String tipo;
	private int costoRetirada;
	private ArrayList<Energia> energiasOcupadas;
	private String debilidad;
	private String resistencia;
	private Ataque ataque1;
	private Ataque ataque2;
	
	public Pokemon(int Retirada, int id, String nombre, int vidaMaxima, int vidaActual, String tipo, String debilidad, String resistencia, Ataque ataque1, Ataque ataque2) 
	{
		super(id, nombre);
		this.vidaMaxima = vidaMaxima;
		this.vidaActual = vidaActual;
		this.tipo = tipo;
		costoRetirada = Retirada;
		energiasOcupadas = new ArrayList<>();
		this.debilidad = debilidad;
		this.resistencia = resistencia;
		this.ataque1 = ataque1;
		this.ataque2 = ataque2;
	}

	public int getVidaMaxima() 
	{
		return vidaMaxima;
	}

	private void setVidaMaxima(int maxvida) 
	{
		this.vidaMaxima = maxvida;
	}

	public int getVidaActual() 
	{
		return vidaActual;
	}

	private void setVidaActual(int Actual) 
	{
		vidaActual = Actual;
	}

	public String getTipo() 
	{
		return tipo;
	}

	private void setTipo(String Tipo) 
	{
		tipo = Tipo;
	}

	public int getCostoRetirada() 
	{
		return costoRetirada;
	}

	private void setCostoRetirada(int retirada) 
	{
		costoRetirada = retirada;
	}

	public ArrayList<Energia> getEnergiasOcupadas() 
	{
		return energiasOcupadas;
	}

	private void setEnergiasOcupadas(ArrayList<Energia> Ocupadas) 
	{
		energiasOcupadas = Ocupadas;
	}

	public String getDebilidad() 
	{
		return debilidad;
	}

	private void setDebilidad(String debil) 
	{
		debilidad = debil;
	}

	public String getResistencia() 
	{
		return resistencia;
	}

	private void setResistencia(String resistencia) 
	{
		resistencia = resistencia;
	}

	public Ataque getAtaque1() 
	{
		return ataque1;
	}

	private void setAtaque1(Ataque ataque) 
	{
		ataque1 = ataque;
	}

	public Ataque getAtaque2() 
	{
		return ataque2;
	}

	private void setAtaque2(Ataque ataque) 
	{
		this.ataque2 = ataque;
	}
	
	
	
}

