/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.designer.kiosko.servicios.service;

//import co.com.designer.kiosko.conexionFuente.implementacion.SesionEntityManagerFactory;
import co.com.designer.kiosko.servicios.controller.CiudadesJpaController;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import javax.naming.InitialContext;
import co.com.designer.kiosko.entidades.Ciudades;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.SystemException;
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
@Path("ciudades")
public class CiudadesRESTFacade {

    private EntityManagerFactory getEntityManagerFactory() throws NamingException {
//        return (EntityManagerFactory) new InitialContext().lookup("java:comp/env/persistence-factory");
//        Object busqueda = new InitialContext().lookup("java:comp/env/persistence-factory");
        EntityManagerFactory emf = (EntityManagerFactory) new InitialContext().lookup("java:comp/env/persistence-factory");
        System.out.println("Persistencia-isOpen: "+ emf.isOpen());
//        return (EntityManagerFactory) busqueda;
        return emf;
//        SesionEntityManagerFactory ses = new SesionEntityManagerFactory();
//        return ses.crearConexionUsuario("DEFAULT1");
    }

    private CiudadesJpaController getJpaController() {
        try {
            UserTransaction utx = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            try {
                //            UserTransaction utx = null;
                System.out.println("utx-status: "+utx.getStatus());
            } catch (SystemException ex) {
                Logger.getLogger(CiudadesRESTFacade.class.getName()).log(Level.SEVERE, "Error conultando el estado de UserTransaction", ex);
            }
            return new CiudadesJpaController(utx, getEntityManagerFactory());
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public CiudadesRESTFacade() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Ciudades entity) {
        try {
            getJpaController().create(entity);
            return Response.created(URI.create(entity.getSecuencia().toString())).build();
        } catch (Exception ex) {
            return Response.notModified(ex.getMessage()).build();
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(Ciudades entity) {
        try {
            getJpaController().edit(entity);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.notModified(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") BigDecimal id) {
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
    public Ciudades find(@PathParam("id") BigDecimal id) {
        return getJpaController().findCiudades(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Ciudades> findAll() {
        return getJpaController().findCiudadesEntities();
    }

    @GET
    @Path("{max}/{first}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Ciudades> findRange(@PathParam("max") Integer max, @PathParam("first") Integer first) {
        return getJpaController().findCiudadesEntities(max, first);
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        return String.valueOf(getJpaController().getCiudadesCount());
    }

}
