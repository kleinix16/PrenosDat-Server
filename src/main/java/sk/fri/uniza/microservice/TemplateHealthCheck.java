/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.fri.uniza.microservice;

import com.codahale.metrics.health.HealthCheck;

/**
 *  zakladny testovaci subor
 * @author klein
 */
public class TemplateHealthCheck extends HealthCheck {

    private final String template;

    /**
     *
     * @param template
     */
    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    protected Result check() throws Exception {
        final String temphum = String.format(template, "TEST");
        if (!temphum.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}