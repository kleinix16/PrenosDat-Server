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
public class DataDAO extends AbstractDAO<Data> {

    /**
     * Vytvorenie spojenie s databazou
     * @param sessionFactory
     */
    public DataDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    /**
     *  Vymazanie dat z databazy
     * @param temphum
     */
    public void delete(Data temphum){
        currentSession().delete(temphum);
    }

    /**
     * Najdenie dat v databaze pomocou ID
     * @param id
     * @return
     */
    public Optional<Data> findById(Long id) {
        if (id != null) {
            return Optional.ofNullable(get(id));
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     *  Vytvorenie dat v databaze
     * @param data
     * @return
     */
    public Data create(Data data) {
        return persist(data);
    }

    /**
     * Vyhladanie vsetkych dat z databazy
     * @return
     */
    public List<Data> findAll() {
        return list(namedQuery("sk.fri.uniza.microservice.Data.findAll"));
    }

}