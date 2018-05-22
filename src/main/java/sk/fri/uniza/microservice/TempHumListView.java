/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.fri.uniza.microservice;

import io.dropwizard.views.View;
import java.util.List;
/**
 *
 * @author klein, simon
 */
public class TempHumListView extends View{

    private List<TempHum> temphums;

    public List<TempHum> getTempHums() {
        return temphums;
    }
    
    public TempHumListView(List<TempHum> temphums) {
        super("temphumList.ftl");
        this.temphums = temphums;
    }
}