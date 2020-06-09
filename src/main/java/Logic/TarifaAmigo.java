package Logic;
import java.util.Vector;

public class TarifaAmigo implements ITarifaStrategy {
	
	private static final int _LIMITE_DE_AMIGOS = 4;

	float tarifaBsMinuto = 0.99f;
	
	Vector<Integer> numerosAmigos = new Vector <Integer>();
	int cantidadNumerosAmigos = 0;

	public float calcularTarifa(CDR CDR) {
		if (numerosAmigos.contains(CDR.obtenerNumeroDelTelefonoDestino())) 
			tarifaBsMinuto = 0.00f;
		
		return tarifaBsMinuto;
	}
	

	public void aniadirTelefonosAmigos(int nuevoAmigo) {
		if(cantidadNumerosAmigos < _LIMITE_DE_AMIGOS) {
			numerosAmigos.add(nuevoAmigo);
			cantidadNumerosAmigos++;
		}	
	}

}
