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
public class PermisoDTO {
    private Long id; 
    private String codigo;
    private String descripcion;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro; 
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaModificacion;
    private boolean estado; 
    private List<PermisoOtorgadoDTO> permisosOtorgados;

    public PermisoDTO() {
    }

    public PermisoDTO(Long id, String codigo, String descripcion, Date fechaRegistro, Date fechaModificacion, boolean estado, List<PermisoOtorgadoDTO> permisosOtorgados) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
        this.estado = estado;
        this.permisosOtorgados = permisosOtorgados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<PermisoOtorgadoDTO> getPermisosOtorgados() {
        return permisosOtorgados;
    }

    public void setPermisosOtorgados(List<PermisoOtorgadoDTO> permisosOtorgados) {
        this.permisosOtorgados = permisosOtorgados;
    }
    
    
}
