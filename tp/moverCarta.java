package tp;

public interface moverCarta<T> {
	void insertarCarta(T carta); //public abstract ya implicito por ser interfaz
	T extraerCarta();
}
