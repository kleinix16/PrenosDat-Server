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
public class TempHumAddEditView extends View {

    private final TempHum temphum;

    public TempHum getTempHum() {
        return temphum;
    }

    public TempHumAddEditView(TempHum temphum) {
        super("temphumAddEdit.ftl");
        this.temphum = temphum;
    }  
}