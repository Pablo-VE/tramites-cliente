/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.util;

import javafx.scene.control.CheckBox;
import org.una.tramites.cliente.dto.PermisoDTO;

/**
 *
 * @author Pablo-VE
 */
public class PermisosTableView {
    PermisoDTO permiso;
    
    private Long id;
    private String codigo;
    private String descripcion;
    private CheckBox select;

    public PermisosTableView() {
    }

    public PermisosTableView(PermisoDTO permiso) {
        this.permiso = permiso;
        this.id=permiso.getId();
        this.codigo=permiso.getCodigo();
        this.descripcion=permiso.getCodigo();
        this.select=new CheckBox();
        
    }

    public PermisoDTO getPermiso() {
        return permiso;
    }

    public void setPermiso(PermisoDTO permiso) {
        this.permiso = permiso;
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

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }
    
    
    
    
}
