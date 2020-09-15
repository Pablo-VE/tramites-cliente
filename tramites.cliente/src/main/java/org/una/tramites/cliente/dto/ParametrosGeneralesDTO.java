/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbDateFormat;

/**
 *
 * @author Luis
 */
public class ParametrosGeneralesDTO {
    private Long id;
    private String nombre;
    private String valor;
    private String descripcion;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaModificacion;
    private boolean estado;
    
    private List<ParametrosGeneralesDTO> parametrosGeneral = new ArrayList<>();
    
    public ParametrosGeneralesDTO(){
    }
    public ParametrosGeneralesDTO(Long id, String nombre, String valor, String descripcion, Date fecharegistro, Date fechaModificacion, boolean estado){
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
    }
       
    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return nombre;
    }
    
    public void setValor(String valor){
        this.valor = valor;
    }
    public String getValor(){
        return valor;
    }
    
    public void setEstado(boolean estado){
        this.estado=estado;
    }
    public boolean getEstado(){
        return estado;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public String getDescripcion(){
        return descripcion;
    }
    
    public java.util.Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(java.util.Date fechaRegistro) {
        this.fechaRegistro = (Date) fechaRegistro;
    }

    public java.util.Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(java.util.Date fechaModificacion) {
        this.fechaModificacion = (Date) fechaModificacion;
    }
    
    public List<ParametrosGeneralesDTO> getParametrosGeneral() {
        return parametrosGeneral;
    }

    public void setParametrosGeneral(List<ParametrosGeneralesDTO> parametrosGeneral) {
        this.parametrosGeneral = parametrosGeneral;
    }
    
    
    
    
    
    
    
}
