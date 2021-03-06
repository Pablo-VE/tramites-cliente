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
 * @author Pablo-VE
 */
public class UsuarioDTO {
    private Long id; 
    private String nombreCompleto;   
    private String cedula; 
    private String passwordEncriptado;
    private boolean estado; 
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro; 
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaModificacion; 
    private boolean esJefe;
    private DepartamentoDTO departamento;
    private List<PermisoOtorgadoDTO> permisosOtorgados;
    private List<TramiteCambioEstadoDTO> tramitescambioestado = new ArrayList<>();

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nombreCompleto, String cedula, String passwordEncriptado, boolean estado, Date fechaRegistro, Date fechaModificacion, boolean esJefe, DepartamentoDTO departamento) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.cedula = cedula;
        this.passwordEncriptado = passwordEncriptado;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
        this.esJefe = esJefe;
        this.departamento = departamento;
    }

    public String getPasswordEncriptado() {
        return passwordEncriptado;
    }

    public void setPasswordEncriptado(String passwordEncriptado) {
        this.passwordEncriptado = passwordEncriptado;
    }

    

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public boolean getEsJefe() {
        return esJefe;
    }

    public void setEsJefe(boolean esJefe) {
        this.esJefe = esJefe;
    }

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoDTO departamento) {
        this.departamento = departamento;
    }

    public List<PermisoOtorgadoDTO> getPermisosOtorgados() {
        return permisosOtorgados;
    }

    public void setPermisosOtorgados(List<PermisoOtorgadoDTO> permisosOtorgados) {
        this.permisosOtorgados = permisosOtorgados;
    }

    public List<TramiteCambioEstadoDTO> getTramitescambioestado() {
        return tramitescambioestado;
    }

    public void setTramitescambioestado(List<TramiteCambioEstadoDTO> tramitescambioestado) {
        this.tramitescambioestado = tramitescambioestado;
    }
    
    
    
    
}
