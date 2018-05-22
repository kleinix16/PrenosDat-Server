/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.fri.uniza.microservice;

import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.hibernate.SessionFactory;

/**
 *
 * @author klein, simon
 */
public class TempHumDAO extends AbstractDAO<TempHum> {

    public TempHumDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    public void delete(TempHum temphum){
        currentSession().delete(temphum);
    }

    public Optional<TempHum> findById(Long id) {
        if (id != null) {
            return Optional.ofNullable(get(id));
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }

    public TempHum create(TempHum person) {
        return persist(person);
    }

    public List<TempHum> findAll() {
        return list(namedQuery("sk.fri.uniza.microservice.TempHumf.findAll"));
    }

}