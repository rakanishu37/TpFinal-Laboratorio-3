package Modelos;

public class Energia extends Carta {
	private String tipo; 

	/**
	 * Constructor para crear el costo de un ataque
	 * @param tipo
	 */
	public Energia(Energia e)
	{
		super((Carta)e);
		setTipo(e.getTipo());
	}
	public Energia(String tipo) {
		super();
		setTipo(tipo);
	}
	
	public Energia(String id,String nombre, String tipo) {
		super(id,nombre);
		setTipo(tipo);		
	}

	public String getTipo() {
		return tipo;
	}

	private void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public boolean equals(Object obj) {
		boolean rta = false;
		if (obj != null) {
			if (obj instanceof Energia) {
				Energia e = (Energia) obj;
				if (getId() == e.getId()) {
					rta = true;
				}
			}
		}
		return rta;
	}
	
	public String mostrarInfoReducido() {
		return getTipo(); 
	}
	
	@Override
	public String toString() {
		return "Energia\n"+super.toString()+"Tipo: " + getTipo()+"\n" ;
	}

	/**
	 * Equipa al pokemon objetivo con esta energia 
	 * @param p 
	 */
	public void serEquipada(Pokemon p) {
		//Se invoca al equipar energia del pokemon p y se pasa este objeto invocante del metodo
		//como parametro al equiparEnergia
		
		p.equiparEnergia(this);
	}
}