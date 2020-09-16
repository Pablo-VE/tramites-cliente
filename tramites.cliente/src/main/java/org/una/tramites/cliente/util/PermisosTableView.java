/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.util;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import org.una.tramites.cliente.dto.PermisoDTO;
import org.una.tramites.cliente.dto.PermisoOtorgadoDTO;
import org.una.tramites.cliente.dto.UsuarioDTO;
import org.una.tramites.cliente.service.PermisoOtorgadoService;

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
        modalidad = (String) AppContext.getInstance().get("ModalidadUsuarios");
        autoSeleccion();
        iniciarAccion();
        if(modalidad.equals("Ver")){
            this.select.setDisable(true);
        }
        
        
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
    
    
    public void iniciarAccion(){
        select.setOnAction((ActionEvent event) -> {
                            try{
                               // System.out.println(permiso.getCodigo());
                                PermisoDTO data = permiso;
                                setearPermisosAOtorgar(data);
                                
                            }catch(Exception ex){}
                        });
    }
    
    
    PermisoOtorgadoService perService = new PermisoOtorgadoService();
    String modalidad;
    public void autoSeleccion(){
        permisosYaOtorgados = (ArrayList<PermisoOtorgadoDTO>) AppContext.getInstance().get("permisosYaOtorgados");
        
        UsuarioDTO usu=(UsuarioDTO) AppContext.getInstance().get("UsuarioEnCuestion");
        if(modalidad.equals("Modificar")||modalidad.equals("Ver")){
            Respuesta res = perService.getByUsuarioPermiso(usu, permiso);
            if(res.getEstado()){
                PermisoOtorgadoDTO per =(PermisoOtorgadoDTO) res.getResultado("PermisoOtorgado");
                if(per.getEstado()){
                    select.setSelected(true);
                    setearPermisosAOtorgar(permiso);
                }
                permisosYaOtorgados.add(per);
            }
            
        }
        AppContext.getInstance().set("permisosYaOtorgados", permisosYaOtorgados);
    }
    
    ArrayList<PermisoOtorgadoDTO> permisosYaOtorgados = new ArrayList<>();
    ArrayList<PermisoDTO> permisos_a_otorgar = new ArrayList<>();
    
    public void setearPermisosAOtorgar(PermisoDTO permiso){
        permisos_a_otorgar = (ArrayList<PermisoDTO>) AppContext.getInstance().get("permisos_a_otorgar");
        
        if(permisos_a_otorgar==null){
            permisos_a_otorgar.add(permiso);
            System.out.println("Se agrego permiso "+permiso.getCodigo());
        }else{
            boolean esta=false;
            for(int i=0; i<permisos_a_otorgar.size(); i++){
                if(permisos_a_otorgar.get(i).getId()==permiso.getId()){
                    esta=true;
                }
            }
            if(esta){
                for(int i=0; i<permisos_a_otorgar.size(); i++){
                    if(permisos_a_otorgar.get(i).getId()==permiso.getId()){
                        System.out.println("Se elimino permiso repetido "+permisos_a_otorgar.get(i).getCodigo());
                        permisos_a_otorgar.remove(i);
                        
                    }
                }
            }else{
                permisos_a_otorgar.add(permiso);
                System.out.println("Se agrego permiso "+permiso.getCodigo());
            }
        }
        AppContext.getInstance().set("permisos_a_otorgar",permisos_a_otorgar);
        
    }
    
    
    
}
