package Logic;

import java.util.ArrayList;

public class CentralTelefonica {

	private ICDRRepositorio repositorioCDRs;
	
	public CentralTelefonica() {
		this.repositorioCDRs = null;
	}
	
	public void asignarPersistencia(ICDRRepositorio registroCDRs) {
		this.repositorioCDRs = registroCDRs;
	}
	
	public ArrayList<CDR> obtenerCDRs() {
		return repositorioCDRs.obtenerCDRs();
	}

	public void guardarRegistrosCDRs() {
		repositorioCDRs.guardarCDRsCalculados();
		repositorioCDRs.mostrarCDRs();
	}
	
	public void almacenarPlanATelefonos( ) {
		repositorioCDRs.cargarPlanATelefonos();
	}
	
	public void almacenarCDRs( ) {
		repositorioCDRs.leerCDRs();
	}
	
	public ArrayList<LineaTelefonica> obtenerUsuarios( ) {
		return repositorioCDRs.obtenerUsuarios();
	}
}
