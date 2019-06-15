package cartas;

public abstract class Carta 
{
	private int id;
	private String nombre;
	
	public Carta(int id, String nombre) 
	{
		super();
		setId(id);
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
