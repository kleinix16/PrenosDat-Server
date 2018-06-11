/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.fri.uniza.microservice;

import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author klein
 */
@Path("/new")
public class SensorResource {
    
    private final String sensorID = "QSlkjdfdfs85dfsd4vdqqwAAAs8aasf47sf5ads7a";
    private final DataDAO dataDAO;

    public SensorResource(DataDAO temphumDAO) {
        this.dataDAO = temphumDAO;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork
    public String addNewData(@FormParam("date") String date, @FormParam("temp") int temp, @FormParam("hum") int hum,@FormParam("ID") String id) {
        if(id.equals(sensorID) ){
            dataDAO.create(new Data(date,temp,hum));
            return "Data was add";
        }else{
            return "Wrong ID";
        }
                
    }
}
