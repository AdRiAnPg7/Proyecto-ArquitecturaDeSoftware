package Logic;

public class TarifaNormal implements ITarifaStrategy {

	float tarifaBsMinuto= 1.00f;

	public float calcularTarifa(CDR CDR) {
		return tarifaBsMinuto;
	}

}
