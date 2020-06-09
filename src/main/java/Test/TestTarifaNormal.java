package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import Logic.*;

class TestTarifaNormal {

	int numeroOrigen = 11111111;
	int numeroDestino = 88888888;
	ITarifaStrategy tarifaNormal = new TarifaNormal();
	IPlan planPrePago = new PlanPrePago();
	IPlan planPostPago = new PlanPostPago();
	IPlan planWow= new PlanWow();
	LocalTime horaInicioLlamada = LocalTime.parse("00:02:35");
	LocalTime Duracion1Minuto = LocalTime.parse("00:01:00");
	LocalTime Duracion2Minutos = LocalTime.parse("00:02:00");
	LocalTime Duracion10Minutos = LocalTime.parse("00:10:00");
	LocalTime Duracion1MinutoY10Segundos = LocalTime.parse("00:01:10");
	LocalTime Duracion2MinutosY30Segundos = LocalTime.parse("00:02:30");
	LocalTime Duracion10MinutosY55Segundos = LocalTime.parse("00:10:55");
	LineaTelefonica telefonoOrigen = new LineaTelefonica(numeroOrigen,null);
	LineaTelefonica telefonoDestino = new LineaTelefonica(numeroDestino,null);
	Llamada llamada = new Llamada() ;
	
	
	@Test
	void calcularTarifaHorarioNormal() {
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion1Minuto);
		
		planPrePago.aniadirTarifa(tarifaNormal);
		telefonoOrigen.aniadirPlan(planPrePago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 1.00f, llamada.calcularCosto());

	}
	
	@Test
	void calcularTarifaHorarioNormal2() {
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion10Minutos);
		
		planPostPago.aniadirTarifa(tarifaNormal);
		telefonoOrigen.aniadirPlan(planPostPago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 10.00f, llamada.calcularCosto());
	}
	
	@Test
	void calcularTarifaHorarioNormal3() {
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion2Minutos);
		
		planPostPago.aniadirTarifa(tarifaNormal);
		telefonoOrigen.aniadirPlan(planPostPago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 2.00f, llamada.calcularCosto());
	}
	
	@Test
	void calcularTarifaHorarioNormal4() {
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion1MinutoY10Segundos);
		
		planPrePago.aniadirTarifa(tarifaNormal);
		telefonoOrigen.aniadirPlan(planPrePago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 1.17f, llamada.calcularCosto());

	}
	
	@Test
	void calcularTarifaHorarioNormal5() {
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion10MinutosY55Segundos);
		
		planPostPago.aniadirTarifa(tarifaNormal);
		telefonoOrigen.aniadirPlan(planPostPago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 10.92f, llamada.calcularCosto());
	}
	
	@Test
	void calcularTarifaHorarioNormal6() {
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion2MinutosY30Segundos);
		
		planPostPago.aniadirTarifa(tarifaNormal);
		telefonoOrigen.aniadirPlan(planPostPago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 2.50f, llamada.calcularCosto());
	}
}
