package com.bring.a.smile.resources;


import com.bring.a.smile.auth.User;
import com.bring.a.smile.exception.DuplicateEntryException;
import com.bring.a.smile.exception.NotFoundException;
import com.bring.a.smile.model.GoodWillRequestCompleteBody;
import com.bring.a.smile.model.GoodwillRequest;
import com.bring.a.smile.model.GoodwillRequestSearchQuery;
import com.bring.a.smile.service.AssociationService;
import com.bring.a.smile.service.GoodWillRequestService;
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
@Path("/goodwillrequest/")
@Api(value = "Goodwill Request")
public class GoodwillRequestResource {

    private AssociationService associationService;
    private GoodWillRequestService goodWillRequestService;

    @Inject
    public GoodwillRequestResource(AssociationService associationService, GoodWillRequestService goodWillRequestService) {
        this.associationService = associationService;
        this.goodWillRequestService = goodWillRequestService;
    }

    @RolesAllowed("coordinator")
    @PUT
    @Path("complete/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response completeGoodwillRequest(@Auth User user, @ApiParam("goodwillRequestCompleteBody") GoodWillRequestCompleteBody goodwillRequestCompleteBody){
        try {
            associationService.completeGoodwillRequest(goodwillRequestCompleteBody);
        } catch (NotFoundException e) {
            log.error("Goodwill request not found: {}", goodwillRequestCompleteBody.getRequestId());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(200).build();
    }

    @PermitAll
    @PUT
    @Path("create/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Auth User user, @ApiParam GoodwillRequest goodwillRequest){
//        goodwillRequest.setCoordinatorId(user.getId());
        try {
            return Response.status(200).entity(goodWillRequestService.createGoodwillRequest(goodwillRequest)).build();
        } catch (DuplicateEntryException e) {
            log.error("Goodwill Request  already found: {}", goodwillRequest);
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @PermitAll
    @POST
    @Path("search/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search( @ApiParam GoodwillRequestSearchQuery searchQuery){
        return Response.status(200).entity(goodWillRequestService.search(searchQuery)).build();
    }
}
