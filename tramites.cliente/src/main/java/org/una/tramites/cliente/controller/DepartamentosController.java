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
import org.una.tramites.cliente.service.DepartamentoService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Respuesta;
import org.una.tramites.cliente.util.Mensaje;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class DepartamentosController implements Initializable {

    @FXML
    private TableView<DepartamentoDTO> tablaDepartamentos;
    @FXML
    private Button botonCerrar;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private ComboBox<String> cbxTipoBusqueda;
    @FXML
    private Button btnAgregar;

    /**
     * Initializes the controller class.
     */
    DepartamentoDTO registroClick = new DepartamentoDTO();
    DepartamentoService depService = new DepartamentoService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnBuscar.setDisable(true);
        txtBuscar.setDisable(true);
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Por identificador unico");
        opciones.add("Por nombre");
        opciones.add("Por estado");
        cbxTipoBusqueda.getItems().clear();
        cbxTipoBusqueda.getItems().addAll(opciones);
        cargarDepartamentosTodos();
        
        
        tablaDepartamentos.setRowFactory( tv -> {
            TableRow<DepartamentoDTO> row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton()==MouseButton.PRIMARY && event.getClickCount()==2){
                    registroClick = row.getItem();
                    try{
                        editarDepartamento(registroClick);
                    }catch(Exception ex){
                        
                    }
                }
            });
            return row;
        
        });
        // TODO
    }    

    public void cargarDepartamentosTodos(){
        ArrayList<DepartamentoDTO> departamentos = new ArrayList<DepartamentoDTO>();
        Respuesta res = depService.getAll();
        departamentos=(ArrayList<DepartamentoDTO>) res.getResultado("Departamentos");
        llenarTabla(departamentos);

    }
    
    public void cargarDepartamentosEstado(String esta){
        boolean estado=true;
        if(esta.equals("activo")||esta.equals("Activo")){
            estado = true;
        }else{
            if(esta.equals("inactivo")||esta.equals("Inactivo")){
                estado = false;
            }
        }
        ArrayList<DepartamentoDTO> departamentos = new ArrayList<DepartamentoDTO>();
        Respuesta res = depService.getDepartamentoByEstado(estado);
        departamentos=(ArrayList<DepartamentoDTO>) res.getResultado("Departamentos");
        llenarTabla(departamentos);
    }
    
    public void cargarDepartamentosNombre(String nombre){
        
        ArrayList<DepartamentoDTO> departamentos = new ArrayList<DepartamentoDTO>();
        Respuesta res = depService.getDepartamentoByNombre(nombre);
        departamentos=(ArrayList<DepartamentoDTO>) res.getResultado("Departamentos");
        llenarTabla(departamentos);
    }
    
    public void cargarDepartamentosId(Long id){
        ArrayList<DepartamentoDTO> departamentos = new ArrayList<DepartamentoDTO>();
        Respuesta res = depService.getById(id);
        DepartamentoDTO depa = (DepartamentoDTO) res.getResultado("Departamento");
        departamentos.add(depa);
        llenarTabla(departamentos);
    }
    
    public void llenarTabla(ArrayList<DepartamentoDTO> departamentos){
        tablaDepartamentos.getColumns().clear();
        if(departamentos!=null){
            ObservableList items = FXCollections.observableArrayList(departamentos);   


            TableColumn colId = new TableColumn("ID");
            colId.setCellValueFactory(new PropertyValueFactory("id"));
            TableColumn colNombre = new TableColumn("Nombre");
            colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
            TableColumn colEstado = new TableColumn("Estado");
            colEstado.setCellValueFactory(new PropertyValueFactory("estado"));

            tablaDepartamentos.getColumns().addAll(colId);
            tablaDepartamentos.getColumns().addAll(colNombre);
            tablaDepartamentos.getColumns().addAll(colEstado);

            tablaDepartamentos.setItems(items);
        }
    }
    
    
    @FXML
    private void actBuscar(ActionEvent event) {
        if(!txtBuscar.getText().isEmpty()){
            if(tipoBusqueda.equals("id")){
                cargarDepartamentosId(Long.valueOf(txtBuscar.getText()));
            }else{
                if(tipoBusqueda.equals("nombre")){
                    cargarDepartamentosNombre(txtBuscar.getText());
                }else{
                    if(tipoBusqueda.equals("estado")){
                        cargarDepartamentosEstado(txtBuscar.getText());
                    }
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
    private void actAgregar(ActionEvent event) throws IOException {
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        
        AppContext.getInstance().set("ModalidadDepartamentos", "Agregar");
        
        Parent root = FXMLLoader.load(App.class.getResource("departamentosDetalleInformacion" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }

    
    public void editarDepartamento(DepartamentoDTO depa) throws IOException{
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        
        AppContext.getInstance().set("ModalidadDepartamentos", "Editar");
        AppContext.getInstance().set("DepartamentoEditar", depa);
        
        Parent root = FXMLLoader.load(App.class.getResource("departamentosDetalleInformacion" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }
    
    private String tipoBusqueda;
    @FXML
    private void actTipoBusqueda(ActionEvent event) {
        txtBuscar.setDisable(false);
        btnBuscar.setDisable(false);
        if(cbxTipoBusqueda.getValue().equals("Por identificador unico")){
            tipoBusqueda="id";
        }else{
            if(cbxTipoBusqueda.getValue().equals("Por nombre")){
                tipoBusqueda="nombre";
            }else if(cbxTipoBusqueda.getValue().equals("Por estado")){
                tipoBusqueda="estado";
            }
        }
    }

    @FXML
    private void actCerrar(ActionEvent event) {
    }
    
}
