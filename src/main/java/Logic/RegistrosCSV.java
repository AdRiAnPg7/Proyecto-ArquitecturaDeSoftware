package Logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;

public class RegistrosCSV implements ICDRRepositorio {
	
	ArrayList <CDR> CDRs  = new ArrayList<CDR>();
	ArrayList <LineaTelefonica> usuarios  = new ArrayList<LineaTelefonica>();
	public RegistrosCSV(){
		
	}
	
	
	public void leerCDRs() {
		
		Path filePath = Paths.get("C:\\Users\\Adrian\\eclipse-workspace\\ArquitecturaSoftware\\CDRs.csv");	
		try {
			BufferedReader br = Files.newBufferedReader(filePath);
			String linea ="";
			
			while((linea = br.readLine())!=null) {
				String[] datosDeLinea = linea.split(",");
			
				var telfOrigen = new LineaTelefonica(Integer.parseInt(datosDeLinea[0]));
				var telfDestino = new LineaTelefonica(Integer.parseInt(datosDeLinea[1]));
				var FechaLlamada = datosDeLinea[2];
				var HoraInicioLlamada = LocalTime.parse(datosDeLinea[3]);
				var DuracionLlamada = LocalTime.parse(datosDeLinea[4]);
				
				CDR temporal = new CDR(telfOrigen,telfDestino,HoraInicioLlamada,DuracionLlamada, FechaLlamada);
				
				CDRs.add(temporal);
			}
		} catch(Exception e){
			System.err.println("No se encontro archivo aaaaaa");
		}
	}
	
	public void mostrarCDRs() {
		for (CDR CDR: CDRs) {
		    CDR.mostrarCDR();
		}
	}
	
	
	public void cargarPlanATelefonos() {
		Path filePath = Paths.get("C:\\Users\\Adrian\\eclipse-workspace\\ArquitecturaSoftware\\Telefonos.csv");

		try {
			BufferedReader br = Files.newBufferedReader(filePath);
			String linea ="";
			
			while((linea = br.readLine())!=null) {
				String[] datosDeLinea = linea.split(",");
				
				asignarPlan(datosDeLinea);
			}
		} catch(Exception e){
			System.err.println("No se encontro archivo eee");
		}
	}

	private void asignarPlan(String[] datosDeLinea) {
		
		
		if("PlanPrePago".equals(datosDeLinea[2]) ) {
			for (CDR CDR: CDRs) {
			    if(String.valueOf(CDR.obtenerNumeroDelTelefonoOrigen()).equals(datosDeLinea[1])) {
			    	var tarifa = new TarifaHorarios();
			    	var plan = new PlanPrePago();
			    	plan.aniadirTarifa(tarifa);
			    	CDR.telefonoOrigen.aniadirPlan(plan);
			    	
			    	var nombre = datosDeLinea [0];
			    	var telefono = Integer.parseInt(datosDeLinea [1]);
			    	var lineaTemporal = new LineaTelefonica(telefono,plan,nombre);
			    	usuarios.add(lineaTemporal);
			    }
			}
		}
		else if("PlanPostPago".equals(datosDeLinea[2]) ) {
			for (CDR CDR: CDRs) {
			    if(String.valueOf(CDR.obtenerNumeroDelTelefonoOrigen()).equals(datosDeLinea[1])) {
			    	var tarifa = new TarifaNormal();
			    	var plan = new PlanPostPago();
			    	plan.aniadirTarifa(tarifa);
			    	CDR.telefonoOrigen.aniadirPlan(plan);
			    	
			    	var nombre = datosDeLinea [0];
			    	var telefono = Integer.parseInt(datosDeLinea [1]);
			    	var lineaTemporal = new LineaTelefonica(telefono,plan,nombre);
			    	usuarios.add(lineaTemporal);
			    }
			}
		}
		else if("PlanWow".equals(datosDeLinea[2])) {
			for (CDR CDR: CDRs) {
			    if(String.valueOf(CDR.obtenerNumeroDelTelefonoOrigen()).equals(datosDeLinea[1])) {
			    	var tarifa = new TarifaAmigo();
			    	var plan = new PlanWow();
			    	tarifa.aniadirTelefonosAmigos(Integer.parseInt(datosDeLinea[3]));
			    	plan.aniadirTarifa(tarifa);
			    	CDR.telefonoOrigen.aniadirPlan(plan);
			    	
			    	var nombre = datosDeLinea [0];
			    	var telefono = Integer.parseInt(datosDeLinea [1]);
			    	var lineaTemporal = new LineaTelefonica(telefono,plan,nombre);
			    	usuarios.add(lineaTemporal);
			    }
			}
		}
	}
	
	
	public void guardarCDRsCalculados() {
		
        File file = new File("C:\\Users\\Adrian\\eclipse-workspace\\ArquitecturaSoftware\\CDRsCalculados.csv");
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            for (CDR CDR: CDRs) {
            	guardarResumenLlamada(pw, CDR);
            	pw.flush();
            }
            pw.close();
            
        } catch (Exception e) {
        	System.err.println("No se pudo crear archivo");
        }
	}

	private void guardarResumenLlamada(PrintWriter pw, CDR CDR) {
		
		var llamada = new Llamada();
		llamada.aniadirCDR(CDR);
		int telefonoOrigen = CDR.obtenerNumeroDelTelefonoOrigen();
		int telefonoDestino = CDR.obtenerNumeroDelTelefonoDestino();
		String Fecha = CDR.obtenerFecha();
		String HoraInicio = CDR.obtenerHoraInicioLlamada().toString();
		String DuracionLlamada = CDR.obtenerDuracionLlamada().toString();
		String Costo = String.valueOf(llamada.calcularCosto());
		
		pw.println(telefonoOrigen + "," + telefonoDestino + "," + Fecha + ","+ HoraInicio + "," + DuracionLlamada + "," + Costo );
	}


	@Override
	public ArrayList<CDR> obtenerCDRs() {
		return this.CDRs;
	}
	
	@Override
	public ArrayList<LineaTelefonica> obtenerUsuarios() {
		return this.usuarios;
	}
}
