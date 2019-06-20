package Modelos;

public class Trainer extends Carta
{
	private String palabraClave;//Capas un arreglo y que se interprete afuera
	private String descripcion;
	private int numero;
	public Trainer(String id,String nombre, String Clave,int num, String descripcion) 
	{
		super(id,nombre);
		setPalabraClave(Clave);
		setNumero(num);
		setDescripcion(descripcion);
	}

	public String getPalabraClave() 
	{
		return palabraClave;
	}

	private void setPalabraClave(String Clave)
	{
		palabraClave = Clave;
	}

	public int getNumero() 
	{
		return numero;
	}
	
	private void setNumero(int num)
	{
		numero = num;
	}
	
	public String getDescripcion() 
	{
		return descripcion;
	}

	private void setDescripcion(String texto) 
	{
		descripcion = texto;
	}
	
	
	@Override
	public String toString() {
		return"Trainer\n"+super.toString()+ "palabraClave=" + palabraClave + "\nnumero=" + numero
		+ "\ndescripcion=" + descripcion + "\n";
		
	}
}
