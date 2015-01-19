/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Desktop;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import maps.java.Elevation;
import maps.java.Geocoding;
import maps.java.MapsJava;
import maps.java.ShowMaps;

/**
 *
 * @author CJAVAPERU
 */
@ManagedBean(name = "geoCodigo")
@SessionScoped
public class geoCodigoController {

    //Cajas de texto del formulario
    private String direccion;
    private String latitud;
    private String longitud;
    private String direccionEncontrada;
    private String codPostal;
    private String elevacion;
    private String resolucion;
    private String status; // esta escondido para verificar el status y poder obtener resolucion y altitud
    
    //  src para el iframe de primefaces en el form
    private String srcGoogle;
    
    //llamado a las clases de java
    private Geocoding ObjGeocoding = new Geocoding();
    private ShowMaps ObjShowMaps = new ShowMaps(); // para el boton mostrar mapa
    private Elevation ObjElevation=new Elevation(); // para encontrar la elevacion

    /**
     * Creates a new instance of geoCodigoController
     */
    public geoCodigoController() {
    }

    public void btnBuscar() {
        try {
            this.CodiGeografica();
            this.comprobarStatus();
            this.elevacionCD();
            
            
        } catch (Exception ex) {
        }
    }

    public void btnMostrarMapa() {
        if (!latitud.isEmpty() && !longitud.isEmpty()) {
            try {
                mostrarMapa();
            } catch (Exception ex) {
            }
        }
    }

    public void mostrarMapa() {
        try {
            String direccionMapa = ObjShowMaps.getURLMap(Double.valueOf(latitud), Double.valueOf(longitud));
            srcGoogle = direccionMapa; // asigno el url para cargarlo en el iframe

        } catch (Exception e) {
        }

    }

    private void CodiGeografica() throws UnsupportedEncodingException, MalformedURLException {
        if (!this.direccion.isEmpty()) {
            direccionEncontrada = "";
            Point2D.Double resultado = ObjGeocoding.getCoordinates(direccion);
            latitud = String.valueOf(resultado.x);
            longitud = String.valueOf(resultado.y);
            direccionEncontrada = String.valueOf(ObjGeocoding.getAddressFound());
            codPostal = ObjGeocoding.getPostalcode();
           

        }
    }
    
    private void elevacionCD() throws MalformedURLException{
        if(!latitud.isEmpty() && !longitud.isEmpty()){
            double resultado=ObjElevation.getElevation(Double.valueOf(latitud),
                    Double.valueOf(longitud));
            elevacion = String.valueOf(resultado);
            resolucion = String.valueOf(ObjElevation.getResolution());

        }
    }
    
    private void comprobarStatus(){
         status = (MapsJava.getLastRequestStatus());
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDireccionEncontrada() {
        return direccionEncontrada;
    }

    public void setDireccionEncontrada(String direccionEncontrada) {
        this.direccionEncontrada = direccionEncontrada;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getElevacion() {
        return elevacion;
    }

    public void setElevacion(String elevacion) {
        this.elevacion = elevacion;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public Geocoding getObjGeocoding() {
        return ObjGeocoding;
    }

    public void setObjGeocoding(Geocoding ObjGeocoding) {
        this.ObjGeocoding = ObjGeocoding;
    }

    public String getSrcGoogle() {
        return srcGoogle;
    }

    public void setSrcGoogle(String srcGoogle) {
        this.srcGoogle = srcGoogle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

}
