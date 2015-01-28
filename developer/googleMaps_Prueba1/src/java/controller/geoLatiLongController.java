/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.swing.DefaultListModel;
import maps.java.Geocoding;

/**
 *
 * @author CJAVAPERU
 */
@ManagedBean(name = "geoLatiLong")
@RequestScoped
public class geoLatiLongController {

    private String direccion;
    private String latitud;
    private String longitud;
    private String direccionEncontrada;
    private String codPostal;
    private String elevacion;
    private String resolucion;
    private String status; // esta escondido para verificar el status y poder obtener resolucion y altitud
    //lista para las direcciones encontradas
    private List<String> direccionEncontradaList;
//  src para el iframe de primefaces en el form
    private String srcGoogle;

//llamado a las clases de java
    private Geocoding ObjGeocoding = new Geocoding();

    public geoLatiLongController() {
    }

    public void btnBuscar() {
        try {
            CodiGeograficaInver();
            // this.comprobarStatus(status);
            //this.elevacionCI(latitud,longitud);
        } catch (Exception ex) {
        }
    }

    private void CodiGeograficaInver() throws UnsupportedEncodingException, MalformedURLException {
        if (!this.latitud.isEmpty() && !this.longitud.isEmpty()) {
            direccionEncontrada = "";
            DefaultListModel model = new DefaultListModel();
            //direccionEncontrada.
            //jList_CI_DirEncon.setModel(model); // lista 
            ArrayList<String> resultado = ObjGeocoding.getAddress(Double.valueOf(latitud),
                    Double.valueOf(longitud));
            if (resultado.size() > 0) {
                direccionEncontrada = resultado.get(0);
            }
            codPostal = ObjGeocoding.getPostalcode();
            //cargarJList(resultado,jList_CI_DirEncon);
            direccionEncontradaList = resultado;
        }
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSrcGoogle() {
        return srcGoogle;
    }

    public void setSrcGoogle(String srcGoogle) {
        this.srcGoogle = srcGoogle;
    }

    public List<String> getDireccionEncontradaList() {
        return direccionEncontradaList;
    }

    public void setDireccionEncontradaList(List<String> direccionEncontradaList) {
        this.direccionEncontradaList = direccionEncontradaList;
    }

    

    
}
