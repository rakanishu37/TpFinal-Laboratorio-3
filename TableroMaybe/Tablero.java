package TableroMaybe;

import java.awt.Image;
import javax.swing.ImageIcon;

import javax.swing.*;

public class Tablero extends JFrame //implements ActionListener
{
	public static final int DIM_POKE_X = 240;
	public static final int DIM_POKE_Y = 330;
	
	private JLabel iconoPokemonAtacante, iconoPokemonDefensor;
	private JButton boton1, boton2, boton3, boton4;
	
	private Tablero()
	{
		
		setTitle("POKEMON TCG 3.0 viturro ATR perro.");
		setLayout(null);
		
		boton1 = new JButton("Aca iria mi ataque 2 atacante");
		boton1.setBounds(104, 610, 230, 70);										// USAR ESTASS MEDIDAS DE REFERENCIA										
		add(boton1);
		
		boton2 = new JButton("Aca iria mi ataque 1 atacante");
		boton2.setBounds(104, 530, 230, 70);
		add(boton2);		
		
		boton3 = new JButton("Aca iria mi ataque 2 defensor");
		boton3.setBounds(104, 250, 230, 70);										
		add(boton3);
		
		boton4 = new JButton("Aca iria mi ataque 1 defensor");
		boton4.setBounds(104, 170, 230, 70);										
		add(boton4);

		ImageIcon rawActivoA = new ImageIcon(getClass().getResource("/Imagenes/Venusaur.png"));
		Image imagenActivoA = rawActivoA.getImage();
		ImageIcon iconoEscaladoAtacante = new ImageIcon(imagenActivoA.getScaledInstance(DIM_POKE_X, DIM_POKE_Y, Image.SCALE_SMOOTH));
		iconoPokemonAtacante = new JLabel();
		iconoPokemonAtacante.setBounds(100, 375, DIM_POKE_X, DIM_POKE_Y);							//SET DE FUNCIONES PARA IMAGEN ATACANTE
		iconoPokemonAtacante.setIcon(iconoEscaladoAtacante);
		add(iconoPokemonAtacante);
	
		
		ImageIcon rawActivoD = new ImageIcon(getClass().getResource("/Imagenes/Venusaur.png"));
		Image imagenActivoD = rawActivoD.getImage();
		ImageIcon iconoEscaladoDefensor = new ImageIcon(imagenActivoD.getScaledInstance(DIM_POKE_X, DIM_POKE_Y, Image.SCALE_SMOOTH));
		iconoPokemonDefensor = new JLabel();
		iconoPokemonDefensor.setBounds(100, 25, DIM_POKE_X, DIM_POKE_Y);							//SET DE FUNCIONES PARA IMAGEN DEFENSOR
		iconoPokemonDefensor.setIcon(iconoEscaladoDefensor);
		add(iconoPokemonDefensor);
		
		
		
	}
		
		
	public static void main (String args[])
	{
		Tablero tablero = new Tablero();
		tablero.setBounds(200, 100, 450, 768);					// LAS DIMENSIONES DEL TABLERO: DISTANCIA EN X, DISTANCIA EN Y, ANCHO, LARGO.
		tablero.setVisible(true);								// PARA QUE SE VEA LA INTERFAZ.
		tablero.setLocationRelativeTo(null);					// PARA QUE LA INTERFAZ ESTE EN EL MEDIO CUANDO SE CREA, IGNORA X e Y
		tablero.setResizable(false);							// PARA QUE NO PUEDA MODIFICAR LAS DIMENSIONES
	}

}




