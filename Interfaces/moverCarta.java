package Interfaces;
/**
 * 
 * Interfaz para manipular colecciones que contengan cartas
 *
 * @param <T>
 */
public interface moverCarta<T> {
	void insertarCarta(T carta); //public abstract ya implicito por ser interfaz
	T extraerCarta(int indice);
}
