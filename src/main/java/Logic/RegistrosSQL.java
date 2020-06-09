package Logic;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;


public class RegistrosSQL implements ICDRRepositorio {
	
	ArrayList <CDR> CDRs  = new ArrayList<CDR>();
	Connection conexion;
	String CDRsPath = "C:\\Users\\Adrian\\eclipse-workspace\\ArquitecturaSoftware\\Telefonia.db";
	String TelefonosPath = "C:\\Users\\Adrian\\eclipse-workspace\\ArquitecturaSoftware\\Telefonos.db";
	
	public void connect(String Path){
		String filePath = Path;
		try {
			System.out.println("aaaaaaaa");
			System.out.println("jdbc:sqlite:" + filePath);
			conexion = DriverManager.getConnection("jdbc:sqlite:" + filePath);
			System.out.println("eeeeeeee");
			if( conexion != null) {
				System.out.println("Conectado");
			}
		} catch (SQLException exception){
			System.out.println("No se Ha Podido Conectar a La Base De Datos\n" + exception.getMessage());
		}
	}
	
	public void close() {
		try {
			conexion.close();
		}catch (SQLException exception){
			System.out.println("No se Ha Podido Cerrar la Base De Datos\n" + exception.getMessage());
		}
	}
	
	@Override
	public ArrayList<CDR> obtenerCDRs() {
		return CDRs;
	}
	
	
	@Override
	public void leerCDRs() {
		connect(CDRsPath);
		ResultSet result = null;
		try {
			PreparedStatement st = conexion.prepareStatement("select * from cdr");
			result = st.executeQuery();
			while (result.next()) {
				var planPostPago = new PlanPostPago ();
				planPostPago.aniadirTarifa(new TarifaNormal() );
				var telfOrigen = new LineaTelefonica(Integer.parseInt(result.getString("telefonoOrigen")), planPostPago);
				System.out.println(telfOrigen.obtenerNumero());
				var telfDestino = new LineaTelefonica(Integer.parseInt(result.getString("telefonoDestino")), null);
				System.out.println(telfDestino.obtenerNumero());
				var HoraInicioLlamada = LocalTime.parse(result.getString("horaInicioLlamada"));
				System.out.println(HoraInicioLlamada);
				var DuracionLlamada = LocalTime.parse(result.getString("duracionLlamada"));
				System.out.println(DuracionLlamada);
				CDR temporal = new CDR(telfOrigen,telfDestino,HoraInicioLlamada,DuracionLlamada);
				
				CDRs.add(temporal);
			}
			
		} catch(SQLException exception){
			System.out.println(exception.getMessage());
		}
		close();
	}

	@Override
	public void mostrarCDRs() {
		for (CDR CDR: CDRs) {
		    System.out.println(CDR.obtenerNumeroDelTelefonoOrigen() + " " +
		    				   CDR.obtenerNumeroDelTelefonoDestino() + " " +
		    				   CDR.obtenerHoraInicioLlamada() + " " +
		    				   CDR.obtenerDuracionLlamada());
		}
	}

	@Override
	public void cargarPlanATelefonos() {
		connect(TelefonosPath);
		ResultSet result = null;
		try {
			PreparedStatement st = conexion.prepareStatement("select * from Telefonia");
			result = st.executeQuery();
			while (result.next()) {
				asignarPlan(result);
			}
			
		} catch(SQLException exception){
			System.out.println(exception.getMessage());
		}
		close();
		
	}

	private void asignarPlan(ResultSet result) throws NumberFormatException, SQLException {
		
		if("PlanPrePago".equals(result.getString("Plan")) ) {
			for (CDR CDR: CDRs) {
			    if(String.valueOf(CDR.obtenerNumeroDelTelefonoOrigen()).equals(result.getString("Telefono"))) {
			    	var tarifa = new TarifaHorarios();
			    	var plan = new PlanPrePago();
			    	plan.aniadirTarifa(tarifa);
			    	CDR.telefonoOrigen.aniadirPlan(plan);
			    }
			}
		}
		else if("PlanPostPago".equals(result.getString("Plan")) ) {
			for (CDR CDR: CDRs) {
			    if(String.valueOf(CDR.obtenerNumeroDelTelefonoOrigen()).equals(result.getString("Telefono"))) {
			    	var tarifa = new TarifaNormal();
			    	var plan = new PlanPrePago();
			    	plan.aniadirTarifa(tarifa);
			    	CDR.telefonoOrigen.aniadirPlan(plan);
			    }
			}
		}
		else if("PlanWow".equals(result.getString("Plan"))) {
			for (CDR CDR: CDRs) {
			    if(String.valueOf(CDR.obtenerNumeroDelTelefonoOrigen()).equals(result.getString("Telefono"))) {
			    	var tarifa = new TarifaAmigo();
			    	var plan = new PlanWow();
			    	tarifa.aniadirTelefonosAmigos(Integer.parseInt(result.getString("TelefonoAmigo")));
			    	plan.aniadirTarifa(tarifa);
			    	CDR.telefonoOrigen.aniadirPlan(plan);
			    }
			}
		}
		
	}

	@Override
	public void guardarCDRsCalculados() {
		        
		        int indice = 1;
		   
	        try{
	        	connect(CDRsPath); 
	        	System.out.println("ANtes del FOR");
	        	for (CDR CDR: CDRs) {
	        		System.out.println("Dentro del FOR");
	        		System.out.println(CDR.obtenerCosto());
	        		  PreparedStatement posted = conexion.prepareStatement(
	  	            		"UPDATE cdr SET costo=" +CDR.obtenerCosto()+" WHERE id = " +indice);
	        		
	        		  posted.executeUpdate();
	        		   indice = indice + 1;
	  	           
	    		}
	          
	        } catch(Exception e){System.out.println(e);}
	        finally {
	            System.out.println("Insert Completed.");
	        }
	        close();

	}

	@Override
	public ArrayList<LineaTelefonica> obtenerUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

}
