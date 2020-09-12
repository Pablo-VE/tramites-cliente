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
public class TramiteCambioEstadoDTO {
    private Long id;
    private UsuarioDTO usuarioId;
    private TramiteRegistradoDTO tramitesRegistradosId;
    private TramiteEstadoDTO tramitesEstadoId;
    private Date fechaRegistro; 

    public TramiteCambioEstadoDTO() {
    }

    public TramiteCambioEstadoDTO(Long id, UsuarioDTO usuarioId, TramiteRegistradoDTO tramitesRegistradosId, TramiteEstadoDTO tramitesEstadoId, Date fechaRegistro) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.tramitesRegistradosId = tramitesRegistradosId;
        this.tramitesEstadoId = tramitesEstadoId;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioDTO getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UsuarioDTO usuarioId) {
        this.usuarioId = usuarioId;
    }

    public TramiteRegistradoDTO getTramitesRegistradosId() {
        return tramitesRegistradosId;
    }

    public void setTramitesRegistradosId(TramiteRegistradoDTO tramitesRegistradosId) {
        this.tramitesRegistradosId = tramitesRegistradosId;
    }

    public TramiteEstadoDTO getTramitesEstadoId() {
        return tramitesEstadoId;
    }

    public void setTramitesEstadoId(TramiteEstadoDTO tramitesEstadoId) {
        this.tramitesEstadoId = tramitesEstadoId;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
    
}
