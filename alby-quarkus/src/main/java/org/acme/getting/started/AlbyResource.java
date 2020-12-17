package org.acme.getting.started;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/alby")
public class AlbyResource {

    @Inject
    AlbyService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/")
    public String connectionLevelBalancing(@PathParam String name) {
        return service.getEndpoint("Connection");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/multicast")
    public String multicastBalancing(@PathParam String name) {
        return service.getEndpoint("Multicast");
    }
}