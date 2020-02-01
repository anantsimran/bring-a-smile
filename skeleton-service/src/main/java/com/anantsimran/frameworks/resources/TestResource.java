package com.anantsimran.frameworks.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/test/")
@Api
public class TestResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response helloWorld(){
        return Response.status(200).entity("{Hello: \"Hi\"}").build();
    }


}
