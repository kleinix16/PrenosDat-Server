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
public class DataAddEditView extends View {

    private final Data temphum;

    /**
     *  Vyhlada data v databaze a vracia ich ako navratovu hodnotu
     * @return temphum
     */
    public Data getData() {
        return temphum;
    }

    /**
     *  Vygeneruje staticku stranku pre vkladanie a editaciu Dat
     * @param temphum
     */
    public DataAddEditView(Data temphum) {
        super("addEdit.ftl");
        this.temphum = temphum;
    }  
}