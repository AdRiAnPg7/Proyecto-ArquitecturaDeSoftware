package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import Logic.*;

class TestTarifaHorarios {
	
	int numeroOrigen = 11111111;
	int numeroDestino = 88888888;
	ITarifaStrategy tarifaHorario = new TarifaHorarios();
	IPlan planPrePago = new PlanPrePago();
	IPlan planPostPago = new PlanPostPago();
	IPlan planWow= new PlanWow();
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
	void calcularTarifaHorarioReducido() {
		var horaInicioLlamada = LocalTime.parse("00:02:35");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion1Minuto);
		
		planPrePago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPrePago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 0.95f, llamada.calcularCosto());

	}
	
	@Test
	void calcularTarifaHorarioReducido2() {
		var horaInicioLlamada = LocalTime.parse("00:39:50");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion10Minutos);
		
		planPostPago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPostPago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 9.50f, llamada.calcularCosto());
	}
	
	@Test
	void calcularTarifaHorarioReducido3() {
		var horaInicioLlamada = LocalTime.parse("00:39:50");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion2Minutos);
		
		planPostPago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPostPago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 1.90f, llamada.calcularCosto());
	}
	
	@Test
	void calcularTarifaHorarioReducido4() {
		var horaInicioLlamada = LocalTime.parse("00:02:35");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion1MinutoY10Segundos);
		
		planPrePago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPrePago);
		llamada.aniadirCDR(CDR);
		
		
		assertEquals( 1.11f, llamada.calcularCosto());

	}
	
	@Test
	void calcularTarifaHorarioReducido5() {
		var horaInicioLlamada = LocalTime.parse("00:39:50");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion10MinutosY55Segundos);
		
		planPostPago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPostPago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 10.37f, llamada.calcularCosto());
	}
	
	@Test
	void calcularTarifaHorarioReducido6() {
		var horaInicioLlamada = LocalTime.parse("00:39:50");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion2MinutosY30Segundos);
		
		planPostPago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPostPago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 2.38f, llamada.calcularCosto());
	}
	
	@Test
	void calcularTarifaHorarioSuperReducido() {
		var horaInicioLlamada = LocalTime.parse("05:00:00");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion1Minuto);
		
		planPrePago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPrePago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 0.70f, llamada.calcularCosto());

	}
	
	@Test
	void calcularTarifaHorarioSuperReducido2() {
		var horaInicioLlamada = LocalTime.parse("06:00:00");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion2Minutos);
		
		planPostPago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPostPago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 1.40f, llamada.calcularCosto());
	}
	
	@Test
	void calcularTarifaHorarioSuperReducido3() {
		var horaInicioLlamada = LocalTime.parse("03:00:00");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion10Minutos);
		
		planPostPago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPostPago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 7.00f, llamada.calcularCosto());
	}
	
	@Test
	void calcularTarifaHorarioSuperReducido4() {
		var horaInicioLlamada = LocalTime.parse("05:00:00");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion1MinutoY10Segundos);
		
		planPrePago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPrePago);
		llamada.aniadirCDR(CDR);
	
		assertEquals(0.82f, llamada.calcularCosto());

	}
	
	@Test
	void calcularTarifaHorarioSuperReducido5() {
		var horaInicioLlamada = LocalTime.parse("06:00:00");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion2MinutosY30Segundos);
		
		planPostPago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPostPago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 1.75f, llamada.calcularCosto());
	}
	
	@Test
	void calcularTarifaHorarioSuperReducido6() {
		var horaInicioLlamada = LocalTime.parse("03:00:00");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion10MinutosY55Segundos);
		
		planPostPago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPostPago);
		llamada.aniadirCDR(CDR);
	
		assertEquals( 7.64f, llamada.calcularCosto());
	}
	
	
	@Test
	void calcularTarifaNormal() {
		var horaInicioLlamada = LocalTime.parse("12:00:00");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion1Minuto);
		
		planPrePago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPrePago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 1.45f, llamada.calcularCosto());

	}
	
	@Test
	void calcularTarifaNormal2() {
		var horaInicioLlamada = LocalTime.parse("16:00:00");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion2Minutos);
		
		planPostPago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPostPago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 2.90f, llamada.calcularCosto());
	}
	
	@Test
	void calcularTarifaNormal3() {
		var horaInicioLlamada = LocalTime.parse("18:00:00");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion10Minutos);
		
		planPostPago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPostPago);
		llamada.aniadirCDR(CDR);
		
		assertEquals( 14.50f, llamada.calcularCosto());
	}
	@Test
	void calcularTarifaNormal4() {
		var horaInicioLlamada = LocalTime.parse("12:00:00");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion1MinutoY10Segundos);
		
		planPrePago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPrePago);
		llamada.aniadirCDR(CDR);
	
		assertEquals( 1.69f, llamada.calcularCosto());

	}
	
	@Test
	void calcularTarifaNormal5() {
		var horaInicioLlamada = LocalTime.parse("16:00:00");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion2MinutosY30Segundos);
		
		planPostPago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPostPago);
		llamada.aniadirCDR(CDR);

		assertEquals( 3.63f, llamada.calcularCosto());
	}
	
	@Test
	void calcularTarifaNormal6() {
		var horaInicioLlamada = LocalTime.parse("18:00:00");
		var CDR= new CDR (telefonoOrigen,telefonoDestino,horaInicioLlamada,Duracion10MinutosY55Segundos);
		
		planPostPago.aniadirTarifa(tarifaHorario);
		telefonoOrigen.aniadirPlan(planPostPago);
		llamada.aniadirCDR(CDR);
	
		assertEquals( 15.83f, llamada.calcularCosto());
	}
	

}
