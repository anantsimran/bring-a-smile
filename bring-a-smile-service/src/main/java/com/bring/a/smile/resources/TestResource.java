package com.bring.a.smile.resources;

import com.bring.a.smile.auth.User;
import com.bring.a.smile.service.TestService;
import com.google.inject.Inject;
import io.dropwizard.auth.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/test/")
@Api(value = "Test")
public class TestResource {

    private TestService testService;


    @Inject
    public TestResource(TestService testService) {
        this.testService = testService;
    }

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authorization(value = "basic")
    public Response helloWorld(@Auth User user){
        return Response.status(200).entity( testService.getTestString()).build();
    }


}
