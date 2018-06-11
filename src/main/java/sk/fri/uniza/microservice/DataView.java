/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.fri.uniza.microservice;

import io.dropwizard.views.View;

/**
 *
 * @author klein
 */
public class DataView extends View {

    private final Data temphum;

    /**
     * Vyber dat z databazy
     * @return
     */
    public Data getData() {
        return temphum;
    }

    /**
     * Generovanie webovej stranky pre zobrazenie jednotlivych dat
     * @param temphum
     */
    public DataView(Data temphum) {
        super("Daat.ftl");
        this.temphum = temphum;
    }
}