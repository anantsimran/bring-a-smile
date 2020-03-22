package com.bring.a.smile;

import com.bring.a.smile.config.BringASmileConfiguration;
import com.bring.a.smile.dao.AssociationDao;
import com.bring.a.smile.resources.CoordinatorResource;
import com.bring.a.smile.resources.GoodwillRequestResource;
import com.bring.a.smile.resources.TestResource;
import com.bring.a.smile.resources.VolunteerResource;
import com.bring.a.smile.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;

public class BringASmileModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TestResource.class);
        bind(CoordinatorResource.class);
        bind(GoodwillRequestResource.class);
        bind(VolunteerResource.class);

        bind(TestService.class).in(Scopes.SINGLETON);
        bind(AssociationService.class).in(Scopes.SINGLETON);
        bind(CoordinatorService.class).in(Scopes.SINGLETON);
        bind(VolunteerService.class).in(Scopes.SINGLETON);
        bind(GoodWillRequestService.class).in(Scopes.SINGLETON);


        bind(AssociationDao.class).in(Scopes.SINGLETON);
    }

    @Provides
    public ObjectMapper getObjectMapper(BringASmileConfiguration configuration) {
        return new ObjectMapper();
    }

}
