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
 * @author klein
 */
public class DataChartView extends View{
    private List<Data> datas;

    /**
     * generovanie listu dat z databazy
     * @return datas
     */
    public List<Data> getDatas() {
        return datas;
    }
    
    /**
     *  Generovanie statickej stranky pre zobrazenie grafov
     * @param temphums
     */
    public DataChartView(List<Data> temphums) {
        super("chart.ftl");
        this.datas = temphums;
    }
    
}
