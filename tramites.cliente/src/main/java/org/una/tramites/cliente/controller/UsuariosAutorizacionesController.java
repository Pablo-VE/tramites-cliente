/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.una.tramites.cliente.dto.PermisoDTO;
import org.una.tramites.cliente.dto.PermisoOtorgadoDTO;
import org.una.tramites.cliente.dto.UsuarioDTO;
import org.una.tramites.cliente.service.PermisoOtorgadoService;
import org.una.tramites.cliente.service.PermisoService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Mensaje;
import org.una.tramites.cliente.util.PermisosTableView;
import org.una.tramites.cliente.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class UsuariosAutorizacionesController implements Initializable {

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnGuardar;
    @FXML
    private TableView<PermisosTableView> tbPermisos;

    /**
     * Initializes the controller class.
     */
    
    PermisoOtorgadoService perOtorService = new PermisoOtorgadoService();
    
    private String modalidad;
    
    UsuarioDTO usu = new UsuarioDTO();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        permisosOtorgadosUsuario=new ArrayList<>();
        permisos_a_otorgar = new ArrayList<>();
        btnGuardar.setDisable(false);
        btnGuardar.setVisible(true);
        modalidad = (String) AppContext.getInstance().get("ModalidadUsuarios");
        if(modalidad.equals("Agregar")){
            usu= (UsuarioDTO) AppContext.getInstance().get("UsuarioNuevo");
        }else{
            if(modalidad.equals("Modificar")||modalidad.equals("Ver")){
                usu=(UsuarioDTO) AppContext.getInstance().get("UsuarioEnCuestion"); 
                
                if(modalidad.equals("Ver")){
                    btnGuardar.setDisable(true);
                    btnGuardar.setVisible(false);
                }
            }
        }
        AppContext.getInstance().set("permisosYaOtorgados",permisosOtorgadosUsuario);
        AppContext.getInstance().set("permisos_a_otorgar",permisos_a_otorgar);
        cargarPermisosEstado();
       
    }    

    @FXML
    private void actCancelar(ActionEvent event) {
    }

    @FXML
    private void actLimpiar(ActionEvent event) {
    }

    @FXML
    private void actGuardar(ActionEvent event) {
        permisos_a_otorgar = (ArrayList<PermisoDTO>) AppContext.getInstance().get("permisos_a_otorgar");
        permisosOtorgadosUsuario=(ArrayList<PermisoOtorgadoDTO>) AppContext.getInstance().get("permisosYaOtorgados");
        if(modalidad.equals("Agregar")){
            if(permisos_a_otorgar==null){
                
            
            }else{
                List<PermisoOtorgadoDTO> permisosOtorgar = new ArrayList<>();
                for(int i=0; i<permisos_a_otorgar.size(); i++){
                    PermisoOtorgadoDTO permisoOtorgado = new PermisoOtorgadoDTO();
                    permisoOtorgado.setUsuario(usu);
                    permisoOtorgado.setEstado(true);
                    permisoOtorgado.setPermiso(permisos_a_otorgar.get(i));
                    permisosOtorgar.add(permisoOtorgado);
                }
                usu.setPermisosOtorgados(permisosOtorgar);
                AppContext.getInstance().set("UsuarioNuevo", usu);
                cargarPermisosEstado();
                Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Permisos a otorgar", "Se le han a establecidos persmisos al nuevo usuario");
                permisosOtorgadosUsuario=new ArrayList<>();
                permisos_a_otorgar = new ArrayList<>();
                AppContext.getInstance().set("permisosYaOtorgados",permisosOtorgadosUsuario);
                AppContext.getInstance().set("permisos_a_otorgar",permisos_a_otorgar);
            }
        }else{
            if(modalidad.equals("Modificar")){
                modificarPermisos(permisos_a_otorgar, permisosOtorgadosUsuario);
                Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Permisos a otorgar", "Se han modificado los permisos otorgados del usuario correctamente");
                AppContext.getInstance().set("UsuarioEnCuestion", usu);
                AppContext.getInstance().set("ModalidadUsuarios", "Modificar");
                cargarPermisosEstado();
                permisosOtorgadosUsuario=new ArrayList<>();
                permisos_a_otorgar = new ArrayList<>();
                AppContext.getInstance().set("permisosYaOtorgados",permisosOtorgadosUsuario);
                AppContext.getInstance().set("permisos_a_otorgar",permisos_a_otorgar);
            }
        }
    }
    
    PermisoService perService = new PermisoService();
    
    public void cargarPermisosEstado(){
        ArrayList<PermisoDTO> permisos = new ArrayList<PermisoDTO>();
        Respuesta res = perService.getPermisoByEstado(true);
        permisos=(ArrayList<PermisoDTO>) res.getResultado("Permisos");
        llenarTabla(permisos);
    }
    
    
    
    
    
    
    ArrayList<PermisoDTO> permisos_a_otorgar = new ArrayList<>();
    
    ArrayList<PermisoOtorgadoDTO> permisosOtorgadosUsuario = new ArrayList<>();
 
   
    
    
    
    public void llenarTabla(ArrayList<PermisoDTO> permisos){
        List<PermisosTableView> permisos2= new ArrayList<>();
        for(int i=0; i<permisos.size();i++){
            permisos2.add(new PermisosTableView(permisos.get(i)));  
        }
        
        tbPermisos.getColumns().clear();
        if(permisos2!=null){
            ObservableList items = FXCollections.observableArrayList(permisos2);   

            
            TableColumn colId = new TableColumn("ID");
            colId.setCellValueFactory(new PropertyValueFactory<PermisosTableView, String>("id"));
            TableColumn colCodigo = new TableColumn("Codigo");
            colCodigo.setCellValueFactory(new PropertyValueFactory<PermisosTableView, String>("codigo"));
            TableColumn colDescripcion = new TableColumn("Descripcion");
            colDescripcion.setCellValueFactory(new PropertyValueFactory<PermisosTableView, String>("descripcion"));
            TableColumn<PermisosTableView, String> cb = new TableColumn("Otorgar Permiso");
            cb.setCellValueFactory(new PropertyValueFactory<PermisosTableView, String>("select"));
            
 
            tbPermisos.getColumns().addAll(colId);
            tbPermisos.getColumns().addAll(colCodigo);
            tbPermisos.getColumns().addAll(colDescripcion);
            tbPermisos.getColumns().addAll(cb);
            //addButtonToTable();
            tbPermisos.setItems(items);
            
        }
    }
    
    
    public void modificarPermisos(ArrayList<PermisoDTO> permisos, ArrayList<PermisoOtorgadoDTO> permisosO){
        boolean modificado=false;
        boolean existia=false;
        if(permisos!=null){
            for(int i=0; i<permisos.size(); i++){
                existia=false;
                modificado=false;
                if(permisosO!=null){
                    for(int j=0; j<permisosO.size(); j++){
                        
                        if(!modificado){
                            if(permisos.get(i).getId().equals(permisosO.get(j).getPermiso().getId())){
                                existia=true;
                                if(!permisosO.get(j).getEstado()){
                                    PermisoOtorgadoDTO per = new PermisoOtorgadoDTO();
                                    per=permisosO.get(j);
                                    per.setEstado(true);
                                    perOtorService.modificar(per.getId(), per);
                                    
                                    modificado=true;
                                }
                            }
                        }
                    }
                    if(!existia){
                        PermisoOtorgadoDTO perN=new PermisoOtorgadoDTO();
                        perN.setEstado(true);
                        perN.setUsuario(usu);
                        perN.setPermiso(permisos.get(i));
                        perOtorService.guardar(perN);
                        
                    }
                }else{
                    PermisoOtorgadoDTO perN=new PermisoOtorgadoDTO();
                    perN.setEstado(true);
                    perN.setUsuario(usu);
                    perN.setPermiso(permisos.get(i));
                    perOtorService.guardar(perN);
                    
                }
            }
        }
        boolean existe=false;
        if(permisosO!=null){
            for(int i=0; i<permisosO.size(); i++){
                existe=false;
                if(permisos!=null)
                {
                    for(int j=0; j<permisos.size(); j++){
                        if(permisosO.get(i).getPermiso().getId().equals(permisos.get(j).getId())){
                            existe=true;
                        }
                    }
                    if(!existe){
                        PermisoOtorgadoDTO per = new PermisoOtorgadoDTO();
                        per=permisosO.get(i);
                        per.setEstado(false);
                        perOtorService.modificar(per.getId(), per);
                        
                    }
                }else{
                    PermisoOtorgadoDTO per = new PermisoOtorgadoDTO();
                    per=permisosO.get(i);
                    per.setEstado(false);
                    perOtorService.modificar(per.getId(), per);
                    
                }
                
                
            }
        }
        permisos_a_otorgar = new ArrayList<>();
    
        permisosOtorgadosUsuario = new ArrayList<>();
        AppContext.getInstance().set("permisosYaOtorgados",permisosOtorgadosUsuario);
        AppContext.getInstance().set("permisos_a_otorgar",permisos_a_otorgar);
    }
    
    
}
