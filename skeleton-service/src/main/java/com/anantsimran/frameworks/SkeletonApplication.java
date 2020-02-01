package com.anantsimran.frameworks;

import com.anantsimran.frameworks.config.SkeletonConfiguration;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class SkeletonApplication extends Application<SkeletonConfiguration> {


    public static void main(String[] args) throws Exception {
        new SkeletonApplication().run(args);
    }


    @Override
    public void initialize(Bootstrap<SkeletonConfiguration> bootstrap) {
        GuiceBundle<SkeletonConfiguration> guiceBundle = GuiceBundle.<SkeletonConfiguration>newBuilder()
                .addModule(new SkeletonModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(SkeletonConfiguration.class)
                .build();

        bootstrap.addBundle(new SwaggerBundle<SkeletonConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                    SkeletonConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
        bootstrap.addBundle(guiceBundle);


    };



    @Override
    public void run(SkeletonConfiguration configuration, Environment environment) throws Exception {
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
    }
}
