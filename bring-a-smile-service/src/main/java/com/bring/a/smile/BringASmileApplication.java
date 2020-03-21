package com.bring.a.smile;

import com.bring.a.smile.auth.User;
import com.bring.a.smile.auth.UserAuthenticator;
import com.bring.a.smile.auth.UserAuthorizer;
import com.bring.a.smile.config.BringASmileConfiguration;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class BringASmileApplication extends Application<BringASmileConfiguration> {


    public static void main(String[] args) throws Exception {
        new BringASmileApplication().run(args);
    }


    @Override
    public void initialize(Bootstrap<BringASmileConfiguration> bootstrap) {
        GuiceBundle<BringASmileConfiguration> guiceBundle = GuiceBundle.<BringASmileConfiguration>newBuilder()
                .addModule(new BringASmileModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(BringASmileConfiguration.class)
                .build();

        bootstrap.addBundle(new SwaggerBundle<BringASmileConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                    BringASmileConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
        bootstrap.addBundle(guiceBundle);


    };



    @Override
    public void run(BringASmileConfiguration configuration, Environment environment) throws Exception {
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // DO NOT pass a preflight request to down-stream auth filters
        // unauthenticated preflight requests should be permitted by spec
        cors.setInitParameter(CrossOriginFilter.CHAIN_PREFLIGHT_PARAM, Boolean.FALSE.toString());


        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new UserAuthenticator())
                        .setAuthorizer(new UserAuthorizer())
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }
}
