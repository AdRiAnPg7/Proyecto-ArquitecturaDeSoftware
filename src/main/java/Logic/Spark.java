package Logic;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class Spark implements IPresentacion {
	
	public static CentralTelefonica central = new CentralTelefonica ();
	
  
	public void iniciarPresentacion() {
		get("/", (request, response) -> {
				Map<String, Object> model = new HashMap<>();
				model.put("Message", "Pagina Principal");
				return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/index.vm"));
			});
		 get("/CDRs", (request, response) -> {
	        	central.guardarRegistrosCDRs();
	        	Map<String, Object> model = new HashMap<>();
	        	model.put("CDRs", central.obtenerCDRs());      
	        	model.put("Title","CDRs");
	        	return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/CDRs/CDRs.vm"));
	        });
		 get("/Usuarios", (request, response) -> {
			  	Map<String, Object> model = new HashMap<>();
	        	model.put("Usuarios", central.obtenerUsuarios());
	        	return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/Usuarios/Usuarios.vm"));
	        });
		 get("/CDRsTarificados", (request, response) -> {
			  	Map<String, Object> model = new HashMap<>();
	        	model.put("CDRs", central.obtenerCDRs());
	        	model.put("Title","CDRs Tarificados");
	        	return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/CDRs/CDRsTarificados.vm"));
	        });
		  get("/Configuracion", (request, response) -> {
	        	Map<String, Object> model = new HashMap<>();
	        	return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/Configuracion/Configuracion.vm"));
	        });
	}
}
