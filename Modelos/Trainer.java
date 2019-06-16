package Modelos;

public class Trainer extends Carta
{
	private String palabraClave;//Capas un arreglo y que se interprete afuera
	private String descripcion;
		
	public Trainer(String nombre, String Clave, String texto) 
	{
		super(nombre);
		setPalabraClave(Clave);
		setDescripcion(texto);
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
	
	/*menu que haga los controles
	public void usar()
	{		
	}
	 */
}
