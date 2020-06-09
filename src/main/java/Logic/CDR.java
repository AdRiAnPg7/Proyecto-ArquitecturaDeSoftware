package Logic;
import java.time.LocalTime;

public class CDR {
	LineaTelefonica telefonoOrigen;
	LineaTelefonica telefonoDestino;
	String fecha;
	LocalTime horaInicioLlamada; 
	LocalTime tiempoDuracionLlamada;
	
	float costo;
	
	public CDR (LineaTelefonica telefonoOrigen, 
				LineaTelefonica telefonoDestino, 
				LocalTime horaInicioLlamada, 
				LocalTime tiempoDuracionLlamada, 
				String fecha) {
		this.telefonoOrigen = telefonoOrigen;
		this.telefonoDestino = telefonoDestino;
		this.horaInicioLlamada = horaInicioLlamada;
		this.tiempoDuracionLlamada = tiempoDuracionLlamada;
		this.fecha = fecha;
	}
	
	
	public CDR (LineaTelefonica telefonoOrigen, 
				LineaTelefonica telefonoDestino, 
				LocalTime horaInicioLlamada, 
				LocalTime tiempoDuracionLlamada) {
		this.telefonoOrigen = telefonoOrigen;
		this.telefonoDestino = telefonoDestino;
		this.horaInicioLlamada = horaInicioLlamada;
		this.tiempoDuracionLlamada = tiempoDuracionLlamada;
		this.fecha = null;
	}
	
	public LineaTelefonica obtenerTelefonoOrigen() {
		return this.telefonoOrigen;
	}
	
	public LineaTelefonica obtenerTelefonoDestino() {
		return this.telefonoDestino;
	}
	
	public LocalTime obtenerHoraInicioLlamada() {
		return this.horaInicioLlamada;
	}
	
	public LocalTime obtenerDuracionLlamada() {
		return this.tiempoDuracionLlamada;
	}
	
	public String obtenerFecha() {
		return this.fecha;
	}
	
	public void aniadirTelefonoOrigen(LineaTelefonica telefono) {
		this.telefonoOrigen = telefono;
	}
	
	public void aniadirTelefonoDestino(LineaTelefonica telefono) {
		this.telefonoDestino = telefono;
	}
	
	public void aniadirHoraInicioLlamada(LocalTime Hora) {
		this.horaInicioLlamada = Hora;
	}
	
	public void aniadirFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public void aniadirDuracionLlamada(LocalTime DuracionLlamada) {
		this.tiempoDuracionLlamada = DuracionLlamada;
	}
	
	public int obtenerNumeroDelTelefonoDestino() {
		return telefonoDestino.obtenerNumero();
	}
	
	public int obtenerNumeroDelTelefonoOrigen() {
		return telefonoOrigen.obtenerNumero();
	}
	
	public float obtenerTarifa(CDR CDR) {
		return telefonoOrigen.obtenerTarifaDelPlan(CDR);
	}
	
	public void mostrarCDR() {
		System.out.println(telefonoOrigen.obtenerNumero() + " " +
							telefonoDestino.obtenerNumero() + " " +
							fecha + " " +
							horaInicioLlamada + " " +
							tiempoDuracionLlamada );
	}
	
	public float obtenerCosto() {
		var CDR = new CDR(telefonoOrigen,telefonoDestino,horaInicioLlamada,tiempoDuracionLlamada);
		var llamada = new Llamada(CDR);
		return llamada.calcularCosto();
	}
	
}
