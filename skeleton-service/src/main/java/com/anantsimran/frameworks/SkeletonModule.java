package com.anantsimran.frameworks;

import com.anantsimran.frameworks.config.SkeletonConfiguration;
import com.anantsimran.frameworks.resources.TestResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class SkeletonModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TestResource.class);
    }

    @Provides
    public ObjectMapper getObjectMapper(SkeletonConfiguration configuration) {
        return new ObjectMapper();
    }

}
