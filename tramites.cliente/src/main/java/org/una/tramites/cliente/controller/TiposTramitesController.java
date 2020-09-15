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
import org.una.tramites.cliente.dto.TramiteTipoDTO;
import org.una.tramites.cliente.dto.UsuarioDTO;
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
    TramiteTipoService tramService = new TramiteTipoService();
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnBuscar.setDisable(true);
        txtBuscar.setDisable(true);
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Por id");
        opciones.add("Por descripcion");
        opciones.add("Por departamento");
        cmbTipoBusqueda.getItems().clear();
        cmbTipoBusqueda.getItems().addAll(opciones);
        cargarTramitesTiposTodos();
        AppContext.getInstance().set("ModalidadTramitesTipos", "");
        AppContext.getInstance().set("TramiteTipoEditar", "");
        AppContext.getInstance().set("TramiteTipoVer", "");
   
    }    

    public void cargarTramitesTiposTodos(){
        ArrayList<TramiteTipoDTO> tramitesTipos = new ArrayList<TramiteTipoDTO>();
        Respuesta res = tramService.getAll();
        tramitesTipos=(ArrayList<TramiteTipoDTO>) res.getResultado("TramitesTipos");
        llenarTabla(tramitesTipos);

    }

    
    public void cargarTramitesTiposDescripcion(String descripcion){
        
        ArrayList<TramiteTipoDTO> tramitesTipos = new ArrayList<TramiteTipoDTO>();
        Respuesta res = tramService.getByDescripcion(descripcion);
        tramitesTipos = (ArrayList<TramiteTipoDTO>) res.getResultado("TramitesTipos");
        llenarTabla(tramitesTipos);
    }
    
    public void cargarTramitesTiposId(Long id){
        ArrayList<TramiteTipoDTO> tramites = new ArrayList<TramiteTipoDTO>();
        Respuesta res = tramService.getById(id);
        TramiteTipoDTO tram = (TramiteTipoDTO) res.getResultado("TramiteTipo");
        tramites.add(tram);
        llenarTabla(tramites);
    }
    
    public void cargarTramitesTiposDepartamento(String departamento){
        ArrayList<TramiteTipoDTO> tramitesTipos = new ArrayList<TramiteTipoDTO>();
        Respuesta res = tramService.getByDepartamento((Long)obtenerDepartamento(departamento).getId());
        tramitesTipos = (ArrayList<TramiteTipoDTO>) res.getResultado("TramitesTipos");
        llenarTabla(tramitesTipos);
    }
    public DepartamentoDTO obtenerDepartamento(String departamento){
        DepartamentoService depService = new DepartamentoService();
        ArrayList<DepartamentoDTO> departamentos = new ArrayList<DepartamentoDTO>();
        Respuesta ress = depService.getDepartamentoByNombre(departamento);
        departamentos=(ArrayList<DepartamentoDTO>) ress.getResultado("Departamentos");
        
        return departamentos.get(0);
    }
    public void editarTramitesTipos(TramiteTipoDTO tram) throws IOException{
        if(tram!=null){
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");

            AppContext.getInstance().set("ModalidadTramitesTipos", "Editar");
            AppContext.getInstance().set("TramiteTipoEditar", tram);

            Parent root = FXMLLoader.load(App.class.getResource("tiposTramitesDetalleInformacion" + ".fxml"));
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
        }else{
            Mensaje.showAndWait(Alert.AlertType.WARNING, "Ver Tipo de Trámite", "Debes seleccionar un tipo de trámite");
        }
    }
    
    public void  ver(TramiteTipoDTO tram) throws IOException{
        if(tram!=null){
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");

            AppContext.getInstance().set("ModalidadTramitesTipos", "Ver");
            AppContext.getInstance().set("TramiteTipoVer", tram);

            Parent root = FXMLLoader.load(App.class.getResource("tiposTramitesDetalleInformacion" + ".fxml"));
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
        }else{
            Mensaje.showAndWait(Alert.AlertType.WARNING, "Ver Tipo de Trámite", "Debes seleccionar un tipo de trámite");
        }
    }
    public void llenarTabla(ArrayList<TramiteTipoDTO> tramiteTipo){
        tbTiposTramites.getColumns().clear();
        if(tramiteTipo!=null){
            ObservableList items = FXCollections.observableArrayList(tramiteTipo);   


            TableColumn colId = new TableColumn("ID");
            colId.setCellValueFactory(new PropertyValueFactory("id"));
            
            TableColumn colDescripcion = new TableColumn("Descripcion");
            colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
            
            TableColumn<TramiteTipoDTO, String> colEstado = new TableColumn("Estado");
            colEstado.setCellValueFactory(per -> {
                
                String estadoString;
                if(per.getValue().isEstado())
                    estadoString = "Activo";
                else
                    estadoString = "Inactivo";
                return new ReadOnlyStringWrapper(estadoString);
            });
            
            TableColumn colFechaRegistro = new TableColumn("Fecha Registro");
            colFechaRegistro.setCellValueFactory(new PropertyValueFactory("fechaRegistro"));
            
            TableColumn colFechaModificacion = new TableColumn("Fecha Modificacion");
            colFechaModificacion.setCellValueFactory(new PropertyValueFactory("fechaModificacion"));
            
            TableColumn colDepartamento = new TableColumn("Departamento");
            colDepartamento.setCellValueFactory(new PropertyValueFactory("departamento"));
            
            
            
            

            tbTiposTramites.getColumns().addAll(colId);
            tbTiposTramites.getColumns().addAll(colDescripcion);
            tbTiposTramites.getColumns().addAll(colEstado);
            tbTiposTramites.getColumns().addAll(colFechaRegistro);
            tbTiposTramites.getColumns().addAll(colFechaModificacion);
            tbTiposTramites.getColumns().addAll(colDepartamento);
            addButtonToTable();
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
                }else{
                    if(tipoBusqueda.equals("departamento")){
                        cargarTramitesTiposDepartamento(txtBuscar.getText());
                    }
                }
            }
        }else{
            Mensaje.showAndWait(Alert.AlertType.WARNING, "Busqueda", "Ingresa el valor a buscar");
        }
    }
    
    private void addButtonToTable() {
        TableColumn<TramiteTipoDTO, Void> colBtn = new TableColumn("Acciones");

        Callback<TableColumn<TramiteTipoDTO, Void>, TableCell<TramiteTipoDTO, Void>> cellFactory = new Callback<TableColumn<TramiteTipoDTO, Void>, TableCell<TramiteTipoDTO, Void>>() {
            @Override
            public TableCell<TramiteTipoDTO, Void> call(final TableColumn<TramiteTipoDTO, Void> param) {
                final TableCell<TramiteTipoDTO, Void> cell = new TableCell<TramiteTipoDTO, Void>() {

                    private final Button btn = new Button("Mod");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            try{
                            TramiteTipoDTO data = getTableView().getItems().get(getIndex());
                            editarTramitesTipos(data);
                            }catch(Exception ex){}
                        });
                    }
                    
                    private final Button btn2 = new Button("Ver");

                    {
                        btn2.setOnAction((ActionEvent event) -> {
                            try{
                            TramiteTipoDTO data = getTableView().getItems().get(getIndex());
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

        tbTiposTramites.getColumns().add(colBtn);

    }

    @FXML
    private void actBorrar(ActionEvent event) {
        txtBuscar.setText("");
        cargarTramitesTiposTodos();
    }

    @FXML
    private void actAgregar(ActionEvent event) throws IOException {
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        
        AppContext.getInstance().set("ModalidadTramitesTipos", "Agregar");
        
        Parent root = FXMLLoader.load(App.class.getResource("tiposTramitesDetalleInformacion" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }
    private String tipoBusqueda;
    @FXML
    private void actTipoBusqueda(ActionEvent event) {
        txtBuscar.setDisable(false);
        btnBuscar.setDisable(false);
        if(cmbTipoBusqueda.getValue().equals("Por id")){
            tipoBusqueda="id";
        }else{
            if(cmbTipoBusqueda.getValue().equals("Por descripcion")){
                tipoBusqueda="descripcion";
            }else if(cmbTipoBusqueda.getValue().equals("Por departamento")){
                tipoBusqueda="departamento";
            }
        }
    }
    
    @FXML
    private void actCerrar(ActionEvent event) {
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        Contenedor.getChildren().clear();
    }
  
    
}
