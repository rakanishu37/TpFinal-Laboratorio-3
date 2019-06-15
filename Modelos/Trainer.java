package Modelos;

public class Trainer extends Carta
{
	private String palabraClave;
	private String descripcion;
	
	
	
	public Trainer(int id, String nombre, String Clave, String texto) 
	{
		super(id, nombre);
		
	}


	public String getPalabraClave() 
	{
		return palabraClave;
	}

	private void setPalabraClave(String Clave)
	{
		palabraClave = Clave;
	}

	public String getDescripcion() 
	{
		return descripcion;
	}

	private void setDescripcion(String texto) 
	{
		descripcion = texto;
	}
	
	/** 
	 * menu que haga los controles 
	 */
	
	public void usar()
	{
		
		
		
	}

}
