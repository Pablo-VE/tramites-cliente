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
import org.una.tramites.cliente.dto.DepartamentoDTO;
import org.una.tramites.cliente.dto.PermisoDTO;
import org.una.tramites.cliente.dto.UsuarioDTO;
import org.una.tramites.cliente.service.PermisoService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Mensaje;
import org.una.tramites.cliente.util.Respuesta;

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

            TableColumn<PermisoDTO, String> colEstado = new TableColumn("Estado");
            colEstado.setCellValueFactory(per -> {
            String estadoString;
                if(per.getValue().getEstado())
                    estadoString = "Activo";
                else
                    estadoString = "Inactivo";
                return new ReadOnlyStringWrapper(estadoString);
                });
            
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
            addButtonToTable();
            tbPermisos.setItems(items);
        }
    }
    public void editarPermiso(PermisoDTO per) throws IOException{
        if(per!=null){
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");

            AppContext.getInstance().set("ModalidadPermisos", "Modificar");
            AppContext.getInstance().set("PermisoEnCuestion", per);

            Parent root = FXMLLoader.load(App.class.getResource("permisosDetalleInformacion" + ".fxml"));
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
        }else{
            Mensaje.showAndWait(Alert.AlertType.WARNING, "Ver Usuario", "Debes seleccionar un usuario");
        }
    }
    

        
    public void  ver(PermisoDTO per) throws IOException{
        if(per!=null){
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");

            AppContext.getInstance().set("ModalidadPermisos", "Ver");
            AppContext.getInstance().set("PermisoEnCuestion", per);

            Parent root = FXMLLoader.load(App.class.getResource("permisosDetalleInformacion" + ".fxml"));
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
        }else{
            Mensaje.showAndWait(Alert.AlertType.WARNING, "Ver Usuario", "Debes seleccionar un usuario");
        }
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
        txtBuscar.setText("");
        txtBuscar.setPromptText("Consultar por Id o código");
        cargarPermisosTodos();
        Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Permisos", "Permisos disponibles");
    }

    @FXML
    private void actAgregar(ActionEvent event) throws IOException {
        
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        AppContext.getInstance().set("ModalidadPermisos", "Agregar");
        
        Parent root = FXMLLoader.load(App.class.getResource("permisosDetalleInformacion" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }

    @FXML
    private void actCerrar(ActionEvent event) {
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        Contenedor.getChildren().clear();
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
     private void addButtonToTable() {
        TableColumn<PermisoDTO, Void> colBtn = new TableColumn("Acciones");

        Callback<TableColumn<PermisoDTO, Void>, TableCell<PermisoDTO, Void>> cellFactory = new Callback<TableColumn<PermisoDTO, Void>, TableCell<PermisoDTO, Void>>() {
            @Override
            public TableCell<PermisoDTO, Void> call(final TableColumn<PermisoDTO, Void> param) {
                final TableCell<PermisoDTO, Void> cell = new TableCell<PermisoDTO, Void>() {

                    private final Button btn = new Button("Mod");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            try{
                            PermisoDTO data = getTableView().getItems().get(getIndex());
                            
                            editarPermiso(data);
                            }catch(Exception ex){}
                        });
                    }
                    
                    private final Button btn2 = new Button("Ver");

                    {
                        btn2.setOnAction((ActionEvent event) -> {
                            try{
                            PermisoDTO data = getTableView().getItems().get(getIndex());
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

        tbPermisos.getColumns().add(colBtn);

    }
}
