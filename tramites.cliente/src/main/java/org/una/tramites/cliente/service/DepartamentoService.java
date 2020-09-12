/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import org.una.tramites.cliente.dto.DepartamentoDTO;
import org.una.tramites.cliente.util.Request;
import org.una.tramites.cliente.util.Respuesta;

/**
 *
 * @author Pablo-VE
 */
public class DepartamentoService {
    public Respuesta guardarDepartamento(DepartamentoDTO departamento){
        try{
            Request request = new Request("departamentos");
            request.post(departamento);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar el departamento");
            }
            DepartamentoDTO result = (DepartamentoDTO) request.readEntity(DepartamentoDTO.class);
            return new Respuesta(true, "Departamento", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificarDepartamento(Long id, DepartamentoDTO departamento){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("departamentos", "/{id}", parametros);
            request.put(departamento);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el departamento");
            }
            DepartamentoDTO result = (DepartamentoDTO) request.readEntity(DepartamentoDTO.class);
            return new Respuesta(true, "Departamento", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getDepartamentoByEstado(Boolean estado){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("estado", estado);
            Request request = new Request("departamentos/estado", "/{estado}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener los departamentos");
            }
            List<DepartamentoDTO> result = (List<DepartamentoDTO>) request.readEntity(new GenericType<List<DepartamentoDTO>>(){});
            return new Respuesta(true, "Departamentos",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
