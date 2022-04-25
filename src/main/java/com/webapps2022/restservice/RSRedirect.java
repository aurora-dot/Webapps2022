/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.restservice;

import java.net.URI;
import java.net.URISyntaxException;
import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author blankie
 */
@Singleton
@Path("/")
public class RSRedirect {
    @Context
    UriInfo uriInfo;

    @GET
    @Path("/")
    public Response redirect() throws URISyntaxException {
        return Response.temporaryRedirect(new URI("https://localhost:8181/webapps2022/faces/index.xhtml")).build();
    }
}
