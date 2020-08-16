/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.designer.kiosko.servicios.service;

import co.com.designer.kiosko.servicios.controller.OpcionesKioskosJpaController;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import javax.naming.InitialContext;
import co.com.designer.kiosko.entidades.OpcionesKioskos;
import java.math.BigInteger;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Lenovo
 */
@Path("opcioneskioskos")
public class OpcionesKioskosRESTFacade {

    private EntityManagerFactory getEntityManagerFactory() throws NamingException {
        return (EntityManagerFactory) new InitialContext().lookup("java:comp/env/persistence-factory");
    }

    private OpcionesKioskosJpaController getJpaController() {
        try {
            UserTransaction utx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            return new OpcionesKioskosJpaController(utx, getEntityManagerFactory());
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public OpcionesKioskosRESTFacade() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(OpcionesKioskos entity) {
        try {
            getJpaController().create(entity);
            return Response.created(URI.create(entity.getSecuencia().toString())).build();
        } catch (Exception ex) {
            return Response.notModified(ex.getMessage()).build();
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(OpcionesKioskos entity) {
        try {
            getJpaController().edit(entity);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.notModified(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") BigInteger id) {
        try {
            getJpaController().destroy(id);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.notModified(ex.getMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public OpcionesKioskos find(@PathParam("id") BigInteger id) {
        return getJpaController().findOpcionesKioskos(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<OpcionesKioskos> findAll() {
        return getJpaController().findOpcionesKioskosEntities();
    }

    @GET
    @Path("{max}/{first}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<OpcionesKioskos> findRange(@PathParam("max") Integer max, @PathParam("first") Integer first) {
        return getJpaController().findOpcionesKioskosEntities(max, first);
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        return String.valueOf(getJpaController().getOpcionesKioskosCount());
    }
    
}
