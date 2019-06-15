package Interfaces;

public interface moverCarta<T> {
	void insertarCarta(T carta); //public abstract ya implicito por ser interfaz
	T extraerCarta(int indice);
}
