package TableroMaybe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import javax.swing.ImageIcon;

import javax.swing.*;

public class Tablero extends JFrame implements ActionListener
{
	private JLabel label1, labelimagen, imagensola;
	private JButton boton1, boton2;
	
	private Tablero()
	{
		setLayout(null);
		label1 = new JLabel("Zona de Prueba");
		label1.setBounds(20,20,200,100);
		add(label1);
		
		boton1 = new JButton("cerrar");
		boton1.setBounds(600, 50, 200, 50);
		add(boton1);
		boton1.addActionListener(this);
		
		boton2 = new JButton("imprime algo");
		boton2.setBounds(600, 110, 200, 50);
		add(boton2);
		boton2.addActionListener(this);
		setTitle("EL POKEMON TCG PAPA");
		
		ImageIcon imagenV = new ImageIcon(getClass().getResource("/Imagenes/Venusaur.png"));
		
		Image imagen = imagenV.getImage();
		ImageIcon iconoEscalado = new ImageIcon(imagen.getScaledInstance(220, 300, Image.SCALE_SMOOTH));
			
		labelimagen = new JLabel();
		labelimagen.setBounds(100, 100, 220, 300);
		labelimagen.setIcon(iconoEscalado);
		add(labelimagen);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == boton1)
		{
																//			ACA TENDIRAN QUE IR LOS METODOS, QUE LLAME
			System.exit(0);										// ESTO HACE QUE CIERRE EL PROGRAMA
		}
		
		if(e.getSource() == boton2)
		{
			label1.setText("apretaste el boton 2 wachin");		// SETTEXT CAMBIA EL TEXTO QUE ESTA EN EL LABEL					// CON ESTO REFRESCARIAMOS LA VIDA POR EJEMPLO
		}
	}
	
	public static void main (String args[])
	{
		Tablero tablero = new Tablero();
		tablero.setBounds(200, 100, 1024, 768);					// LAS DIMENSIONES DEL TABLERO: DISTANCIA EN X, DISTANCIA EN Y, ANCHO, LARGO.
		tablero.setVisible(true);								// PARA QUE SE VEA LA INTERFAZ.
		tablero.setLocationRelativeTo(null);					// PARA QUE LA INTERFAZ ESTE EN EL MEDIO CUANDO SE CREA, IGNORA X e Y
		tablero.setResizable(false);							// PARA QUE NO PUEDA MODIFICAR LAS DIMENSIONES
	}

}




