/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.fri.uniza.microservice;


import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import java.util.List;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

/**
 * Basic authentification
 * @author klein
 */
public class ExampleAuthenticator implements Authenticator<BasicCredentials, User> {
    /**
     * Valid users with mapping user -> roles
     */
    private static final Map<String, Set<String>> VALID_USERS = ImmutableMap.of(
        "guest", ImmutableSet.of(),
        "user", ImmutableSet.of("BASIC_USER"),
        "admin", ImmutableSet.of("ADMIN", "BASIC_USER")
    );

    /**
     * Kontolovania pristupu uzivateľov
     * @param credentials
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory buildSessionFactory = new MetadataSources(registry).addResource("hibernate.cfg1.xml").buildMetadata().buildSessionFactory();

        Session session = buildSessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT s from UserInDB s");
        List<UserInDB> user = query.getResultList();
        session.close();

            for (int i = 0; i < user.size(); i++) {
               if (user.get(i).getPassword().equals(credentials.getPassword()) && user.get(i).getUserName().equals(credentials.getUsername())) {
                    return Optional.of(new User(credentials.getUsername(), VALID_USERS.get(user.get(i).getRole())));
                }
            }
        return Optional.empty();
        
        /*boolean correctLogin = false;
        
        if(VALID_USERS.containsKey(credentials.getUsername()))
        {
            switch(credentials.getUsername())
            {
                case "user" : 
                    if("user".equals(credentials.getPassword()))
                    {
                        correctLogin = true;
                    } break;
                case "admin" : 
                    if("admin".equals(credentials.getPassword()))
                    {
                        correctLogin = true;
                    } break;
                    
            }
            if(correctLogin)
            {
                return Optional.of(new User(credentials.getUsername(), VALID_USERS.get(credentials.getUsername())));
            }
            else{
                return Optional.empty();
            }
        }
        return Optional.empty();*/
    }
}