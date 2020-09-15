/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
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
import org.una.tramites.cliente.dto.TramiteTipoDTO;
import org.una.tramites.cliente.service.DepartamentoService;
import org.una.tramites.cliente.service.TramiteTipoService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Mensaje;
import org.una.tramites.cliente.util.Respuesta;


/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class TiposTramitesController implements Initializable {

    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private Button btnAgregar;
    @FXML
    private TableView<TramiteTipoDTO> tbTiposTramites;
    @FXML
    private Button btnCerrar;
    @FXML
    private ComboBox<String> cmbTipoBusqueda;

    /**
     * Initializes the controller class.
     */
    
    TramiteTipoDTO registroClick = new TramiteTipoDTO();
    TramiteTipoService tramService = new TramiteTipoService();
    
    //Implementar findByEstado, findByDepartamento
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnBuscar.setDisable(true);
        txtBuscar.setDisable(true);
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Por identificador unico");
        opciones.add("Por descripcion");
        opciones.add("Por estado");
        opciones.add("Por departamento");
        cmbTipoBusqueda.getItems().clear();
        cmbTipoBusqueda.getItems().addAll(opciones);
        cargarTramitesTiposTodos();
        
        
        tbTiposTramites.setRowFactory( tv -> {
            TableRow<TramiteTipoDTO> row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton()==MouseButton.PRIMARY && event.getClickCount()==2){
                    registroClick = row.getItem();
                    try{
                        editarTramitesTipos(registroClick);
                    }catch(Exception ex){
                        
                    }
                }
            });
            return row;
        
        });
    }    

    public void cargarTramitesTiposTodos(){
        ArrayList<TramiteTipoDTO> tramitesTipos = new ArrayList<TramiteTipoDTO>();
        Respuesta res = tramService.getAll();
        tramitesTipos=(ArrayList<TramiteTipoDTO>) res.getResultado("TramitesTipos");
        llenarTabla(tramitesTipos);

    }
    
    /*public void cargarTramitesTiposEstado(String esta){
        boolean estado=true;
        if(esta.equals("activo")||esta.equals("Activo")){
            estado = true;
        }else{
            if(esta.equals("inactivo")||esta.equals("Inactivo")){
                estado = false;
            }
        }
        ArrayList<TramiteTipoDTO> tramitesTipos = new ArrayList<TramiteTipoDTO>();
        Respuesta res = depService.getByEstado(estado);
        departamentos=(ArrayList<DepartamentoDTO>) res.getResultado("Departamentos");
        llenarTabla(departamentos);
    }*/
    
    /*public void cargarTramitesTiposNombre(String nombre){
        
        ArrayList<DepartamentoDTO> departamentos = new ArrayList<DepartamentoDTO>();
        Respuesta res = depService.getByNombre(nombre);
        departamentos=(ArrayList<DepartamentoDTO>) res.getResultado("Departamentos");
        llenarTabla(departamentos);
    }*/
    
    public void cargarTramitesTiposDescripcion(String descripcion){
        
        ArrayList<TramiteTipoDTO> tramitesTipos = new ArrayList<TramiteTipoDTO>();
        Respuesta res = tramService.getByDescripcion(descripcion);
        tramitesTipos = (ArrayList<TramiteTipoDTO>) res.getResultado("TramitesTipos");
        llenarTabla(tramitesTipos);
    }
    
    public void cargarTramitesTiposId(Long id){
        ArrayList<TramiteTipoDTO> tramitesTipos = new ArrayList<TramiteTipoDTO>();
        Respuesta res = tramService.getById(id);
        TramiteTipoDTO tramite = (TramiteTipoDTO) res.getResultado("TramiteTipo");
        tramitesTipos.add(tramite);
        llenarTabla(tramitesTipos);
    }
    
    public void llenarTabla(ArrayList<TramiteTipoDTO> tramiteTipo){
        tbTiposTramites.getColumns().clear();
        if(tramiteTipo!=null){
            ObservableList items = FXCollections.observableArrayList(tramiteTipo);   


            TableColumn colId = new TableColumn("ID");
            colId.setCellValueFactory(new PropertyValueFactory("id"));
            
            TableColumn colDescripcion = new TableColumn("Descripcion");
            colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
            
            TableColumn colEstado = new TableColumn("Estado");
            colEstado.setCellValueFactory(new PropertyValueFactory("estado"));
            
            TableColumn colFechaRegistro = new TableColumn("Fecha Registro");
            colFechaRegistro.setCellValueFactory(new PropertyValueFactory("fecha_registro"));
            
            TableColumn colFechaModificacion = new TableColumn("Fecha Modificacion");
            colFechaModificacion.setCellValueFactory(new PropertyValueFactory("fecha_modificacion"));
            
            TableColumn colDepartamento = new TableColumn("Departamento");
            colDepartamento.setCellValueFactory(new PropertyValueFactory("departamentos_id"));

            tbTiposTramites.getColumns().addAll(colId);
            tbTiposTramites.getColumns().addAll(colDescripcion);
            tbTiposTramites.getColumns().addAll(colEstado);
            tbTiposTramites.getColumns().addAll(colFechaRegistro);
            tbTiposTramites.getColumns().addAll(colFechaModificacion);
            tbTiposTramites.getColumns().addAll(colDepartamento);
            
            tbTiposTramites.setItems(items);
        }
    }
    @FXML
    private void actBuscar(ActionEvent event) {
        if(!txtBuscar.getText().isEmpty()){
            if(tipoBusqueda.equals("id")){
                cargarTramitesTiposId(Long.valueOf(txtBuscar.getText()));
            }else{
                if(tipoBusqueda.equals("descripcion")){
                    cargarTramitesTiposDescripcion(txtBuscar.getText());
                }/*else{
                    if(tipoBusqueda.equals("estado")){
                        cargarDepartamentosEstado(txtBuscar.getText());
                    }
                }*/
            }
        }else{
            Mensaje.showAndWait(Alert.AlertType.WARNING, "Busqueda", "Ingresa el valor a buscar");
        }
    }
    
    public void editarTramitesTipos(TramiteTipoDTO tram) throws IOException{
        
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        
        AppContext.getInstance().set("ModalidadTramitesTipos", "Editar");
        AppContext.getInstance().set("TramiteTipoEditar", tram);
        Parent root = FXMLLoader.load(App.class.getResource("tiposTramitesDetalleInformacion" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }

    @FXML
    private void actBorrar(ActionEvent event) {
        txtBuscar.setText("");
    }

    @FXML
    private void actAgregar(ActionEvent event) throws IOException {
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        
        AppContext.getInstance().set("ModalidadTramitesTipos", "Agregar");
        
        Parent root = FXMLLoader.load(App.class.getResource("tiposTramitesDetalleInformacion" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }

    @FXML
    private void actTipoBusqueda(ActionEvent event) {
        txtBuscar.setDisable(false);
        btnBuscar.setDisable(false);
        if(cmbTipoBusqueda.getValue().equals("Por identificador unico")){
            tipoBusqueda="id";
        }else{
            if(cmbTipoBusqueda.getValue().equals("Por Descripcion")){
                tipoBusqueda="descripcion";
            }else if(cmbTipoBusqueda.getValue().equals("Por estado")){
                tipoBusqueda="estado";
            }
        }
    }
    private String tipoBusqueda;
    @FXML
    private void actCerrar(ActionEvent event) {
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        Contenedor.getChildren().clear();
    }
    
}
