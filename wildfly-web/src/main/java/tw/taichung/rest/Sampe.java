package tw.taichung.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import tw.taichung.ejb.SampleService;
import tw.taichung.jpa.Sample;

@Path("sample")
public class Sampe {

	@Inject
	private SampleService service;
	
	
	@GET
	@Path("add")
	public String add(@QueryParam("name") String name){
		service .add(name);
		return "SUCCESS";
	}
	
	@GET
    @Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	public Sample find(@QueryParam("id") int id){
		return service.find(id);
	}
	
}
