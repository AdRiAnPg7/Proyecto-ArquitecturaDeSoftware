package Test;
import Logic.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestTelefono {
	
	int numeroTelefono = 77556644;
	IPlan planPrePago = new PlanPrePago();
	IPlan planPostPago = new PlanPostPago();
	IPlan planWow= new PlanWow();
	
	
	@Test
	void obtenerNumeroTelefonico() {
		LineaTelefonica telefono = new LineaTelefonica(numeroTelefono,null);
		assertEquals(numeroTelefono, telefono.obtenerNumero());
	}
	
	@Test
	void obtenerPlanPrePago() {
		LineaTelefonica telefono = new LineaTelefonica(numeroTelefono,planPrePago);
		assertEquals("PlanPrePago", telefono.obtenerPlan().obtenerNombre());
	}
	
	@Test
	void obtenerPlanPostPago() {
		LineaTelefonica telefono = new LineaTelefonica(numeroTelefono,planPostPago);
		assertEquals("PlanPostPago", telefono.obtenerPlan().obtenerNombre());
	}
	
	@Test
	void obtenerPlanWow() {
		LineaTelefonica telefono = new LineaTelefonica(numeroTelefono,planWow);
		assertEquals("PlanWow", telefono.obtenerPlan().obtenerNombre());
	}


}
