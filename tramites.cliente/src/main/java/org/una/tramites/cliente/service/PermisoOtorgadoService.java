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
import org.una.tramites.cliente.dto.PermisoDTO;
import org.una.tramites.cliente.dto.PermisoOtorgadoDTO;
import org.una.tramites.cliente.dto.UsuarioDTO;
import org.una.tramites.cliente.util.Request;
import org.una.tramites.cliente.util.Respuesta;

/**
 *
 * @author Pablo-VE
 */
public class PermisoOtorgadoService {
    public Respuesta getAll(){
        try{
            Request request = new Request("permisos_otorgados");
            request.get();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al obtener todos los permisos otorgados");
            }
            List<PermisoOtorgadoDTO> result = (List<PermisoOtorgadoDTO>) request.readEntity(new GenericType<List<PermisoOtorgadoDTO>>(){});
            return new Respuesta(true, "PermisosOtorgados",result);
        }catch(Exception ex){

            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getById(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("permisos_otorgados", "/{id}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener el permiso otrogado");
            }
            PermisoOtorgadoDTO result = (PermisoOtorgadoDTO) request.readEntity(PermisoOtorgadoDTO.class);
            return new Respuesta(true, "PermisoOtorgado", result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta getByUsuarioPermiso(UsuarioDTO usuario, PermisoDTO permiso){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("usuario", usuario.getId());
            parametros.put("permiso", permiso.getId());
            Request request = new Request("permisos_otorgados/usuario_permiso", "/{usuario}/{permiso}", parametros);
            request.get();
            if(request.isError()){
                System.out.println(request.getError());
                return new Respuesta(false, request.getError(), "Error al obtener el permiso otrogado");
            }
            PermisoOtorgadoDTO result = (PermisoOtorgadoDTO) request.readEntity(PermisoOtorgadoDTO.class);
            return new Respuesta(true, "PermisoOtorgado", result);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
        public Respuesta guardar(PermisoOtorgadoDTO permisoOtorgado){
        try{
            Request request = new Request("permisos_otorgados/");
            request.post(permisoOtorgado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo otorgar el permiso");
            }
            PermisoOtorgadoDTO result = (PermisoOtorgadoDTO) request.readEntity(PermisoOtorgadoDTO.class);
            return new Respuesta(true, "PermisoOtorgado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta modificar(Long id, PermisoOtorgadoDTO permisoOtorgado){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("permisos_otorgados", "/{id}", parametros);
            request.put(permisoOtorgado);
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo modificar el permiso otorgado");
            }
            PermisoOtorgadoDTO result = (PermisoOtorgadoDTO) request.readEntity(PermisoOtorgadoDTO.class);
            return new Respuesta(true, "PermisoOtorgado", result);
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta delete(Long id){
        try{
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("permisos_otorgados", "/{id}", parametros);
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "No se pudo eliminar el permiso otorgado");
            }
            return new Respuesta(true, "Permiso Otorgado eliminado exitosamente");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
    public Respuesta deleteAll(){
        try{
            Request request = new Request("permisos_otorgados");
            request.delete();
            if(request.isError()){
                return new Respuesta(false, request.getError(), "Error al eliminar los permisos otorgados");
            }
            return new Respuesta(true, "Todos los permisos otorgados fueron eliminados con exito");
        }catch(Exception ex){
            return new Respuesta(false, ex.toString(), "No puedo establecerce conexion con el servidor");
        }
    }
    
}
