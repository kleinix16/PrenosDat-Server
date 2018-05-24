/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.fri.uniza.microservice;

import io.dropwizard.views.View;

/**
 *
 * @author klein, simon
 */
public class DataView extends View {

    private final Data temphum;

    public Data getData() {
        return temphum;
    }

    public DataView(Data temphum) {
        super("data.ftl");
        this.temphum = temphum;
    }
}