package Logic;

public interface IPlan {
	
	public void aniadirTarifa(ITarifaStrategy tarifa);
	public float obtenerTarifa(CDR CDR);
	public String obtenerNombre();
}
