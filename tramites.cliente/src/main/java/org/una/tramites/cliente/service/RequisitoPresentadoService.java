/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import org.una.tramites.cliente.dto.RequisitoPresentadoDTO;
import org.una.tramites.cliente.util.Request;
import org.una.tramites.cliente.util.Respuesta;

/**
 *
 * @author Pablo-VE
 */
public class RequisitoPresentadoService {
    public Respuesta getAll(){
        try{
            Request request = new Request("requisitos_presentados");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todos los requisitos presentados");
            }
            List<RequisitoPresentadoDTO> result = (List<RequisitoPresentadoDTO>) request.readEntity(new GenericType<List<RequisitoPresentadoDTO>>(){});
            return new Respuesta(true, "RequisitosPresentados",result);
        }catch(Exception ex){

            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("requisitos_presentados", "/{id}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener el requisito presentado");
            }
            RequisitoPresentadoDTO result = (RequisitoPresentadoDTO) request.readEntity(RequisitoPresentadoDTO.class);
            return new Respuesta(true, "RequisitoPresentado", result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
        public Respuesta guardar(RequisitoPresentadoDTO requisitoPresentado){
        try{
            Request request = new Request("requisitos_presentados");
            request.post(requisitoPresentado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar el requisito presentado");
            }
            RequisitoPresentadoDTO result = (RequisitoPresentadoDTO) request.readEntity(RequisitoPresentadoDTO.class);
            return new Respuesta(true, "RequisitoPresentado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificar(Long id, RequisitoPresentadoDTO requisitoPresentado){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("requisitos_presentados", "/{id}", parametros);
            request.put(requisitoPresentado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el requisito presentado");
            }
            RequisitoPresentadoDTO result = (RequisitoPresentadoDTO) request.readEntity(RequisitoPresentadoDTO.class);
            return new Respuesta(true, "RequisitoPresentado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta delete(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("requisitos_presentados", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar el requisito presentado");
            }
            return new Respuesta(true, "El requisito presentado fue eliminado exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("requisitos_presentados");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar todos los requisitos presentados");
            }
            return new Respuesta(true, "Todos los requisitos presentados fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No pudo establecerce conexion con el servidor");
        }
    }
    
    
    
    public Respuesta getByTramiteRegistrado(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("requisitos_presentados/tramite_registrado", "/{id}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener requisitos presentados por tramite registrado");
            }
            List<RequisitoPresentadoDTO> result = (List<RequisitoPresentadoDTO>) request.readEntity(new GenericType<List<RequisitoPresentadoDTO>>(){});
            return new Respuesta(true, "RequisitosPresentados",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
