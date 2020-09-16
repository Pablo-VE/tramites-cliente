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
public class VariacionDTO {
    private Long id;
    private int grupo;
    private String descripcion;
    private boolean estado;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro;
    private TramiteTipoDTO tramitesTipos; 
    private List<RequisitoDTO> requisitos = new ArrayList<>();

    public VariacionDTO() {
    }

    public VariacionDTO(Long id, int grupo, String descripcion, boolean estado, Date fechaRegistro, TramiteTipoDTO tramites) {
        this.id = id;
        this.grupo = grupo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.tramitesTipos = tramites;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public TramiteTipoDTO getTramites() {
        return tramitesTipos;
    }

    public void setTramites(TramiteTipoDTO tramites) {
        this.tramitesTipos = tramites;
    }

    public List<RequisitoDTO> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(List<RequisitoDTO> requisitos) {
        this.requisitos = requisitos;
    }
    
    
}
