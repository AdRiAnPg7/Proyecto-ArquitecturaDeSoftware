package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Logic.*;

class TestPlanPostPago {

	IPlan planPostPago = new PlanPostPago();
	
	@Test
	void obtenerNombre() {
		assertEquals("PlanPostPago", planPostPago.obtenerNombre());
	}
	
}
