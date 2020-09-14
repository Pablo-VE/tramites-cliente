/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import org.una.tramites.cliente.App;
import org.una.tramites.cliente.dto.DepartamentoDTO;
import org.una.tramites.cliente.dto.PermisoDTO;
import org.una.tramites.cliente.service.PermisoService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Respuesta;
import proyectotitan.util.Mensaje;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class PermisosController implements Initializable {

    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private Button btnAgregar;
   
    @FXML
    private ComboBox<String> cbxTipoBusqueda;
    @FXML
    private TableView tbPermisos;
    @FXML
    private Button btnCerrar;
    
    PermisoDTO registroClick = new PermisoDTO();
    PermisoService perService = new PermisoService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Por id");
        opciones.add("Por estado");
        cbxTipoBusqueda.getItems().clear();
        cbxTipoBusqueda.getItems().addAll(opciones);
        cargarPermisosTodos();
       // cargarPermisosId(Long.valueOf(1));
        
        tbPermisos.setRowFactory( tv -> {
            TableRow<PermisoDTO> row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton()==MouseButton.PRIMARY && event.getClickCount()==2){
                    registroClick = row.getItem();
                    try{
                        editarPermiso(registroClick);
                    }catch(Exception ex){
                        
                    }
                }
            });
            return row;
        
        });

// TODO
    } 
    public void cargarPermisosTodos(){
        ArrayList<PermisoDTO> permisos = new ArrayList<PermisoDTO>();
        Respuesta res = perService.getAll();
        permisos=(ArrayList<PermisoDTO>) res.getResultado("Permisos");
        llenarTabla(permisos);
    }
    
    public void cargarPermisosId(Long id){
        ArrayList<PermisoDTO> permisos = new ArrayList<PermisoDTO>();
        Respuesta res = perService.getById(id);
        PermisoDTO per = (PermisoDTO) res.getResultado("Permiso");
        permisos.add(per);
        llenarTabla(permisos);
    }
    
    public void llenarTabla(ArrayList<PermisoDTO> permisos){
        tbPermisos.getColumns().clear();
        if(permisos!=null){
            ObservableList items = FXCollections.observableArrayList(permisos);   

            TableColumn colEstado = new TableColumn("Estado");
            colEstado.setCellValueFactory(new PropertyValueFactory("estado"));
            TableColumn colId = new TableColumn("ID");
            colId.setCellValueFactory(new PropertyValueFactory("id"));
            TableColumn colCodigo = new TableColumn("Codigo");
            colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
            TableColumn colDescripcion = new TableColumn("Descripcion");
            colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
            
            tbPermisos.getColumns().addAll(colEstado);
            tbPermisos.getColumns().addAll(colId);
            tbPermisos.getColumns().addAll(colCodigo);
            tbPermisos.getColumns().addAll(colDescripcion);


            tbPermisos.setItems(items);
        }
    }
        public void editarPermiso(PermisoDTO per) throws IOException{
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        
       // AppContext.getInstance().set("ModalidadDepartamentos", "Editar");
        //AppContext.getInstance().set("DepartamentoEditar", per);
        
        Parent root = FXMLLoader.load(App.class.getResource("permisosDetalleInformacion" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }
        
    public void cargarPermisosEstado(String esta){
        boolean estado=true;
        if(esta.equals("activo")||esta.equals("Activo")){
            estado = true;
        }else{
            if(esta.equals("inactivo")||esta.equals("Inactivo")){
                estado = false;
            }
        }
        ArrayList<PermisoDTO> permisos = new ArrayList<PermisoDTO>();
        Respuesta res = perService.getPermisoByEstado(estado);
        permisos=(ArrayList<PermisoDTO>) res.getResultado("Permisos");
        llenarTabla(permisos);
    } 
    
    @FXML
    private void actBuscar(ActionEvent event) {
        if(!txtBuscar.getText().isEmpty()){
            if(tipoBusqueda.equals("id")){
                cargarPermisosId(Long.valueOf(txtBuscar.getText()));
            }else{
                if(tipoBusqueda.equals("estado")){
                    cargarPermisosEstado(txtBuscar.getText());
                }
            }
        }else{
            Mensaje.showAndWait(Alert.AlertType.WARNING, "Busqueda", "Ingresa el valor a buscar");
        }
    }
    
 
    
    @FXML
    private void actBorrar(ActionEvent event) {
    }

    @FXML
    private void actAgregar(ActionEvent event) {
    }

    @FXML
    private void actCerrar(ActionEvent event) {
    }


    private String tipoBusqueda;
    @FXML
    private void actTipoBusqueda(ActionEvent event) {
        txtBuscar.setDisable(false);
        btnBuscar.setDisable(false);
        if(cbxTipoBusqueda.getValue().equals("Por id")){
            tipoBusqueda="id";
            txtBuscar.setPromptText("Id");
        }else if(cbxTipoBusqueda.getValue().equals("Por estado")){
            tipoBusqueda="estado";
            txtBuscar.setPromptText("Activo o Inactivo");
        }
        
    } 
}
