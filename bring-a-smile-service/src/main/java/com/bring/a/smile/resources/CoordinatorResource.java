package com.bring.a.smile.resources;


import com.bring.a.smile.auth.User;
import com.bring.a.smile.service.CoordinatorService;
import com.google.inject.Inject;
import io.dropwizard.auth.Auth;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/coordinator/")
@Api(value = "Coordinator")
public class CoordinatorResource {

    private CoordinatorService coordinatorService;


    @Inject
    public CoordinatorResource(CoordinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }


    @RolesAllowed("coordinator")
    @POST
    @Path("login/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Auth User user){
        log.info("Logged in user : " + user);
        return Response.status(200).entity(coordinatorService.getLoginMessage(user)).build();
    }














}
