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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.una.tramites.cliente.App;
import org.una.tramites.cliente.dto.ParametrosGeneralesDTO;
import org.una.tramites.cliente.service.ParametrosGeneralesService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Mensaje;
import org.una.tramites.cliente.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class ParametrosController implements Initializable {

    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button botonCerrar;
    @FXML
    private ComboBox<String> cbxTipoBusqueda;
    
    ParametrosGeneralesDTO registroClick = new ParametrosGeneralesDTO();
    ParametrosGeneralesService parService = new ParametrosGeneralesService();
    @FXML
    private TableView tablaParametros;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Por id");
        opciones.add("Por nombre");
        opciones.add("Por estado");
        cbxTipoBusqueda.getItems().clear();
        cbxTipoBusqueda.getItems().addAll(opciones);
        cargarPermisosTodos();
        
    }    
    public void cargarPermisosTodos(){
        ArrayList<ParametrosGeneralesDTO> parametrosGeneral = new ArrayList<ParametrosGeneralesDTO>();
        Respuesta res = parService.getAll();
        parametrosGeneral=(ArrayList<ParametrosGeneralesDTO>) res.getResultado("ParametrosGeneral");
        llenarTabla(parametrosGeneral);
    }
     public void llenarTabla(ArrayList<ParametrosGeneralesDTO> parametrosGeneral){
        tablaParametros.getColumns().clear();
        if(parametrosGeneral!=null){
            ObservableList items = FXCollections.observableArrayList(parametrosGeneral);   

            TableColumn<ParametrosGeneralesDTO, String> colEstado = new TableColumn("Estado");
            colEstado.setCellValueFactory(par -> {
            String estadoString;
                if(par.getValue().getEstado())
                    estadoString = "Activo";
                else
                    estadoString = "Inactivo";
                return new ReadOnlyStringWrapper(estadoString);
                });
            
            TableColumn colId = new TableColumn("ID");
            colId.setCellValueFactory(new PropertyValueFactory("id"));
            TableColumn colNombre = new TableColumn("Nombre");
            colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
            TableColumn colValor = new TableColumn("Valor");
            colValor.setCellValueFactory(new PropertyValueFactory("valor"));
            TableColumn colDescripcion = new TableColumn("Descripcion");
            colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
            
            TableColumn colFechaRegistro = new TableColumn("Fecha Registro");
            colFechaRegistro.setCellValueFactory(new PropertyValueFactory("fechaRegistro"));
            TableColumn colFechaModificacion = new TableColumn("Fecha Modificacion");
            colFechaModificacion.setCellValueFactory(new PropertyValueFactory("fechaModificacion"));
            
         
            tablaParametros.getColumns().addAll(colId);
            tablaParametros.getColumns().addAll(colNombre);
            tablaParametros.getColumns().addAll(colValor);
            tablaParametros.getColumns().addAll(colEstado);
            tablaParametros.getColumns().addAll(colDescripcion);
            tablaParametros.getColumns().addAll(colFechaRegistro);
            tablaParametros.getColumns().addAll(colFechaModificacion);
            addButtonToTable();
            tablaParametros.setItems(items);
        }
    }
     
     private void addButtonToTable() {
        TableColumn<ParametrosGeneralesDTO, Void> colBtn = new TableColumn("Acciones");

        Callback<TableColumn<ParametrosGeneralesDTO, Void>, TableCell<ParametrosGeneralesDTO, Void>> cellFactory = new Callback<TableColumn<ParametrosGeneralesDTO, Void>, TableCell<ParametrosGeneralesDTO, Void>>() {
            @Override
            public TableCell<ParametrosGeneralesDTO, Void> call(final TableColumn<ParametrosGeneralesDTO, Void> param) {
                final TableCell<ParametrosGeneralesDTO, Void> cell = new TableCell<ParametrosGeneralesDTO, Void>() {

                    private final Button btn = new Button("Mod");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            try{
                            ParametrosGeneralesDTO data = getTableView().getItems().get(getIndex());
                            
                            editarParametrosGenerales(data);
                            }catch(Exception ex){}
                        });
                    }
                    
                    private final Button btn2 = new Button("Ver");

                    {
                        btn2.setOnAction((ActionEvent event) -> {
                            try{
                            ParametrosGeneralesDTO data = getTableView().getItems().get(getIndex());
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

        tablaParametros.getColumns().add(colBtn);

    }
    
     public void editarParametrosGenerales(ParametrosGeneralesDTO per) throws IOException{
        if(per!=null){
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");

            AppContext.getInstance().set("ModalidadParametros", "Modificar");
            AppContext.getInstance().set("ParametroEnCuestion", per);

            Parent root = FXMLLoader.load(App.class.getResource("parametrosDetalleInformacion" + ".fxml"));
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
        }else{
            Mensaje.showAndWait(Alert.AlertType.WARNING, "Modificar Parametros Generales", "Debes seleccionar un parametro general");
        }
    }
    
     public void  ver(ParametrosGeneralesDTO per) throws IOException{
        if(per!=null){
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");

            AppContext.getInstance().set("ModalidadParametros", "Ver");
            AppContext.getInstance().set("ParametroEnCuestion", per);

            Parent root = FXMLLoader.load(App.class.getResource("parametrosDetalleInformacion" + ".fxml"));
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
        }else{
            Mensaje.showAndWait(Alert.AlertType.WARNING, "Ver Parametros Generales", "Debes seleccionar un parametro general");
        }
    } 
        
    @FXML
    private void actBuscar(ActionEvent event) {
        if(!txtBuscar.getText().isEmpty()){
            if(tipoBusqueda.equals("id")){
                cargarParametrosId(Long.valueOf(txtBuscar.getText()));
            }else{
                if(tipoBusqueda.equals("estado")){
                    cargarParametrosEstado(txtBuscar.getText());
                }else{
                    if(tipoBusqueda.equals("nombre")){
                        cargarParametrosNombre(txtBuscar.getText());
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
        if(cbxTipoBusqueda.getValue().equals("Por id")){
            tipoBusqueda="id";
            txtBuscar.setPromptText("Id");
        }else if(cbxTipoBusqueda.getValue().equals("Por estado")){
            tipoBusqueda="estado";
            txtBuscar.setPromptText("Activo o Inactivo");
        }else{
            if(cbxTipoBusqueda.getValue().equals("Por nombre")){
                tipoBusqueda="nombre";
                txtBuscar.setPromptText("Nombre");
            }
        }  
    }
    
     public void cargarParametrosId(Long id){
        ArrayList<ParametrosGeneralesDTO> parametros = new ArrayList<ParametrosGeneralesDTO>();
        Respuesta res = parService.getById(id);
        ParametrosGeneralesDTO per = (ParametrosGeneralesDTO) res.getResultado("ParametrosGeneral");
        parametros.add(per);
        llenarTabla(parametros);
    }
      public void cargarParametrosEstado(String esta){
        boolean estado=true;
        if(esta.equals("activo")||esta.equals("Activo")){
            estado = true;
        }else{
            if(esta.equals("inactivo")||esta.equals("Inactivo")){
                estado = false;
            }
        }
        ArrayList<ParametrosGeneralesDTO> parametros = new ArrayList<ParametrosGeneralesDTO>();
        Respuesta res = parService.getByEstado(estado);
        parametros=(ArrayList<ParametrosGeneralesDTO>) res.getResultado("ParametrosGeneral");
        llenarTabla(parametros);
    } 
    
      public void cargarParametrosNombre(String nombre){
        ArrayList<ParametrosGeneralesDTO> parametros = new ArrayList<ParametrosGeneralesDTO>();
        Respuesta res = parService.getByNombre(nombre);
        parametros=(ArrayList<ParametrosGeneralesDTO>) res.getResultado("ParametrosGeneral");
        llenarTabla(parametros);
    }
    @FXML
    private void actBorrar(ActionEvent event) {
        txtBuscar.setText("");
        txtBuscar.setPromptText("Consultar por Id, Nombre o Estado");
        cargarPermisosTodos();
        Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Parametros Generales", "Parametros disponibles");
    }

    @FXML
    private void actAgregar(ActionEvent event) throws IOException {
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        AppContext.getInstance().set("ModalidadParametros", "Agregar");
        
        Parent root = FXMLLoader.load(App.class.getResource("parametrosDetalleInformacion" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }

}
