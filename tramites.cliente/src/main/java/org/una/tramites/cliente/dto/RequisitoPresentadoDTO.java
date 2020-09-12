/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.dto;

import java.util.Date;

/**
 *
 * @author Pablo-VE
 */
public class RequisitoPresentadoDTO {
    private Long id;
    private Date fechaRegistro;
    private TramiteRegistradoDTO tramiteRegistradoId;
    private RequisitoDTO requisitoId;

    public RequisitoPresentadoDTO() {
    }

    public RequisitoPresentadoDTO(Long id, Date fechaRegistro, TramiteRegistradoDTO tramiteRegistradoId, RequisitoDTO requisitoId) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.tramiteRegistradoId = tramiteRegistradoId;
        this.requisitoId = requisitoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public TramiteRegistradoDTO getTramiteRegistradoId() {
        return tramiteRegistradoId;
    }

    public void setTramiteRegistradoId(TramiteRegistradoDTO tramiteRegistradoId) {
        this.tramiteRegistradoId = tramiteRegistradoId;
    }

    public RequisitoDTO getRequisitoId() {
        return requisitoId;
    }

    public void setRequisitoId(RequisitoDTO requisitoId) {
        this.requisitoId = requisitoId;
    }
    
    
}
