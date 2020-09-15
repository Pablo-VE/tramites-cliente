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
import javafx.beans.property.ReadOnlyStringWrapper;
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
import org.una.tramites.cliente.dto.UsuarioDTO;
import org.una.tramites.cliente.service.UsuarioService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Respuesta;
import org.una.tramites.cliente.util.Mensaje;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class UsuariosPrincipalController implements Initializable {

    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private TableView<UsuarioDTO> tbUsuarios;
    @FXML
    private ComboBox<String> cmbTipoBusqueda;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnVer;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnModificar;

    UsuarioService usuService = new UsuarioService();

    
    UsuarioDTO registroClick = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       cargarTodos();
        
        
        tbUsuarios.setRowFactory( tv -> {
            TableRow<UsuarioDTO> row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton()==MouseButton.PRIMARY && event.getClickCount()==1){
                    registroClick = row.getItem();
                }
            });
            return row;
        
        });
    }    

    @FXML
    private void actBuscar(ActionEvent event) {
    }

    @FXML
    private void actBorrar(ActionEvent event) {
    }

    @FXML
    private void actVolver(ActionEvent event) {
    }

    @FXML
    private void actVer(ActionEvent event) throws IOException {
        if(registroClick!=null){
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");

            AppContext.getInstance().set("ModalidadUsuarios", "Ver");
            AppContext.getInstance().set("UsuarioEnCuestion", registroClick);

            Parent root = FXMLLoader.load(App.class.getResource("usuarios" + ".fxml"));
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
        }else{
            Mensaje.showAndWait(Alert.AlertType.WARNING, "Ver Usuario", "Debes seleccionar un usuario");
        }
    }

    @FXML
    private void actAgregar(ActionEvent event) throws IOException {
       
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");

            AppContext.getInstance().set("ModalidadUsuarios", "Agregar");

            Parent root = FXMLLoader.load(App.class.getResource("usuarios" + ".fxml"));
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
        
    }

    @FXML
    private void actModificar(ActionEvent event) throws IOException {
        if(registroClick!=null){
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");

            AppContext.getInstance().set("ModalidadUsuarios", "Modificar");
            AppContext.getInstance().set("UsuarioEnCuestion", registroClick);

            Parent root = FXMLLoader.load(App.class.getResource("usuarios" + ".fxml"));
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
        }else{
            Mensaje.showAndWait(Alert.AlertType.WARNING, "Ver Usuario", "Debes seleccionar un usuario");
        }
    }

    
    public void cargarTodos(){
        
        ArrayList<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
        Respuesta res = usuService.getAll();
        usuarios=(ArrayList<UsuarioDTO>) res.getResultado("Usuarios");
        llenarTabla(usuarios);
    }
    
    public void cargarNombre(String nombre){
        
        ArrayList<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
        Respuesta res = usuService.getUsersByNombre(nombre);
        usuarios=(ArrayList<UsuarioDTO>) res.getResultado("Usuarios");
        llenarTabla(usuarios);
    }
    
    public void cargarCedula(String cedula){
        
        ArrayList<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
        Respuesta res = usuService.getUsersByCedula(cedula);
        usuarios=(ArrayList<UsuarioDTO>) res.getResultado("Usuarios");
        llenarTabla(usuarios);
    }
    
    public void cargarId(Long id){
        ArrayList<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
        Respuesta res = usuService.getUserById(id);
        UsuarioDTO usu = (UsuarioDTO) res.getResultado("Usuario");
        usuarios.add(usu);
        llenarTabla(usuarios);
    }
    
    
    
    public void llenarTabla(ArrayList<UsuarioDTO> usuarios){
        tbUsuarios.getColumns().clear();
        if(usuarios!=null){
            ObservableList items = FXCollections.observableArrayList(usuarios);   

            TableColumn colId = new TableColumn("ID");
            colId.setCellValueFactory(new PropertyValueFactory("id"));
            TableColumn colNombre = new TableColumn("Nombre");
            colNombre.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));
            TableColumn<UsuarioDTO, String> colEstado = new TableColumn("Estado");
            colEstado.setCellValueFactory(per -> {
            String estadoString;
            if(per.getValue().getEstado())
                estadoString = "Activo";
            else
                estadoString = "Inactivo";
            return new ReadOnlyStringWrapper(estadoString);
        });
            TableColumn<UsuarioDTO, String> colJefe = new TableColumn("Jefe");
            colJefe.setCellValueFactory(per -> {
            String estadoString;
            if(per.getValue().getEsJefe())
                estadoString = "Si";
            else
                estadoString = "No";
            return new ReadOnlyStringWrapper(estadoString);
        });
            
            
            tbUsuarios.getColumns().addAll(colId);
            tbUsuarios.getColumns().addAll(colNombre);
            tbUsuarios.getColumns().addAll(colEstado);
            tbUsuarios.getColumns().addAll(colJefe);
            
            tbUsuarios.setItems(items);
        }
    }
    
}
