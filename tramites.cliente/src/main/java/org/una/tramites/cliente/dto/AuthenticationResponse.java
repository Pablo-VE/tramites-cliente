/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.dto;

import java.util.List;

/**
 *
 * @author Pablo-VE
 */
public class AuthenticationResponse {
    private String jwt;
    private UsuarioDTO usuario;
    private List<PermisoOtorgadoDTO> permisos;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String jwt, UsuarioDTO usuario, List<PermisoOtorgadoDTO> permisos) {
        this.jwt = jwt;
        this.usuario = usuario;
        this.permisos = permisos;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public List<PermisoOtorgadoDTO> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PermisoOtorgadoDTO> permisos) {
        this.permisos = permisos;
    }
    
    
}
