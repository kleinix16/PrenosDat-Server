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
public class DataListView extends View{

    private List<Data> datas;

    public List<Data> getDatas() {
        return datas;
    }
    
    public DataListView(List<Data> temphums) {
        super("list.ftl");
        this.datas = temphums;
    }
}