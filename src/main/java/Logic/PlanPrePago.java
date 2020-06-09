package Logic;

public class PlanPrePago implements IPlan {

	public ITarifaStrategy tarifa;

	public String obtenerNombre(){
		return "PlanPrePago";
	}
	
	public void aniadirTarifa(ITarifaStrategy tarifa) {
		this.tarifa = tarifa;
	}

	public float obtenerTarifa(CDR CDR) {
		return tarifa.calcularTarifa(CDR);
	}
}