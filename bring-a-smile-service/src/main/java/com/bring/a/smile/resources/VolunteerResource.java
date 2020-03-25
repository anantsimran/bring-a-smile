package com.bring.a.smile.resources;

import com.bring.a.smile.auth.User;
import com.bring.a.smile.exception.DuplicateEntryException;
import com.bring.a.smile.model.Volunteer;
import com.bring.a.smile.service.AssociationService;
import com.bring.a.smile.service.VolunteerService;
import com.google.inject.Inject;
import io.dropwizard.auth.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




@Slf4j
@Path("/volunteer/")
@Api(value = "Volunteer")
public class VolunteerResource {

    private VolunteerService volunteerService;
    private AssociationService associationService;

    @Inject
    public VolunteerResource(VolunteerService volunteerService, AssociationService associationService) {
        this.volunteerService = volunteerService;
        this.associationService = associationService;
    }

    @RolesAllowed("volunteer")
    @POST
    @Path("login/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Auth User user){
        log.info("Logged in user : " + user);
        return Response.status(200).entity(volunteerService.getLoginMessage(user)).build();
    }

    @PermitAll
    @PUT
    @Path("register/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(Volunteer volunteer){
        log.info("Registering Volunteer: " + volunteer);
        try {
            return Response.status(200).entity(volunteerService.register(volunteer)).build();
        } catch (DuplicateEntryException e) {
            log.error("Volunteer already found: {}", volunteer);
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @RolesAllowed("Volunteer")
    @PUT
    @Path("enroll/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response enroll(@Auth User user, @ApiParam("requestId") String goodwillRequestId){
        try {
            associationService.enroll(user.getId(),goodwillRequestId);
        } catch (DuplicateEntryException e) {
            log.error("Enrollment already found: {}", goodwillRequestId);
            return Response.status(Response.Status.CONFLICT).build();
        }
        return Response.status(200).build();
    }



}
