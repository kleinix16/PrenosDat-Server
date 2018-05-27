/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.fri.uniza.microservice;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import io.dropwizard.views.View;
import static java.lang.System.console;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author klein, simon
 */
@Path("/data")
@RolesAllowed("BASIC_USER")
public class DataResource {

    private final DataDAO dataDAO;

    public DataResource(DataDAO temphumDAO) {
        this.dataDAO = temphumDAO;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public DataView getTempHum(@PathParam("id") LongParam id) {
        Optional<Data> result = dataDAO.findById(id.get());

        if (result.isPresent()) {
            return new DataView(result.get());
        }

        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/edit/{id}")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public DataAddEditView getEditForm(@PathParam("id") LongParam id) {
        Optional<Data> result = dataDAO.findById(id.get());

        if (result.isPresent()) {
            return new DataAddEditView(result.get());
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/add")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public View getAddForm() {
        return new View("addEdit.ftl", StandardCharsets.UTF_8) {
        };
    }
    
    
    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork
    public void addNewData(@FormParam("date") String date, @FormParam("temp") int temp, @FormParam("hum") int hum) {
        dataDAO.create(new Data(date,temp,hum));
    }
    

    @POST
    @Path("/edit")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork
    public DataView editTempHum(@FormParam("id") String _id, @FormParam("date") String date, @FormParam("temp") int temp, @FormParam("hum") int hum  /* TempHum temphum*/) {
                
        Optional<Data> result = dataDAO.findById(Long.parseLong(_id));
        if (result.isPresent()) {
            result.get().setDate(date);
            result.get().setTemp(temp);
            result.get().setHum(hum);
            return new DataView(result.get());
        } else {
            Data create = dataDAO.create(new Data(date,temp,hum));
            return new DataView(create);
        }
    }
    

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Data createTempHum(Data temphum) {
        return dataDAO.create(temphum);
    }

    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public DataListView deleteTempHum(@PathParam("id") LongParam id) {
        Optional<Data> result = dataDAO.findById(id.get());
        if (result.isPresent()) {
            dataDAO.delete(result.get());
            return new DataListView(dataDAO.findAll());
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Data deleteTempHum2(@PathParam("id") LongParam id) {
        Optional<Data> result = dataDAO.findById(id.get());
        if (result.isPresent()) {
            dataDAO.delete(result.get());
            return result.get();
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public DataListView getTempHum() {
        return new DataListView(dataDAO.findAll());
    }
    
    @GET
    @Path("/chart")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public DataChartView getChart() {
        return new DataChartView(dataDAO.findAll());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<Data> listTempHums() {
        return dataDAO.findAll();
    }
}