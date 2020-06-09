package Logic;
import java.time.LocalTime;

public class TarifaHorarios implements ITarifaStrategy {
	
	float tarifaBsMinuto= 0.00f;
	boolean resp;
	String horaNormalInical = "06:59:59";
	String horaNormalFinal = "21:00:00";

	String horaSuperReducidoInicial = "00:59:59";
	String horaSuperReducidoFinal = "07:00:00";

	public float calcularTarifa(CDR CDR) {
		if(estaEnHorarioNormal(CDR.horaInicioLlamada))
			tarifaBsMinuto = 1.45f;
		else 
			if(estaEnHorarioSuperReducido(CDR.horaInicioLlamada))
				tarifaBsMinuto =  0.70f;
			else
				tarifaBsMinuto = 0.95f;
				
		return tarifaBsMinuto;
	}
	
	private boolean estaEntre(LocalTime hora, String Inicio, String Fin) {
		LocalTime horaInicial = LocalTime.parse(Inicio) ;
		LocalTime horaFinal = LocalTime.parse(Fin) ;
		
		return hora.isAfter(horaInicial) && hora.isBefore(horaFinal);
	}
	

	private boolean estaEnHorarioNormal(LocalTime hora) {
		resp = estaEntre(hora, horaNormalInical, horaNormalFinal);
		return  resp;
	}

	private boolean estaEnHorarioSuperReducido(LocalTime hora) {
		resp = estaEntre(hora, horaSuperReducidoInicial, horaSuperReducidoFinal);
		return  resp;
	}

}
