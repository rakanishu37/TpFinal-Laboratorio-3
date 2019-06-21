package Interfaces;
/**
 * 
 * Interfaz para manipular colecciones que contengan cartas
 *
 * @param <T>
 */
public interface moverCarta<T> {
	/**
	 * Inserta una carta en la coleccion
	 * @param carta
	 */
	void insertarCarta(T carta); //public abstract ya implicito por ser interfaz
	/**
	 * Quita y retorna una carta de la coleccion de la 
	 * @param indice posicion de la carta a quitar
	 * @return 
	 */
	T extraerCarta(int indice);
}
