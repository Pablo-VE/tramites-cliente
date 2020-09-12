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
import org.una.tramites.cliente.dto.TransaccionDTO;
import org.una.tramites.cliente.util.Request;
import org.una.tramites.cliente.util.Respuesta;

/**
 *
 * @author Pablo-VE
 */
public class TransaccionService {
    public Respuesta getAll(){
        try{
            Request request = new Request("transacciones");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todas las transacciones");
            }
            List<TransaccionDTO> result = (List<TransaccionDTO>) request.readEntity(new GenericType<List<TransaccionDTO>>(){});
            return new Respuesta(true, "Transacciones",result);
        }catch(Exception ex){

            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("transacciones", "/{id}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener la transaccion");
            }
            TransaccionDTO result = (TransaccionDTO) request.readEntity(TransaccionDTO.class);
            return new Respuesta(true, "Transaccion", result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
        public Respuesta guardar(TransaccionDTO transaccion){
        try{
            Request request = new Request("transacciones");
            request.post(transaccion);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar la transaccion");
            }
            TransaccionDTO result = (TransaccionDTO) request.readEntity(TransaccionDTO.class);
            return new Respuesta(true, "Transaccion", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificar(Long id, TransaccionDTO transaccion){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("transacciones", "/{id}", parametros);
            request.put(transaccion);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar la transaccion");
            }
            TransaccionDTO result = (TransaccionDTO) request.readEntity(TransaccionDTO.class);
            return new Respuesta(true, "Transaccion", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta delete(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("transacciones", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar la transaccion");
            }
            return new Respuesta(true, "Transaccion eliminada exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("transacciones");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar las transacciones");
            }
            return new Respuesta(true, "Todas las transacciones fueron eliminadas con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
