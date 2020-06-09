package Logic;

import java.util.ArrayList;

public interface ICDRRepositorio {
	
	public ArrayList <CDR> obtenerCDRs();
	public ArrayList <LineaTelefonica> obtenerUsuarios();
	public void leerCDRs();
	public void mostrarCDRs();
	public void cargarPlanATelefonos();
	public void guardarCDRsCalculados();
	
}
