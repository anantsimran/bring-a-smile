package com.bring.a.smile;

import com.bring.a.smile.api.IESDao;
import com.bring.a.smile.api.IESSearchDao;
import com.bring.a.smile.dao.*;
import com.bring.a.smile.managed.ESCLusterManaged;
import com.bring.a.smile.resources.CoordinatorResource;
import com.bring.a.smile.resources.GoodwillRequestResource;
import com.bring.a.smile.resources.TestResource;
import com.bring.a.smile.resources.VolunteerResource;
import com.bring.a.smile.service.*;
import com.bring.a.smile.utils.DalUtils;
import com.bring.a.smile.utils.ESUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import org.elasticsearch.client.Client;

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

        bind(ESCLusterManaged.class).in(Scopes.SINGLETON);
    }

    @Provides
    Client getESClient(ESCLusterManaged esCLusterManaged){
        return esCLusterManaged.getClient();
    }

    @Provides
    IESDao getESDao(ESCLusterManaged escLusterManaged){
        return new ESDao(escLusterManaged.getClient());
    }

    @Provides
    IESSearchDao getESearchDao(ESCLusterManaged escLusterManaged){
        return new ESSearchDao(escLusterManaged.getClient());
    }

    @Provides
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Provides
    public ESUtils getESUtils(ObjectMapper objectMapper){
        return new ESUtils(objectMapper);
    }

    @Provides
    public DalUtils getDalUtils(){
        return new DalUtils();
    }

    @Provides
    public AssociationDao getAssociationDao(IESDao esDao, IESSearchDao esSearchDao, ESUtils esUtils, DalUtils dalUtils){
        return new AssociationDao(esDao, esSearchDao, esUtils, dalUtils);
    }

    @Provides
    public AuthorizationDao getAuthorizationDao(IESDao esDao, IESSearchDao esSearchDao, ESUtils esUtils){
        return new AuthorizationDao(esDao, esSearchDao, esUtils);
    }

    @Provides
    public CoordinatorDao getCoordinatorDao(IESDao esDao, IESSearchDao esSearchDao, ESUtils esUtils){
        return new CoordinatorDao(esDao, esSearchDao, esUtils);
    }

    @Provides
    public GoodwillRequestDao getGoodwillRequestDao(IESDao esDao, IESSearchDao esSearchDao, ESUtils esUtils){
        return new GoodwillRequestDao(esDao, esSearchDao, esUtils);
    }
    @Provides
    public VolunteerDao getVolunteerDao(IESDao esDao, IESSearchDao esSearchDao, ESUtils esUtils, DalUtils dalUtils){
        return new VolunteerDao(esDao, esSearchDao, esUtils, dalUtils);
    }



}
