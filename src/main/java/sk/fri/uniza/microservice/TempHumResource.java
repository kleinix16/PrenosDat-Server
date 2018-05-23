/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.fri.uniza.microservice;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import io.dropwizard.views.View;
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
@Path("/temphum")
@RolesAllowed("BASIC_USER")
public class TempHumResource {

    private final TempHumDAO temphumDAO;

    public TempHumResource(TempHumDAO temphumDAO) {
        this.temphumDAO = temphumDAO;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public TempHumView getTempHum(@PathParam("id") LongParam id) {
        Optional<TempHum> result = temphumDAO.findById(id.get());

        if (result.isPresent()) {
            return new TempHumView(result.get());
        }

        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/edit/{id}")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public TempHumAddEditView getEditForm(@PathParam("id") LongParam id) {
        Optional<TempHum> result = temphumDAO.findById(id.get());

        if (result.isPresent()) {
            return new TempHumAddEditView(result.get());
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/add")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public View getAddForm() {
        return new View("temphumAddEdit.ftl", StandardCharsets.UTF_8) {
        };
    }
    
    
    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork
    public TempHum addNewData(@FormParam("date") String date, @FormParam("temp") float temp, @FormParam("hum") int hum) {
        return temphumDAO.create(new TempHum(date,temp,hum));
    }
    

    @POST
    @Path("/edit")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork
    public TempHumView editTempHum(@FormParam("id") String _id, @FormParam("date") String date, @FormParam("temp") float temp, @FormParam("hum") int hum  /* TempHum temphum*/) {
        Optional<TempHum> result = temphumDAO.findById(Long.parseLong(_id));
        if (result.isPresent()) {
            result.get().setDate(date);
            return new TempHumView(result.get());
        } else {
            TempHum create = temphumDAO.create(new TempHum(date,temp,hum));
            return new TempHumView(create);
        }
    }
    

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public TempHum createTempHum(TempHum temphum) {
        return temphumDAO.create(temphum);
    }

    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public TempHumListView deleteTempHum(@PathParam("id") LongParam id) {
        Optional<TempHum> result = temphumDAO.findById(id.get());
        if (result.isPresent()) {
            temphumDAO.delete(result.get());
            return new TempHumListView(temphumDAO.findAll());
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public TempHum deleteTempHum2(@PathParam("id") LongParam id) {
        Optional<TempHum> result = temphumDAO.findById(id.get());
        if (result.isPresent()) {
            temphumDAO.delete(result.get());
            return result.get();
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public TempHumListView getTempHum() {
        return new TempHumListView(temphumDAO.findAll());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<TempHum> listTempHums() {
        return temphumDAO.findAll();
    }
}