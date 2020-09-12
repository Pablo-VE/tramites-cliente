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
import org.una.tramites.cliente.dto.ClienteDTO;
import org.una.tramites.cliente.util.Request;
import org.una.tramites.cliente.util.Respuesta;
/**
 *
 * @author Pablo-VE
 */
public class ClienteService {
    public Respuesta getAll(){
        try{
            Request request = new Request("clientes");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todos los clientes");
            }
            List<ClienteDTO> result = (List<ClienteDTO>) request.readEntity(new GenericType<List<ClienteDTO>>(){});
            return new Respuesta(true, "Clientes",result);
        }catch(Exception ex){

            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("clientes", "/{id}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener el cliente");
            }
            ClienteDTO result = (ClienteDTO) request.readEntity(ClienteDTO.class);
            return new Respuesta(true, "Cliente", result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
        public Respuesta guardar(ClienteDTO cliente){
        try{
            Request request = new Request("clientes");
            request.post(cliente);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo guardar el cliente");
            }
            ClienteDTO result = (ClienteDTO) request.readEntity(ClienteDTO.class);
            return new Respuesta(true, "Cliente", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificar(Long id, ClienteDTO cliente){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("clientes", "/{id}", parametros);
            request.put(cliente);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el cliente");
            }
            ClienteDTO result = (ClienteDTO) request.readEntity(ClienteDTO.class);
            return new Respuesta(true, "Cliente", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta delete(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("clientes", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar el cliente");
            }
            return new Respuesta(true, "El cliente fue eliminado exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("clientes");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar todos los clientes");
            }
            return new Respuesta(true, "Todos los clientes fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No pudo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getByCedula(String term){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("term", term);
            Request request = new Request("clientes/cedula", "/{term}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener el cliente");
            }
            ClienteDTO result = (ClienteDTO) request.readEntity(ClienteDTO.class);
            return new Respuesta(true, "Cliente", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getByNombre(String term){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("term", term);
            Request request = new Request("clientes/nombre", "/{term}", parametros);
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todos los clientes");
            }
            List<ClienteDTO> result = (List<ClienteDTO>) request.readEntity(new GenericType<List<ClienteDTO>>(){});
            return new Respuesta(true, "Clientes",result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
}
