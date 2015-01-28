/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author CJAVAPERU
 */
@ManagedBean(name = "ruta")
@RequestScoped
public class rutasController {

    private String origen;
    private String destino;
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
  
    private Route ObjRoute=new Route();
    
    public rutasController() {
        
    }
    
    public void crearRuta()
    {
        if(!origen.isEmpty() && !destino.isEmpty()){
             ArrayList<String> hitos=new ArrayList<>();
             //if(jCheckBox_Ruta_Hito.isSelected() && !JText_Ruta_Hito.getText().isEmpty()){
             //    hitos.add(JText_Ruta_Hito.getText());
             }
             String[][] arrayRoute=ObjRoute.getRoute(JText_Ruta_DirecOrigen.getText(), JText_Ruta_DirecDestin.getText(),
                     hitos, Boolean.TRUE,this.seleccionarModoRuta(),this.seleccionarRestricciones());  
             rellenarTablaRuta(arrayRoute);
             rellenarDatosrRuta();
            
         }
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    
    
}
