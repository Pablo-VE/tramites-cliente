/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.dto;

import java.util.Date;
import javax.json.bind.annotation.JsonbDateFormat;

/**
 *
 * @author Pablo-VE
 */
public class PermisoOtorgadoDTO {
    private Long id; 
    private UsuarioDTO usuario;   
    private PermisoDTO permiso; 
    
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro; 
    
    private boolean estado; 
////    private List<TransaccionDTO> transacciones;

    public PermisoOtorgadoDTO() {
    }

    public PermisoOtorgadoDTO(Long id, UsuarioDTO usuario, PermisoDTO permiso, Date fechaRegistro, boolean estado) {
        this.id = id;
        this.usuario = usuario;
        this.permiso = permiso;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public PermisoDTO getPermiso() {
        return permiso;
    }

    public void setPermiso(PermisoDTO permiso) {
        this.permiso = permiso;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

//    public List<TransaccionDTO> getTransacciones() {
//        return transacciones;
//    }
//
//    public void setTransacciones(List<TransaccionDTO> transacciones) {
//        this.transacciones = transacciones;
//    }
    
    
}
