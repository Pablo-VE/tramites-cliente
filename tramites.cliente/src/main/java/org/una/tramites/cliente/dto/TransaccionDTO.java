/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.dto;

import java.util.Date;
import javax.json.bind.annotation.JsonbDateFormat;

/**
 *
 * @author Pablo-VE
 */
public class TransaccionDTO {
    private Long id;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    Date fechaRegistro;
    PermisoOtorgadoDTO permisoOtorgado;
    String objeto;
    String información;

    public TransaccionDTO() {
    }

    public TransaccionDTO(Long id, Date fechaRegistro, PermisoOtorgadoDTO permisoOtorgado, String objeto, String información) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.permisoOtorgado = permisoOtorgado;
        this.objeto = objeto;
        this.información = información;
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

    public PermisoOtorgadoDTO getPermisoOtorgado() {
        return permisoOtorgado;
    }

    public void setPermisoOtorgado(PermisoOtorgadoDTO permisoOtorgado) {
        this.permisoOtorgado = permisoOtorgado;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getInformación() {
        return información;
    }

    public void setInformación(String información) {
        this.información = información;
    }
}
