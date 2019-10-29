package org.acme.relationships.boundary;

import org.acme.relationships.entity.Child;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.enterprise.context.RequestScoped;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("parents")
@NoCache
@RequestScoped
public class ParentResource {

    @Context
    ResourceContext resourceContext;

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
    @Path("{name}")
    public Child findChild(@PathParam("name") String name) {
        return Child.findByName(name);
    }

    @Path("children")
    @Transactional
    public ChildResource childResource() {
        return resourceContext.initResource(new ChildResource());
    }
}