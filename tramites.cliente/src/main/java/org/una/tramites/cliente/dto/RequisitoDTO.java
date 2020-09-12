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
public class RequisitoDTO {
    private Long id;
    private String descripcion;
    private boolean estado;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro;
    private VariacionDTO variaciones;
    private List<RequisitoPresentadoDTO> requisitosPresentados = new ArrayList<>();

    public RequisitoDTO() {
    }

    public RequisitoDTO(Long id, String descripcion, boolean estado, Date fechaRegistro, VariacionDTO variaciones) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.variaciones = variaciones;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public VariacionDTO getVariaciones() {
        return variaciones;
    }

    public void setVariaciones(VariacionDTO variaciones) {
        this.variaciones = variaciones;
    }

    public List<RequisitoPresentadoDTO> getRequisitosPresentados() {
        return requisitosPresentados;
    }

    public void setRequisitosPresentados(List<RequisitoPresentadoDTO> requisitosPresentados) {
        this.requisitosPresentados = requisitosPresentados;
    }
    
    
}
