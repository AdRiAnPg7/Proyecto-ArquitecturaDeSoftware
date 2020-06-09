package Logic;

public class LineaTelefonica {
	private int numero;
	String nombre;
	IPlan plan;
	
	public LineaTelefonica (int numero, IPlan plan, String nombre) {
		this.numero = numero;
		this.plan = plan;
		this.nombre = nombre;
	}
	
	public LineaTelefonica (int numero, IPlan plan) {
		this.numero = numero;
		this.plan = plan;
		this.nombre = null;
	}
	
	public LineaTelefonica (int numero) {
		this.numero = numero;
		this.plan = null;
		this.nombre = null;
	}
	
	public void aniadirNumero(int numero) {
		this.numero = numero;
	}
	
	public int obtenerNumero() {
		return this.numero;
	}

	public void aniadirPlan(IPlan plan) {
		this.plan = plan;
	}
	public IPlan obtenerPlan() {
		return this.plan;
	}
	
	public String obtenerPlanString() {
		return this.plan.obtenerNombre();
	}
	public float obtenerTarifaDelPlan(CDR CDR) {
		return plan.obtenerTarifa(CDR);
	}
	
	public void aniadirNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String obtenerNombre() {
		return this.nombre;
	}
}
