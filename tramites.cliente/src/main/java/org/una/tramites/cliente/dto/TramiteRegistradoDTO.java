/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pablo-VE
 */
public class TramiteRegistradoDTO {
    private Long id;
    private int tramitesTiposId;
    private ClienteDTO clienteId;
    private List<TramiteCambioEstadoDTO> tramitescambioestado = new ArrayList<>();
    private List<RequisitoPresentadoDTO> requisitosPresentados = new ArrayList<>();
    private List<ArchivoRelacionadoDTO> archivosRelacionados = new ArrayList<>();

    public TramiteRegistradoDTO() {
    }

    public TramiteRegistradoDTO(Long id, int tramitesTiposId, ClienteDTO clienteId) {
        this.id = id;
        this.tramitesTiposId = tramitesTiposId;
        this.clienteId = clienteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTramitesTiposId() {
        return tramitesTiposId;
    }

    public void setTramitesTiposId(int tramitesTiposId) {
        this.tramitesTiposId = tramitesTiposId;
    }

    public ClienteDTO getClienteId() {
        return clienteId;
    }

    public void setClienteId(ClienteDTO clienteId) {
        this.clienteId = clienteId;
    }

    public List<TramiteCambioEstadoDTO> getTramitescambioestado() {
        return tramitescambioestado;
    }

    public void setTramitescambioestado(List<TramiteCambioEstadoDTO> tramitescambioestado) {
        this.tramitescambioestado = tramitescambioestado;
    }

    public List<RequisitoPresentadoDTO> getRequisitosPresentados() {
        return requisitosPresentados;
    }

    public void setRequisitosPresentados(List<RequisitoPresentadoDTO> requisitosPresentados) {
        this.requisitosPresentados = requisitosPresentados;
    }

    public List<ArchivoRelacionadoDTO> getArchivosRelacionados() {
        return archivosRelacionados;
    }

    public void setArchivosRelacionados(List<ArchivoRelacionadoDTO> archivosRelacionados) {
        this.archivosRelacionados = archivosRelacionados;
    }
    
    
}
