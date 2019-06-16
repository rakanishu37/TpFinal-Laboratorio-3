package Modelos;

public class Energia extends Carta {
	private String tipo; 

	public Energia(String nombre, String tipo) {
		super(nombre);
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
}