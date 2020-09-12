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
public class TramiteEstadoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String estadosSucesores;
    private List<TramiteCambioEstadoDTO> tramitescambioestado = new ArrayList<>();

    public TramiteEstadoDTO() {
    }

    public TramiteEstadoDTO(Long id, String nombre, String descripcion, String estadosSucesores) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estadosSucesores = estadosSucesores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadosSucesores() {
        return estadosSucesores;
    }

    public void setEstadosSucesores(String estadosSucesores) {
        this.estadosSucesores = estadosSucesores;
    }

    public List<TramiteCambioEstadoDTO> getTramitescambioestado() {
        return tramitescambioestado;
    }

    public void setTramitescambioestado(List<TramiteCambioEstadoDTO> tramitescambioestado) {
        this.tramitescambioestado = tramitescambioestado;
    }
    
    
}
