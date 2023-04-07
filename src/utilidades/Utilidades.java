package utilidades;

public class Utilidades {

	public static int numeros_aleatorios(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
