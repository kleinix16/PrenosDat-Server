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

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ExampleAuthenticator implements Authenticator<BasicCredentials, User> {
    /**
     * Valid users with mapping user -> roles
     */
    private static final Map<String, Set<String>> VALID_USERS = ImmutableMap.of(
        "guest", ImmutableSet.of(),
        "user", ImmutableSet.of("BASIC_USER"),
        "admin", ImmutableSet.of("ADMIN", "BASIC_USER")
    );

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        boolean correctLogin = false;
        
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
        return Optional.empty();
    }
}