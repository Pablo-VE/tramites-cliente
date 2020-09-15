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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
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
    private Button btnAgregar;

    UsuarioService usuService = new UsuarioService();

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Por id");
        opciones.add("Por nombre");
        opciones.add("Por cedula");
        cmbTipoBusqueda.getItems().clear();
        cmbTipoBusqueda.getItems().addAll(opciones);
        cargarTodos(); 
    }    

    @FXML
    private void actBuscar(ActionEvent event) {
         if(!txtBuscar.getText().isEmpty()){
            if(tipoBusqueda.equals("id")){
                cargarId(Long.valueOf(txtBuscar.getText()));
            }else{
                if(tipoBusqueda.equals("nombre")){
                    cargarNombre(txtBuscar.getText());
                }else{
                    if(tipoBusqueda.equals("cedula")){
                        cargarCedula(txtBuscar.getText());
                    }
                }
            }
        }else{
            Mensaje.showAndWait(Alert.AlertType.WARNING, "Busqueda", "Ingresa el valor a buscar");
        }
    }
    
    private String tipoBusqueda;
    @FXML
    private void actTipoBusqueda(ActionEvent event) {
        txtBuscar.setDisable(false);
        btnBuscar.setDisable(false);
        if(cmbTipoBusqueda.getValue().equals("Por id")){
            tipoBusqueda="id";
            txtBuscar.setPromptText("Id");
        }else if(cmbTipoBusqueda.getValue().equals("Por nombre")){
            tipoBusqueda="nombre";
            txtBuscar.setPromptText("Nombre");
        }else{
            if(cmbTipoBusqueda.getValue().equals("Por cedula")){
                tipoBusqueda="cedula";
                txtBuscar.setPromptText("cédula");   
            }
        }
    }

    @FXML
    private void actBorrar(ActionEvent event) {
        txtBuscar.setText("");
        txtBuscar.setPromptText("Consultar por Id, nombre o cédula");
        cmbTipoBusqueda.setPromptText("Tipo de busqueda");
        cargarTodos();
        Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Usuarios", "Usuarios disponibles");
    }

    @FXML
    private void actVolver(ActionEvent event) {
    }

   
    
    
    public void  ver(UsuarioDTO usu) throws IOException{
        if(usu!=null){
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");

            AppContext.getInstance().set("ModalidadUsuarios", "Ver");
            AppContext.getInstance().set("UsuarioEnCuestion", usu);

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

    
    public void modificar(UsuarioDTO usu) throws IOException{
        if(usu!=null){
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");

            AppContext.getInstance().set("ModalidadUsuarios", "Modificar");
            AppContext.getInstance().set("UsuarioEnCuestion", usu);

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
            TableColumn colCedula = new TableColumn("Cedula");
            colCedula.setCellValueFactory(new PropertyValueFactory("cedula"));
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
            tbUsuarios.getColumns().addAll(colCedula);
            tbUsuarios.getColumns().addAll(colEstado);
            tbUsuarios.getColumns().addAll(colJefe);
            addButtonToTable();
            tbUsuarios.setItems(items);
        }
    }
    
    private void addButtonToTable() {
        TableColumn<UsuarioDTO, Void> colBtn = new TableColumn("Acciones");

        Callback<TableColumn<UsuarioDTO, Void>, TableCell<UsuarioDTO, Void>> cellFactory = new Callback<TableColumn<UsuarioDTO, Void>, TableCell<UsuarioDTO, Void>>() {
            @Override
            public TableCell<UsuarioDTO, Void> call(final TableColumn<UsuarioDTO, Void> param) {
                final TableCell<UsuarioDTO, Void> cell = new TableCell<UsuarioDTO, Void>() {

                    private final Button btn = new Button("Editar");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            try{
                            UsuarioDTO data = getTableView().getItems().get(getIndex());
                            modificar(data);
                            }catch(Exception ex){}
                        });
                    }
                    
                    private final Button btn2 = new Button("Ver");

                    {
                        btn2.setOnAction((ActionEvent event) -> {
                            try{
                            UsuarioDTO data = getTableView().getItems().get(getIndex());
                            ver(data);
                            }catch(Exception ex){}
                        });
                    }
                    
                    HBox pane = new HBox(btn, btn2);

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(pane);
                            
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tbUsuarios.getColumns().add(colBtn);

    }
    
}
