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
    private TableView<PermisoDTO> tbPermisos;

    /**
     * Initializes the controller class.
     */
    
    PermisoOtorgadoService perOtorService = new PermisoOtorgadoService();
    
    private String modalidad;
    
    UsuarioDTO usu = new UsuarioDTO();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modalidad = (String) AppContext.getInstance().get("ModalidadUsuarios");
        if(modalidad.equals("Agregar")){
            usu= (UsuarioDTO) AppContext.getInstance().get("UsuarioNuevo");
        }else{
            if(modalidad.equals("Modificar")){
                usu=(UsuarioDTO) AppContext.getInstance().get("UsuarioEnCuestion");
                
            }
        }
        cargarPermisosEstado();
        // TODO
    }    

    @FXML
    private void actCancelar(ActionEvent event) {
    }

    @FXML
    private void actLimpiar(ActionEvent event) {
    }

    @FXML
    private void actGuardar(ActionEvent event) {
        if(modalidad.equals("Agregar")){
            if(permisos_a_otorgar==null){
                Mensaje.showAndWait(Alert.AlertType.WARNING, "Permisos a otorgar", "No ha seleccionado ningun permiso para otorgarle al usuario");
            
            }else{
                List<PermisoOtorgadoDTO> permisosOtorgar = new ArrayList<>();
                for(int i=0; i<permisos_a_otorgar.size(); i++){
                    PermisoOtorgadoDTO permisoOtorgado = new PermisoOtorgadoDTO();
                    permisoOtorgado.setUsuario(usu);
                    permisoOtorgado.setEstado(true);
                    permisoOtorgado.setPermiso(permisos_a_otorgar.get(i));
//                    perOtorService.guardar(permisoOtorgado);
                    permisosOtorgar.add(permisoOtorgado);
                }
                usu.setPermisosOtorgados(permisosOtorgar);
                AppContext.getInstance().set("UsuarioNuevo", usu);
                Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Permisos a otorgar", "Se le han a establecidos persmisos al nuevo usuario");
            }
        }
        mostrarPermisosAOtorgar();
    }
    
    PermisoService perService = new PermisoService();
    
    public void cargarPermisosEstado(){
        ArrayList<PermisoDTO> permisos = new ArrayList<PermisoDTO>();
        Respuesta res = perService.getPermisoByEstado(true);
        permisos=(ArrayList<PermisoDTO>) res.getResultado("Permisos");
        llenarTabla(permisos);
    }
    
    public void llenarTabla(ArrayList<PermisoDTO> permisos){
        tbPermisos.getColumns().clear();
        if(permisos!=null){
            ObservableList items = FXCollections.observableArrayList(permisos);   

            
           
            
            TableColumn colId = new TableColumn("ID");
            colId.setCellValueFactory(new PropertyValueFactory("id"));
            TableColumn colCodigo = new TableColumn("Codigo");
            colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
            TableColumn colDescripcion = new TableColumn("Descripcion");
            colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
            
          
            tbPermisos.getColumns().addAll(colId);
            tbPermisos.getColumns().addAll(colCodigo);
            tbPermisos.getColumns().addAll(colDescripcion);
            addButtonToTable();
            tbPermisos.setItems(items);
            
        }
    }
    
    private void addButtonToTable() {
        TableColumn<PermisoDTO, Void> colCB = new TableColumn("Otorgar Permiso");

        Callback<TableColumn<PermisoDTO, Void>, TableCell<PermisoDTO, Void>> cellFactory = new Callback<TableColumn<PermisoDTO, Void>, TableCell<PermisoDTO, Void>>() {
            
            @Override
            public TableCell<PermisoDTO, Void> call(final TableColumn<PermisoDTO, Void> param) {
                final TableCell<PermisoDTO, Void> cell = new TableCell<PermisoDTO, Void>() {
                    
                   
                    private final CheckBox cbox = new CheckBox();
                    {
                        
                        //System.out.println(getTableRow().getItem().getCodigo());
                     //   System.out.println(getTableView().getItems().get(getIndex()).getCodigo());
                        cbox.setOnAction((ActionEvent event) -> {
                            try{
                            PermisoDTO data = getTableView().getItems().get(getIndex());
                                setearPermisosAOtorgar(data);
                            }catch(Exception ex){}
                        });
                        
                    }
                    
                    
                    

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(cbox);
                            
                        }
                    }
                };
                return cell;
            }
        };

       //olCB.get
        colCB.setCellFactory(cellFactory);

        tbPermisos.getColumns().add(colCB);
    }
    
    
    ArrayList<PermisoDTO> permisos_a_otorgar = new ArrayList<>();
    
    public void setearPermisosAOtorgar(PermisoDTO permiso){
        if(permisos_a_otorgar==null){
            permisos_a_otorgar.add(permiso);
        }else{
            boolean esta=false;
            for(int i=0; i<permisos_a_otorgar.size(); i++){
                if(permisos_a_otorgar.get(i).getId()==permiso.getId()){
                    esta=true;
                }
            }
            if(esta){
                eliminarPermisosAOtorgar(permiso);
            }else{
                permisos_a_otorgar.add(permiso);
            }
        }
        
    }
    
    
    ArrayList<PermisoDTO> permisosOtorgadosUsuario = new ArrayList<>();
    
    public boolean permisoOtorgado(PermisoDTO per){
        Respuesta res = perOtorService.getByUsuarioPermiso(usu, per);
        if(res.getEstado()){
            return true;
        }else{
            System.out.println(res.getMensajeInterno());
        }
        return false;
    }
    
    
    
    public void eliminarPermisosAOtorgar(PermisoDTO permiso){
        for(int i=0; i<permisos_a_otorgar.size(); i++){
            if(permisos_a_otorgar.get(i).getId()==permiso.getId()){
                permisos_a_otorgar.remove(i);
            }
        }
    }
    
    public void mostrarPermisosAOtorgar(){
        System.out.println("Permisos a otorgar");
        if(permisos_a_otorgar==null){
            System.out.println("Lista Vacia");
        }else{
            for(int i=0; i<permisos_a_otorgar.size(); i++){
                System.out.println(permisos_a_otorgar.get(i).getCodigo());
            }
        }
    }
    
    
    
    public void llenarTabla2(ArrayList<PermisoDTO> permisos){
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
            TableColumn cb = new TableColumn("Otorgar Permiso");
            cb.setCellValueFactory(new PropertyValueFactory<PermisosTableView, String>("select"));
          
            tbPermisos.getColumns().addAll(colId);
            tbPermisos.getColumns().addAll(colCodigo);
            tbPermisos.getColumns().addAll(colDescripcion);
            tbPermisos.getColumns().addAll(cb);
            //addButtonToTable();
            tbPermisos.setItems(items);
            
        }
    }
    
}
