package org.acme.relationships.boundary;

import org.acme.relationships.entity.Child;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ChildResource {
    @GET
    public Response welcome() {
        return Response.ok().build();
    }

    @POST
    @Transactional
    public Response saveChild(Child child) {
        child.persist();
        return Response.status(201).build(); //for sake of demo no location
    }

    @GET
    @Path("/{name}")
    public Child findChild(@PathParam("name") String name) {
        return Child.findByName(name);
    }
}