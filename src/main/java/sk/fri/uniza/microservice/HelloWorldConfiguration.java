/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.fri.uniza.microservice;

/**
 *
 * @author klein
 * 
 */
import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import io.dropwizard.db.DataSourceFactory;
import java.util.Collections;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.dhatim.dropwizard.jwt.cookie.authentication.JwtCookieAuthConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *  Vytvaranie hlavnej konfiguracie servera
 * @author klein
 */
public class HelloWorldConfiguration extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    
    @NotNull
    private Map<String, Map<String, String>> viewRendererConfiguration = Collections.emptyMap();

    /**
     * 
     * @return
     */
    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    /**
     *
     * @param dataSourceFactory
     */
    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }

    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    /**
     *
     * @return
     */
    @JsonProperty
    public String getTemplate() {
        return template;
    }

    /**
     *
     * @param template
     */
    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     *
     * @return
     */
    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    /**
     *
     * @param name
     */
    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

    /**
     *
     * @return
     */
    @JsonProperty("viewRendererConfiguration")
    public Map<String, Map<String, String>> getViewRendererConfiguration() {
        return viewRendererConfiguration;
    }

    /**
     *
     * @param viewRendererConfiguration
     */
    @JsonProperty("viewRendererConfiguration")
    public void setViewRendererConfiguration(Map<String, Map<String, String>> viewRendererConfiguration) {
        final ImmutableMap.Builder<String, Map<String, String>> builder = ImmutableMap.builder();
        for (Map.Entry<String, Map<String, String>> entry : viewRendererConfiguration.entrySet()) {
            builder.put(entry.getKey(), ImmutableMap.copyOf(entry.getValue()));
        }
        this.viewRendererConfiguration = builder.build();
    }
}