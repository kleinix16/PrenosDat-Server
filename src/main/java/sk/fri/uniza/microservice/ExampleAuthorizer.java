/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.fri.uniza.microservice;

import io.dropwizard.auth.Authorizer;

/**
 * Basic authorization
 * @author klein
 */
public class ExampleAuthorizer implements Authorizer<User> {

    /**
     * Pridavanie prav uzivatelom
     * @param user
     * @param role
     * @return
     */
    @Override
    public boolean authorize(User user, String role) {
        return user.getRoles() != null && user.getRoles().contains(role);
    }
}