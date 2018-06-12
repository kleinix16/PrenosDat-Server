/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.fri.uniza.microservice;

/**
 *
 * @author klein, simon
 */
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import java.util.Map;
import org.dhatim.dropwizard.jwt.cookie.authentication.JwtCookieAuthBundle;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

//import com.example.helloworld.resources.HelloWorldResource;
//import com.example.helloworld.health.TemplateHealthCheck;
//skuska

/**
 *
 * @author klein
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    static SessionFactory buildSessionFactory;

    private final HibernateBundle<HelloWorldConfiguration> hibernateBundle = new HibernateBundle<HelloWorldConfiguration>(Data.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(HelloWorldConfiguration t) {
            return t.getDataSourceFactory();
        }
    };

    /**
     *  Hlavny program
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        new HelloWorldApplication().run(args);
        
        /*
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory buildSessionFactory = new MetadataSources(registry).addResource("hibernate.cfg.xml").buildMetadata().buildSessionFactory();
        try (Session session = buildSessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new UserInDB("admin","heslo","admin"));
            session.save(new UserInDB("user","klein","user"));
            session.getTransaction().commit();
        }*/
    }

    /**
     *  Nazov aplikacie
     * @return
     */
    @Override
    public String getName() {
        return "hello-world";
    }

    /**
     *  Inicializacia
     * @param bootstrap
     */
    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new ViewBundle<HelloWorldConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(HelloWorldConfiguration configuration) {

                return super.getViewConfiguration(configuration); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    /**
     * Hlavna cast programu
     * @param configuration
     * @param environment
     */
    @Override
    public void run(HelloWorldConfiguration configuration,
            Environment environment) {

        final DataDAO dao = new DataDAO(hibernateBundle.getSessionFactory());

        final DataResource temphumResource = new DataResource(dao);
        final SensorResource sensorResource = new SensorResource(dao);

        final TemplateHealthCheck healthCheck
                = new TemplateHealthCheck(configuration.getTemplate());

        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new ExampleAuthenticator())
                .setAuthorizer(new ExampleAuthorizer())
                .setRealm("SUPER SECRET STUFF")
                .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

        environment.healthChecks().register("template", healthCheck);
        
        environment.jersey().register(temphumResource);
        environment.jersey().register(sensorResource);

    }

}