package Logic;

public class PlanPostPago implements IPlan {
	
	public ITarifaStrategy tarifa;
	
	public String obtenerNombre(){
		return "PlanPostPago";
	}

	public void aniadirTarifa(ITarifaStrategy tarifa) {
		this.tarifa = tarifa;
	}
	
	public float obtenerTarifa(CDR CDR) {
		return tarifa.calcularTarifa(CDR);
	}

}
