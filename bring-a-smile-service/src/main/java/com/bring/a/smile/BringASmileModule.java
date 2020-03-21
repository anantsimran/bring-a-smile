package com.bring.a.smile;

import com.bring.a.smile.config.BringASmileConfiguration;
import com.bring.a.smile.resources.TestResource;
import com.bring.a.smile.service.TestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;

public class BringASmileModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TestResource.class);
        bind(TestService.class).in(Scopes.SINGLETON);
    }

    @Provides
    public ObjectMapper getObjectMapper(BringASmileConfiguration configuration) {
        return new ObjectMapper();
    }

}
