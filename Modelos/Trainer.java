package Modelos;

public class Trainer extends Carta
{
	private String palabraClave;//Capas un arreglo y que se interprete afuera
	private String descripcion;
	private int [] numero;
	public Trainer(String nombre, String Clave,int[] num, String descripcion) 
	{
		super(nombre);
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

	public int[] getNumero() 
	{
		return numero;
	}
	
	private void setNumero(int[] num)
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
	
	/*menu que haga los controles
	public void usar()
	{		
	}
	 */
	
	@Override
	public String toString() {
		int[] num = getNumero();
		String msg= "Trainer\n"+super.toString()+ "palabraClave=" + palabraClave + "\nnumero="; 
		for(int i= 0; i< num.length; i++) {
			msg+= num[i]+ " ";
		}
		msg+="\ndescripcion=" + descripcion + "\n";
		return msg;
	}
}
