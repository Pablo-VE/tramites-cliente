/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.dto;

import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbDateFormat;

/**
 *
 * @author Pablo-VE
 */
public class PermisoOtorgadoDTO {
    private Long id; 
    private UsuarioDTO usuarioid;   
    private PermisoDTO permisoid; 
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro; 
    private boolean estado; 
    private List<TransaccionDTO> transacciones;

    public PermisoOtorgadoDTO() {
    }

    public PermisoOtorgadoDTO(Long id, UsuarioDTO usuarioid, PermisoDTO permisoid, Date fechaRegistro, boolean estado, List<TransaccionDTO> transacciones) {
        this.id = id;
        this.usuarioid = usuarioid;
        this.permisoid = permisoid;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.transacciones = transacciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioDTO getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(UsuarioDTO usuarioid) {
        this.usuarioid = usuarioid;
    }

    public PermisoDTO getPermisoid() {
        return permisoid;
    }

    public void setPermisoid(PermisoDTO permisoid) {
        this.permisoid = permisoid;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<TransaccionDTO> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<TransaccionDTO> transacciones) {
        this.transacciones = transacciones;
    }
    
    
}
