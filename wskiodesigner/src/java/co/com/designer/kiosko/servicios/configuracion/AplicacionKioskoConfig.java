/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.designer.kiosko.servicios.configuracion;
//import co.com.designer.kiosko.servicios.service.CiudadesRESTFacade;
import co.com.designer.kiosko.servicios.service.ConexionesKioskosRESTFacade;
import co.com.designer.kiosko.servicios.service.OpcionesKioskosRESTFacade;
import co.com.designer.kiosko.servicios.service.ParametrizaClaveRESTFacade;
import co.com.designer.kiosko.servicios.service.PreguntasKioskosRESTFacade;
import co.com.designer.kiosko.servicios.service.TiposDocumentosRESTFacade;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author Lenovo
 */
@javax.ws.rs.ApplicationPath("webresources")
public class AplicacionKioskoConfig extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    /**
     * Do not modify 
     * @param resources addRestResourceClasses() method.
     * It is automatically populated with
     * all resources definde in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources){
//        resources.add(CiudadesRESTFacade.class);
        resources.add(ConexionesKioskosRESTFacade.class);
        resources.add(OpcionesKioskosRESTFacade.class);
        resources.add(ParametrizaClaveRESTFacade.class);
        resources.add(PreguntasKioskosRESTFacade.class);
        resources.add(TiposDocumentosRESTFacade.class);
    }
}
