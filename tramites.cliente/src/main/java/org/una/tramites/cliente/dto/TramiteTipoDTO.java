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
public class TramiteTipoDTO {
    private Long id;
    private String descripcion;
    private boolean estado;
    private DepartamentoDTO departamento;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaModificacion;
    private List<VariacionDTO> variaciones = new ArrayList<>();

    public TramiteTipoDTO() {
    }

    public TramiteTipoDTO(Long id, String descripcion, boolean estado, DepartamentoDTO departamento, Date fechaRegistro, Date fechaModificacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.departamento = departamento;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoDTO departamento) {
        this.departamento = departamento;
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

    public List<VariacionDTO> getVariaciones() {
        return variaciones;
    }

    public void setVariaciones(List<VariacionDTO> variaciones) {
        this.variaciones = variaciones;
    }
    
    public String toString(){
        return descripcion;
    }
}
