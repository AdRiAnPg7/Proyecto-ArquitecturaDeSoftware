package Logic;
import java.time.LocalTime;

public class Llamada {
	private static final double _FACTOR_DE_REDONDEO = 100.0;
	private static final float _FACTOR_DE_CONVERSION_A_MINUTOS = 60.00f;
	float minutos, costo;
	CDR CDR;
	
	public Llamada() {
		this.CDR = null;
	}
	
	public Llamada(CDR CDR) {
		this.CDR = CDR;
	}
	
	public void aniadirCDR(CDR CDR) {
		this.CDR= CDR;
	}
	
	public float calcularCosto(){
		minutos = obtenerCantidadMinutos(CDR.tiempoDuracionLlamada);
		costo = CDR.obtenerTarifa(CDR);
		costo = redondearCosto (minutos * costo);
		return costo;
	}
	
	public float obtenerCantidadMinutos(LocalTime tiempoDuracionLlamada) {
		return (tiempoDuracionLlamada.getHour() * _FACTOR_DE_CONVERSION_A_MINUTOS) + tiempoDuracionLlamada.getMinute() + (tiempoDuracionLlamada.getSecond() / _FACTOR_DE_CONVERSION_A_MINUTOS); 
	}
	
	protected float redondearCosto(float tarifa) {
		return (float) (Math.round(tarifa * _FACTOR_DE_REDONDEO)/_FACTOR_DE_REDONDEO);
	}

}
